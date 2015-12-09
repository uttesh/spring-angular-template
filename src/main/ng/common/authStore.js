'use strict';

var authStoreProvider = angular.module('common.authStore', []);

authStoreProvider.provider('authStore', function() {
    this.token;
    this.user;

    this.$get = function() {
        return {
            getToken: function() {
                return this.token;
            },
            setToken: function(authToken) {
                this.token = authToken;
            },
            getUser: function() {
                return this.user;
            },
            setUser: function(authUser) {
                this.user = authUser;
            },
            isLoggedIn: function() {
                if (!this.token || !this.user) return false;
                var tokenParts = this.token.split(':');
                return (tokenParts[0] === this.user.username);
            },
            hasRole: function(role) {
                if (!this.user) return false;
                return _.contains(this.user.roles, role);
            },
            clean: function() {
                delete this.token;
                delete this.user;
            }
        };
    };

});
