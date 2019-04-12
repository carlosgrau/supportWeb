'use strict'

SuportWeb4All.config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode(true);
    }]);
SuportWeb4All.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
    }]);