'use strict';

var services = angular.module('common.user.service', []);

services.factory('userService', function(Restangular, $cookieStore, authStore) {
    var userResource = Restangular.all("secure/users");
    var authResource = Restangular.all("public/auth");
    
    return {
        authenticate: function(username, password) {
            return authResource.customPOST(
                    $.param({username: username, password: password}),
                    'authenticate',
                    {},
                    {'Content-Type': "application/x-www-form-urlencoded; charset=UTF-8"}
            );
        },
        logout: function() {
            authStore.clean();
            $cookieStore.remove('authToken');
        },
        isLoggedIn: function() {
            return (authStore.isLoggedIn());
        },
        register: function(newUser) {
            return authResource.customPOST(
                    newUser,
                    'register',
                    {},
                    {'Content-Type': "application/json; charset=UTF-8"}
            );
        },
        getUser: function() {
            return userResource.get("me");
        },
        setUser: function(user) {
            this.user = user;
        },
        remove: function(id) {
            return userResource.remove({id: id});
        },
        save: function(user) {
            return userResource.post(user);
        },
    };

});
    