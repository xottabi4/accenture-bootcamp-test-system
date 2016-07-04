/**
 * @license HTTP Auth Interceptor Module for AngularJS
 * (c) 2012 Witold Szczerba
 * License: MIT
 */
(function ()
{
    'use strict';

    angular.module('http-auth-interceptor', ['http-auth-interceptor-buffer'])

            .factory('authService', ['$rootScope', 'httpBuffer', function ($rootScope, httpBuffer)
            {
                return {
                    /**
                     * Call this function to indicate that authentication was successfull and trigger a
                     * retry of all deferred requests.
                     * @param data an optional argument to pass on to $broadcast which may be useful for
                     * example if you need to pass through details of the user that was logged in
                     */
                    loginConfirmed: function (data, configUpdater)
                    {
                        var updater = configUpdater || function (config)
                        {
                            return config;
                        };
                        $rootScope.$broadcast('event:auth-loginConfirmed', data);
                        httpBuffer.retryAll(updater);
                    },

                    /**
                     * Call this function to indicate that authentication should not proceed.
                     * All deferred requests will be abandoned or rejected (if reason is provided).
                     * @param data an optional argument to pass on to $broadcast.
                     * @param reason if provided, the requests are rejected; abandoned otherwise.
                     */
                    loginCancelled: function (data, reason)
                    {
                        httpBuffer.rejectAll(reason);
                        $rootScope.$broadcast('event:auth-loginCancelled', data);
                    }
                };
            }]).constant('authServiceConfig', {authUrl: '', loginFailureSignaledWith401: false})

    /**
     * $http interceptor.
     * On 401 response (without 'ignoreAuthModule' option) stores the request
     * and broadcasts 'event:angular-auth-loginRequired'.
     */.config(['$httpProvider', function ($httpProvider)
            {
                $httpProvider.interceptors.push(['$rootScope', '$q', 'httpBuffer', 'authServiceConfig', function ($rootScope, $q, httpBuffer, authServiceConfig)
                {
                    return {
                        responseError: function (rejection)
                        {
                            if (rejection.status === 401 && !rejection.config.ignoreAuthModule) {
                                var deferred = $q.defer();
                                if (!authServiceConfig.loginFailureSignaledWith401 || !rejection.config.url.match(authServiceConfig.authUrl)) {
                                    httpBuffer.append(rejection.config, deferred);
                                    if (1 === httpBuffer.getBufferLength()) {
                                        $rootScope.$broadcast('event:auth-credentialsRequired', rejection);
                                    }
                                    $rootScope.$broadcast('event:auth-loginRequired', rejection);
                                    return deferred.promise;
                                } else {
                                    $rootScope.$broadcast('event:auth-loginFailed', rejection);
                                    $rootScope.$broadcast('event:auth-loginRequired', rejection);
                                    return $q.reject(rejection);
                                }

                            } else if (rejection.status === 0) {
                                return $q.defer().promise;
                            }

                            // otherwise, default behaviour
                            return $q.reject(rejection);
                        }
                    };
                }]);
            }]);

    /**
     * Private module, a utility, required internally by 'http-auth-interceptor'.
     */
    angular.module('http-auth-interceptor-buffer', [])

            .factory('httpBuffer', ['$injector', function ($injector)
            {
                /** Holds all the requests, so they can be re-requested in future. */
                var buffer = [];

                /** Service initialized later because of circular dependency problem. */
                var $http;

                function retryHttpRequest(config, deferred)
                {
                    function successCallback(response)
                    {
                        deferred.resolve(response);
                    }

                    function errorCallback(response)
                    {
                        deferred.reject(response);
                    }

                    $http = $http || $injector.get('$http');
                    $http(config).then(successCallback, errorCallback);
                }

                return {
                    /**
                     * Appends HTTP request configuration object with deferred response attached to buffer.
                     */
                    append: function (config, deferred)
                    {
                        buffer.push({
                            config: config,
                            deferred: deferred
                        });
                    },

                    getBufferLength: function ()
                    {
                        return buffer.length;
                    },

                    /**
                     * Abandon or reject (if reason provided) all the buffered requests.
                     */
                    rejectAll: function (reason)
                    {
                        if (reason) {
                            for (var i = 0; i < buffer.length; ++i) {
                                buffer[i].deferred.reject(reason);
                            }
                        }
                        buffer = [];
                    },

                    /**
                     * Retries all the buffered requests clears the buffer.
                     */
                    retryAll: function (updater)
                    {
                        for (var i = 0; i < buffer.length; ++i) {
                            retryHttpRequest(updater(buffer[i].config), buffer[i].deferred);
                        }
                        buffer = [];
                    }
                };
            }]);
})();
