(function() {
    'use strict';

    angular.module('ng-spring-app')

            .controller('SettingCtrl', ['$scope', 'settingsService',
                function($scope, settingsService) {
                    
                    settingsService.getSettings().then(
                            function(data) {
                               $scope.settings = data; 
                            });

                    
                    $scope.save = function() {
                        settingsService.save($scope.settings)
                                .then(saveSuccess, saveFail);
                    }

                    var saveSuccess = function(data) {
                       
                    };

                    var saveFail = function(message) {
                        $scope.loginError = 'Login failed: ' + message.data;
                    };
                }]);
})();
