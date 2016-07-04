var loginApp = angular.module("App", []);

$scope.newPage = function (){
	location.href = 'views/tests.html';
	$location.path( 'views/tests.html' );	
};

loginApp.directive( 'goClick', function ( $location ) {
  return function ( scope, element, attrs ) {
    var path;

    attrs.$observe( 'goClick', function (val) {
      path = val;
    });

    element.bind( 'click', function () {
      scope.$apply( function () {
        $location.path( path );
      });
    });
  };
});
