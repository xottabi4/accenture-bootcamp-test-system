'use strict';

myapp.service('Session', function() {
    this.create = function(data) {
        this.id = data.id;
        this.email = data.email;
        this.name = data.name;
        this.surname = data.surname;
        this.roles = [];
        angular.forEach(data.roles, function(value, key) {
            this.push(value.name);
        }, this.roles);
    };
    this.invalidate = function() {
        this.id = null;
        this.email = null;
        this.name = null;
        this.surname = null;
        this.roles = null;
    };
    return this;
});

myapp.service('AuthSharedService', function($rootScope, $http, $resource,
    authService, Session) {
    return {
        login: function(email, password) {
            var config = {
                params: {
                    email: email,
                    security_code: password
                },
                ignoreAuthModule: 'ignoreAuthModule'
            };
            $http.post('authenticate', '', config).success(
                function(data, status, headers, config) {
                    authService.loginConfirmed(data);
                }).error(function(data, status, headers, config) {
                $rootScope.authenticationError = true;
                Session.invalidate();
            });
        },
        getAccount: function() {
            $rootScope.loadingAccount = true;
            $http.get('security/account')
                .then(function(response) {
                    authService.loginConfirmed(response.data);
                });
        },
        isAuthorized: function(authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                if (authorizedRoles == '*') {
                    return true;
                }
                authorizedRoles = [authorizedRoles];
            }
            var isAuthorized = false;
            angular.forEach(authorizedRoles, function(authorizedRole) {
                var authorized = (!!Session.email && Session.roles
                    .indexOf(authorizedRole) !== -1);
                if (authorized || authorizedRole == '*') {
                    isAuthorized = true;
                }
            });
            return isAuthorized;
        },
        logout: function() {
            $rootScope.authenticationError = false;
            $rootScope.authenticated = false;
            $rootScope.account = null;
            $http.get('logout');
            Session.invalidate();
            authService.loginCancelled();
        }
    };
});

// myapp.service('HomeService', function($log, $resource) {
//     return {
//         getName: function() {
//             var userResource = $resource('resources/json/techno.json', {}, {
//                 query: {
//                     method: 'GET',
//                     params: {},
//                     isArray: true
//                 }
//             });
//             return userResource.query();
//         }
//     }
// });
//
//
// myapp.service('UsersService', function ($log, $resource) {
// return {
// getAll: function () {
// var userResource = $resource('users', {}, {
// query: {method: 'GET', params: {}, isArray: true}
// });
// return userResource.query();
// }
// }
// });
