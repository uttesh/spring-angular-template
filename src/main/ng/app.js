(function() {
    'use strict';
    angular.module('ng-spring-app', [
        'ngRoute',
        'ngMaterial',
        'ui.router',
        'ngCookies',
        'restangular',
        'angular-jwt',
        'common.authStore',
        'common.auth.service',
        'common.user.service',
        'common.settings.service'])

            .config(function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider, $provide,
                    RestangularProvider) {
                        
                $urlRouterProvider.otherwise("/");
                $locationProvider.html5Mode(false);
                var templatePath = contextPath + '/' + 'static/template';

                $stateProvider
                        .state('Login', {
                            url: '/login',
                            controller: 'LoginCtrl',
                            templateUrl: templatePath + '/login/login.html'
                        })
                        .state('Home', {
                            url: '/',
                            controller: 'HomeCtrl',
                            templateUrl: templatePath + '/home/home.html'
                        });
                        
                $httpProvider.defaults.transformResponse.push(function(responseData) {
                    convertDateStringsToDates(responseData);
                    return responseData;
                });

                /* Register error provider that shows message on failed requests or redirects to login page on
                 * unauthenticated requests */
                $httpProvider.interceptors.push(function($q, $rootScope, $location) {
                    return {
                        responseError: function(rejection) {
                            var status = rejection.status;
                            var config = rejection.config;
                            var method = config.method;
                            var url = config.url;

                            if (status === 401) {
                                $location.path("/login");
                            } else {
                                $rootScope.error = method + " on " + url + " failed with status " + status;
                            }

                            return $q.reject(rejection);
                        }
                    };
                });

                $httpProvider.interceptors.push(function($q, $rootScope, $location) {
                    return {
                        request: function(config) {
                            var isRestCall = config.url.indexOf('/rest/') === 0;
                            if (isRestCall) {
                                console.log('**** Please use Restangular for http requests ****' + config.url);
                            }
                            return config;
                        }
                    };
                });

                RestangularProvider.setBaseUrl(contextPath + '/rest/');

            })

            .run(['$rootScope', '$location', '$cookieStore', 'authStore', 'Restangular', 'userService', 'settingsService', '$http', '$stateParams', 'jwtHelper',
                function($rootScope, $location, $cookieStore, authStore, Restangular, userService, settingsService, $http, $stateParams, jwtHelper) {

                    $rootScope.alert = function(text) {
                        console.log('alert: ' + text);
                    };

                    $rootScope.$on('$viewContentLoaded', function() {
                        delete $rootScope.error;
                    });
                    $rootScope.$on('$stateChangeStart',
                            function(event, toState, toParams, fromState, fromParams) {
                                if (!$cookieStore.get('authToken') && (toState.url !== '/login')) {
                                    $location.path("/login");
                                    // event.preventDefault();
                                } else if ($cookieStore.get('authToken') && (toState.url === '/login')) {
                                    event.preventDefault();
                                }
                            });
                    var authToken = authStore.getToken();
                    if (!authToken) {
                        authToken = $cookieStore.get('authToken');
                        if (!authToken) {
                            $location.path("/login");
                        } else {
                            cacheToken(Restangular, authStore, authToken, $rootScope);
                        }
                    } else {
                        cacheToken(Restangular, authStore, authToken, $rootScope);
                    }

                    Restangular.setErrorInterceptor(function(response) {
                        if ((response.status === 401) || (response.status === 403)) {
                            $location.path("/login");
                            return false; // error handled
                        }
                        $rootScope.isloggedIn = true;
                        return true; // error not handled
                    });
                    $rootScope.initialized = true;
                }])
})();

function cacheToken(Restangular, authStore, token, $rootScope) {
    authStore.setToken(token);
    $rootScope.isloggedIn = true;
    Restangular.setDefaultHeaders({'TestClient-Auth-Token': authStore.getToken()});
}

function convertDateStringsToDates(input) {
    var regexIso8601 = /^(\d{4}|\+\d{6})(?:-(\d{2})(?:-(\d{2})(?:T(\d{2}):(\d{2}):(\d{2})\.(\d{1,})(Z|([\-+])(\d{2}):(\d{2}))?)?)?)?$/;
    if (typeof input !== "object")
        return input;

    for (var key in input) {
        if (!input.hasOwnProperty(key))
            continue;

        var value = input[key];
        var match;
        // Check for string properties which look like dates.
        if (typeof value === "string" && (match === value.match(regexIso8601))) {
            var milliseconds = Date.parse(match[0]);
            if (!isNaN(milliseconds)) {
                input[key] = new Date(milliseconds);
            }
        } else if (typeof value === "object") {
            // Recurse into object
            convertDateStringsToDates(value);
        }
    }
}

