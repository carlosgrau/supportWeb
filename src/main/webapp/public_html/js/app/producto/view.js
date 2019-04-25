'use strict';

moduleProducto.controller('productoViewController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 'sessionService',
    function ($scope, $http, $location, toolService, $routeParams, sessionService) {
        $scope.ejercicio = sessionService.getEmpresa();
        var host = 'http://localhost:8081/';
        $http({
            method: 'GET',
            url: host + 'json?ob=producto&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataProducto = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataProducto = response.data.message || 'Request failed';
        });

        $scope.isActive = toolService.isActive;



    }



]);