var testApp = angular.module("App", ["ngRoute"]);
	testApp.config(function($routeProvider) {
		$routeProvider
			.when("/Tehnicaltest", {
				templateUrl : "tehnical_test.html"
				controller: 'TehnicalController'
	    		})

			.when("/Englishtest", {
				templateUrl : "english_test.html"
				controller: 'EngilshController'
	    		})
	 		.otherwise({
                    		redirectTo: '/'
                	});
		});

testApp.controller("TehnicalController", function($scope, $routeParams) {
        $scope.param = $routeParams.param;
	$scope.message ="This is tehnical test";
    });


testApp.controller("EngilshController", function($scope, $routeParams) {
        $scope.param = $routeParams.param;
	$scope.message ="This is english test";
    });

