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
<<<<<<< HEAD
}).controller ("json", function ($scope, $http){
	//$scope.questions = [];
    $http.get('js/test_questions.json').success(function(data) {
    	
        $scope.questions = data.test;
        console.log($scope.questions[0].answer_test);
    })
    
=======
	
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
>>>>>>> debfceaab54a028bc3731cdd5d25fb0aa889aed0

    
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
}).controller('TimerController', function($scope,$sessionStorage, $window, $location, $interval){
	
	$interval(callAtInterval, 5000);
   // $scope.$watch('dateMilisec', function(){
	if(sessionStorage.getItem("autosave")!=null){	//
	   	$scope.dateMilisec = sessionStorage.getItem("autosave");		
		$scope.newDate = new Date(+sessionStorage.getItem("autosave"));
		console.log(Date.now());

	}
	else{
			$scope.setTestTime;
       	    $scope.myDate = new Date();
	    $scope.newDate = $scope.myDate.getTime()+ 3600000;	
	    sessionStorage.setItem("autosave", $scope.newDate);
	   // console.log(sessionStorage.getItem("autosave"));
   	}
  //  });
	function callAtInterval() {
   	console.log("Interval occurred");
	if (sessionStorage.getItem("autosave") < Date.now()){
		$window.location.href = 'http://localhost:8080/#/applicant';
        }
}

});
