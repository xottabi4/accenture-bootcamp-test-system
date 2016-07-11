'use strict';

myapp.controller('LoginController', function($rootScope, $scope, AuthSharedService) {
    $scope.login = function() {
        $rootScope.authenticationError = false;
        AuthSharedService.login($scope.email, $scope.password);
    }
}).controller('LogoutController', function(AuthSharedService) {
    AuthSharedService.logout();
}).controller('HomeController', function($scope, Session) {
    $scope.email = Session.email;
}).controller("RecruiterController", function($scope, Session) {
    $scope.email = Session.email;
}).controller("GraderController", function($scope, Session) {
    $scope.email = Session.email;
}).controller("TehnicalController", function($scope, $routeParams) {
    $scope.param = $routeParams.param;
    $scope.message = "This is tehnical test";
}).controller("EngilshController", function($scope, $routeParams) {
    $scope.param = $routeParams.param;
    $scope.message = "This is english test";
}).controller("Engilsh_test_Controlle", function($scope, $http) {
    $http.get('http://localhost:8080/applicant/get-questions', {
        params: {
            testType: 'Language'
        }
    }).success(function(data) {
        $scope.questions = data.questions;
        $scope.option = $scope.questions.option;
        console.log($scope.questions.option);
    })
}).controller("Tehnica_test_lController", function($scope, $http) {
    $http.get('http://localhost:8080/applicant/get-questions', {
        params: {
            testType: 'Technical'
        }
    }).success(function(data) {

        $scope.questions = data.questions;
        $scope.option = $scope.questions.option;
        //console.log($scope.questions.option);
    })
}).controller('getAnswers', function($scope, $http) {
    $("#getAnswers").click(function() {
        var Answers = {};
        $("textarea").each(function(index) {

            //console.log($( this ).val() );

            var answertext = $(this).val();
            var answerID = index + 1;
            var optionChecked = [];

            //var Answers1={"answers":[{"id":answerID ,"Answertext": answertext, "optionChecked":optionChecked}]};
            //console.log(Answers1);

            var checkID;

            $("input").each(function(index) {
                //console.log($( this ).val() );

                checkID = 'c-' + index;
                var checked = document.getElementById(checkID);
                if (checked.checked) {
                    optionChecked[index] = index + 1;
                    //alert("checked") ;
                } else {
                    alert("You didn't check it!");
                }
                console.log(checkID);

                var answers = [];
                answers[index] = {
                    "id": answerID,
                    "Answertext": answertext,
                    "optionChecked": optionChecked
                };

                Answers = {
                    "answers": answers
                };
                console.log(Answers);

            })
            $http({
                    url: "http://localhost:8080/applicant/-Answers",
                    method: 'POST',
                    params: {
                        testType: 'Technical'
                    },
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                            /*'Accept': 'text/json'*/
                    }
                }) //.success().error();
        })
    })
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
}).controller('TimerControllerLanguageTest', function($scope, $sessionStorage, $window, $location, $interval, $http) {
    $http.get('http://localhost:8080/applicant/get-questions', {
        params: {
            testType: 'Language'
        }
    }).success(function(data) {
        $scope.testDurationLanguage = data.duration;
        if (sessionStorage.getItem("autosave") != null) { //
            $scope.dateMilisec = sessionStorage.getItem("autosave");
            $scope.newDate = new Date(+sessionStorage.getItem("autosave"));
        } else {

            $scope.myDate = new Date();
            $scope.newDate = $scope.myDate.getTime() + $scope.testDurationLanguage;
            sessionStorage.setItem("autosave", $scope.newDate);
        }
    })


    $interval(callAtInterval, 5000);

    function callAtInterval() {
        console.log("Interval occurred");
        if (sessionStorage.getItem("autosave") < Date.now()) {
            $window.location.href = 'http://localhost:8080/#/applicant';
        }
    }

}).controller('TimerControllerTechnicalTest', function($scope, $sessionStorage, $window, $location, $interval, $http) {

    $http.get('http://localhost:8080/applicant/get-questions', {
        params: {
            testType: 'Technical'
        }
    }).success(function(data) {
        $scope.testDurationTechnical = data.duration;
        if (sessionStorage.getItem("autosave") != null) { //
            $scope.dateMilisec = sessionStorage.getItem("autosave");
            $scope.newDate = new Date(+sessionStorage.getItem("autosave"))

        } else {
            $scope.myDate = new Date();
            $scope.newDate = $scope.myDate.getTime() + $scope.testDurationTechnical;
            sessionStorage.setItem("autosave", $scope.newDate);
        }
    })


    $interval(callAtInterval, 5000);

    function callAtInterval() {
        console.log("Interval occurred");
        if (sessionStorage.getItem("autosave") < Date.now()) {
            $window.location.href = 'http://localhost:8080/#/applicant';
        }
    }


});
