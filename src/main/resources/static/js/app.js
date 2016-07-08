'use strict';

var myapp = angular.module('myApp', ['ngResource', 'ngRoute', 'ngAnimate',
    'http-auth-interceptor'
]);

myapp.constant('USER_ROLES', {
    all: '*',
    grader: 'grader',
    applicant: 'applicant',
    recruiter: 'recruiter'
});

myapp.config(function($routeProvider, USER_ROLES) {
    $routeProvider.when("/applicant", {
        templateUrl: "views/applicant/home.html",
        controller: 'HomeController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.applicant]
        }
    }).when("/applicant/tehnical-test", {
        templateUrl: 'views/applicant/test_form.html',
        controller: 'Tehnica_test_lController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.applicant]
        }
    }).when("/applicant/english-test", {
        templateUrl: "views/applicant/test_form.html",
        controller: 'Engilsh_test_Controlle',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.applicant]
        }
    }).when("/grader", {
        templateUrl: "views/grader/test.html",
        controller: 'GraderController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.grader]
        }
    }).when("/recruiter", {
        templateUrl: "views/recruiter/test.html",
        controller: 'RecruiterController',
        access: {
            loginRequired: true,
            authorizedRoles: [USER_ROLES.recruiter]
        }
    }).when('/', {
        resolve: {
            "check": function($location, Session) {
                if (Session.roles == USER_ROLES.applicant) {
                    $location.path('/applicant');
                } else if (Session.roles == USER_ROLES.grader) {
                    $location.path('/grader');
                } else if (Session.roles == USER_ROLES.recruiter) {
                    $location.path('/recruiter');
                } else {
                    $location.path('/error/404');
                }
            }
        }
    }).when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/loading', {
        templateUrl: 'views/loading.html',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/logout", {
        template: " ",
        controller: "LogoutController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/error/:code", {
        templateUrl: "views/error.html",
        controller: "ErrorController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).otherwise({
        redirectTo: '/error/404',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    });
});

myapp.run(function($rootScope, $location, $http, AuthSharedService, Session,
    USER_ROLES, $q, $timeout) {
    $rootScope.$on('$routeChangeStart', function(event, next) {
        if (next.originalPath === "/login" && $rootScope.authenticated) {
            event.preventDefault();
        } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-loginRequired", {});
        } else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-forbidden", {});
        }
    });
    // do i need it?
    // $rootScope.$on('$routeChangeSuccess', function(scope, next,
    //     current) {
    //     $rootScope.$evalAsync(function() {
    //         $.material.init();
    //     });
    // });

    // Call when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function(event, data) {
        $rootScope.loadingAccount = false;
        document.getElementById("navbar").className =
            document.getElementById("navbar").className.replace(/(?:^|\s)hide(?!\S)/g, '');
        var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/");
        var delay = ($location.path() === "/loading" ? 1500 : 0);

        $timeout(function() {
            Session.create(data);
            $rootScope.account = Session;
            $rootScope.authenticated = true;
            $location.path(nextLocation).replace();
        }, delay);

    });

    // Call when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function(event, data) {
        if ($rootScope.loadingAccount && data.status !== 401) {
            $rootScope.requestedUrl = $location.path()
            $location.path('/loading');
        } else {
            Session.invalidate();
            $rootScope.authenticated = false;
            $rootScope.loadingAccount = false;
            $location.path('/login');
        }
    });

    // Call when the 403 response is returned by the server
    $rootScope.$on('event:auth-forbidden', function(rejection) {
        $rootScope.$evalAsync(function() {
            $location.path('/error/403').replace();
        });
    });

    // Call when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function() {
        document.getElementById("navbar").className += " hide ";
        $location.path('/login').replace();
    });

    // Get already authenticated user account
    AuthSharedService.getAccount();
});
