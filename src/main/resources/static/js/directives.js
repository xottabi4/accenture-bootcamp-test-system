myapp.directive('access', [
    'AuthSharedService',
    function(AuthSharedService) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var roles = attrs.access.split(',');
                if (roles.length > 0) {
                    if (AuthSharedService.isAuthorized(roles)) {
                        element.addClass('hide');
                    } else {
                    	element.removeClass('hide');
                    }
                }
            }
        };
    }
]);
