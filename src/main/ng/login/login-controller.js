(function() {
    'use strict';

    angular.module('ng-spring-app')

            .controller('LoginCtrl', ['$scope','$rootScope', '$cookieStore','$location',
                function($scope,$rootScope, $cookieStore,$location) {

                $scope.login = function() {
                        $cookieStore.put('authToken','test');
                        $rootScope.isloggedIn = true;
                        $location.path('/home');
                    };
                    
                }]);
})();
