'use strict';

var services = angular.module('common.auth.service', []);

services.factory('authService', function(Restangular) {
    var userResource = Restangular.all("api");

    return {
        
        register: function(newApplication) {
            return userResource.customPOST(
                    newApplication,
                    'register',
                    {},
                    {'Content-Type': "application/json; charset=UTF-8"}
            );
        },       
        save: function(user) {
            return userResource.post(user);
        },
    };

});
    