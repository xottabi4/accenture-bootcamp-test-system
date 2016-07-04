
//modular (nemainiit)
var app = angular.module('app', ['ui.bootstrap','countTo', 'ngAnimate']);

//funkcija inputam
app.controller('inputFun', ['$scope', function($scope) {
	      $scope.example = {
		text: 'Enter',
		word: /^\s*\w*\s*$/
	      };
	    }]);



//pgrogress bar
app.controller('ProgressBarCtrl', function ($scope){
$scope.max = 100;
$scope.value = 1;

$scope.increase = function(){

	$scope.$watch($scope, function(){
		if($scope.value===1){
			$scope.dinamyc=1;
			$scope.value++;
}
		else{
		 $scope.dinamyc = $scope.value;
		 $scope.value++;
		
}

});
};

});

//funkcija, kas uzskaita atbildeetus jautaajumus
app.controller('CounterController', function($scope) {
  var incremented = false;
  $scope.number = 0;
  var max = 100;

  $scope.increment = function() {
    if ($scope.number >= max) { return; }
    $scope.number++;
  };
});

//funkcija, lai .html failaa nevajadzeetu katrai formai likt ng-blur
app.controller('blurController', function($scope, $element) {
	$scope.InVar = 0;
	$scope.SelVar = 0;
	$scope.RadVar = 0;

	$element.find('input').on('blur', function() {
	if($scope.InVar===0){    	
	$scope.increment();
	$scope.increase();
	$scope.InVar++;
	}
	});
	$element.find('select').on('blur', function() {
	if($scope.SelVar===0){
	$scope.increment();
	$scope.increase();
	$scope.SelVar++;
	}
	});
	$element.find('radio').on('blur', function() {	   
	if($scope.RadVar===0){	
	$scope.increment();
	$scope.increase();
	$scope.RadVar++;
	}
	});
});


