'use strict';

moduleCliente.controller('clienteViewController', ['$scope', '$http', 'toolService', '$routeParams', 'sessionService',
    function ($scope, $http, toolService, $routeParams, sessionService) {
        $http({
            method: 'GET',
            withCredentials: true,
            url: 'json?ob=cliente&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message;
        }, function (response) {
            $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $scope.isActive = toolService.isActive;


    }]);