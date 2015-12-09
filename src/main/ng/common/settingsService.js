'use strict';

var services = angular.module('common.settings.service', []);

services.factory('settingsService', function(Restangular) {
    var settingsResource = Restangular.all("secure/settings");
    return {
        save: function(settings) {
            return settingsResource.post(settings);
        },getSettings: function() {
            return settingsResource.get("app");
        }
    };

});
    