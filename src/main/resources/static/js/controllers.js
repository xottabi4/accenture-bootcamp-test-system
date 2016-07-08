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
}).controller("TehnicalController", function($scope, $routeParams) {
	$scope.param = $routeParams.param;
	$scope.message = "This is tehnical test";
}).controller("EngilshController", function($scope, $routeParams) {
	$scope.param = $routeParams.param;
	$scope.message = "This is english test";
	
}).controller ("Engilsh_test_Controlle", function ($scope ,$http){
	
	$http.get('http://localhost:8080/applicant/get-questions',{params: {testType: 'Language'}}).success(function(data) {

		$scope.questions = data.questions;
		$scope.option=$scope.questions.option;
		console.log($scope.questions.option );
		
	
	})
	
	
}).controller ("Tehnica_test_lController", function ($scope ,$http){
	
	$http.get('http://localhost:8080/applicant/get-questions',{params: {testType: 'Technical'}}).success(function(data) {

		$scope.questions = data.questions;
		$scope.option=$scope.questions.option;
		console.log($scope.questions.option );
		
	
	})
//	var result= 
		//questionsService.getquestions("Language");
	//$scope.Languagequestions=Test.questions;
	//console.log("       "+Test.questions);
//	$scope.Technicalquestions=questionsService.myFunc("Technical");
//    console.log($scope.Languagequestions);
//    console.log($scope.Technicalquestions);

    
    
    
    
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
