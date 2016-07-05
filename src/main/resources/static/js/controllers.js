'use strict';

myapp.controller('LoginController',
    function($rootScope, $scope, AuthSharedService) {
        $scope.login = function() {
            $rootScope.authenticationError = false;
            AuthSharedService.login($scope.email, $scope.password);
        }
    }).controller('LogoutController', function(AuthSharedService) {
    AuthSharedService.logout();
}).controller('HomeController', function($scope, Session) {
    $scope.email = Session.email;
}).controller("TehnicalController", function($scope) {
    $scope.message = "This is tehnical test";
}).controller("EngilshController", function($scope) {
    $scope.message = "This is english test";
}).controller('ErrorController', function($scope, $routeParams) {
    $scope.code = $routeParams.code;
    switch ($scope.code) {
        case "403":
            $scope.message = "Oops! you have come to unauthorised page."
            break;
        case "404":
            $scope.message = "Page not found."
            break;
        default:
            $scope.code = 500;
            $scope.message = "Oops! unexpected error"
    }
});
