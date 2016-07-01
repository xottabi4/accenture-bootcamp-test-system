
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
	

	$element.find('input').on('blur', function() {
    	$scope.increment();
	$scope.increase();
	});
	$element.find('select').on('blur', function() {
	    $scope.increment();
	$scope.increase();
	});
	$element.find('radio').on('blur', function() {	   
	 $scope.increment();
	$scope.increase();
	});
});


