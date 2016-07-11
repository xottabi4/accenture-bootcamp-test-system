
var app = angular.module('app', ['ui.bootstrap', 'ngAnimate', 'ngMaterial', 'material.svgAssetsCache', 'ngMessages']);


app.controller('AppCtrl', function($scope) {
    $scope.items = ["a","b",3,4,5,6];
      $scope.selected = [];

      $scope.toggle = function (item, list) {
        var idx = list.indexOf(item);
	//what happens if we uncheck 'pretty checkbox'
        if (idx > -1) {
          list.splice(idx, 1);  //take out from list

        }
        else {
          list.push(item);

        }
	
      };

      //function with list with selected items
      $scope.exists = function (item, list) {
	
	return list.indexOf(item) > -1;
      };

	

});
