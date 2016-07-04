'use strict';

var myapp = angular.module('myApp', [ 'ngResource', 'ngRoute', 'ngAnimate',
		'http-auth-interceptor' ]);

myapp.constant('USER_ROLES', {
	all : '*',
	grader : 'grader',
	applicant : 'applicant',
	reviewer : 'reviewer'
});

myapp.config(function($routeProvider, USER_ROLES) {
	$routeProvider.when("/applicant", {
		templateUrl : "views/applicant/test_form.html",
		controller : 'HomeController',
		access : {
			loginRequired : true,
			authorizedRoles : [ USER_ROLES.applicant ]
		}
	}).when("/applicant/english-test", {
		templateUrl : "views/applicant/test_form.html",
		controller : 'HomeController',
		access : {
			loginRequired : true,
			authorizedRoles : [ USER_ROLES.applicant ]
		}

	}).when("/applicant/tehnical-test", {
		templateUrl : 'test_form.html',
		controller : 'TehnicalController',
		access : {
			loginRequired : true,
			authorizedRoles : [ USER_ROLES.applicant ]
		}

	}).when("/applicant/english-test", {
		templateUrl : "test_form.html",
		controller : 'EngilshController',
		access : {
			loginRequired : true,
			authorizedRoles : [ USER_ROLES.applicant ]
		}
	}).when("/grader", {
		templateUrl : "views/grader/test.html",
		controller : 'HomeController',
		access : {
			loginRequired : true,
			authorizedRoles : [ USER_ROLES.grader ]
		}
	}).when("/reviewer", {
		templateUrl : "views/reviewer/test.html",
		controller : 'HomeController',
		access : {
			loginRequired : true,
			authorizedRoles : [ USER_ROLES.reviewer ]
		}
	}).when('/', {
		redirectTo : '/applicant'
	}).when('/login', {
		templateUrl : 'views/login.html',
		controller : 'LoginController',
		access : {
			loginRequired : false,
			authorizedRoles : [ USER_ROLES.all ]
		}
	}).when('/loading', {
		templateUrl : 'views/loading.html',
		access : {
			loginRequired : false,
			authorizedRoles : [ USER_ROLES.all ]
		}
	}).when("/logout", {
		template : " ",
		controller : "LogoutController",
		access : {
			loginRequired : false,
			authorizedRoles : [ USER_ROLES.all ]
		}
	}).when("/error/:code", {
		templateUrl : "views/error.html",
		controller : "ErrorController",
		access : {
			loginRequired : false,
			authorizedRoles : [ USER_ROLES.all ]
		}
	}).otherwise({
		redirectTo : '/error/404',
		access : {
			loginRequired : false,
			authorizedRoles : [ USER_ROLES.all ]
		}
	});
});

myapp
		.run(function($rootScope, $location, $http, AuthSharedService, Session,
				USER_ROLES, $q, $timeout) {
			$rootScope
					.$on(
							'$routeChangeStart',
							function(event, next) {
								if (next.originalPath === "/login"
										&& $rootScope.authenticated) {
									event.preventDefault();
								} else if (next.access
										&& next.access.loginRequired
										&& !$rootScope.authenticated) {
									event.preventDefault();
									$rootScope.$broadcast(
											"event:auth-loginRequired", {});
								} else if (next.access
										&& !AuthSharedService
												.isAuthorized(next.access.authorizedRoles)) {
									event.preventDefault();
									$rootScope.$broadcast(
											"event:auth-forbidden", {});
								}
							});
			// do i need it?
			// $rootScope.$on('$routeChangeSuccess', function(scope, next,
			// current) {
			// $rootScope.$evalAsync(function() {
			// $.material.init();
			// });
			// });

			// Call when the the client is confirmed
			$rootScope
					.$on(
							'event:auth-loginConfirmed',
							function(event, data) {
								console.log('login confirmed start ' + data);
								$rootScope.loadingAccount = false;
								var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl
										: "/");
								document.getElementById("navbar").className = "navbar navbar-inverse";
								// angular.forEach(Session.roles,
								// function(value, key) {
								// if (value.name == USER_ROLES.applicant) {
								// nextLocation = ($rootScope.requestedUrl ?
								// $rootScope.requestedUrl :
								// "/applicant");
								// break;
								// } else if (value.name == USER_ROLES.grader) {
								// nextLocation = ($rootScope.requestedUrl ?
								// $rootScope.requestedUrl :
								// "/grader");
								// break;
								// } else if (value.name == USER_ROLES.reviewer)
								// {
								// nextLocation = ($rootScope.requestedUrl ?
								// $rootScope.requestedUrl :
								// "/reviewer");
								// break;
								// }
								// }
								var delay = ($location.path() === "/loading" ? 1000
										: 0);

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
				$location.path('/login').replace();
				document.getElementById("navbar").className = "navbar navbar-inverse hidden-xs hidden-sm hidden-md hidden-lg";
			});

			// Get already authenticated user account
			AuthSharedService.getAccount();

		});
