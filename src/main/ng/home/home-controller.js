(function() {
    'use strict';

    angular.module('ng-spring-app')

            .controller('HomeCtrl', ['$scope', '$location', 'userService', '$rootScope', '$cookieStore',
                function($scope, $location, userService, $rootScope, $cookieStore) {
                    $scope.user = {'firstName':'Uttesh','lastName':'Kumar'};
                    console.log('HomeCtrl ');
                    userService.getUser().then(function(data) {
                        console.log('data : '+JSON.stringify(data));
                        $scope.user = data;
                    });
                    $scope.logout = function() {
                        userService.logout();
                        $cookieStore.remove('authToken');
                        $rootScope.isloggedIn = false;
                        $location.path('/login');
                    };
                }])

})();

