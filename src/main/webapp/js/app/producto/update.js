'use strict';
moduleProducto.controller('productoEditController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 'sessionService',
    function ($scope, $http, $location, toolService, $routeParams, sessionService) {

        $scope.ejercicio = sessionService.getEmpresa();
        var id = $routeParams.id;

        $http({
            method: 'GET',
            withCredentials: true,
            url: 'json?ob=producto&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDatoUsuario = response.data.message;
        }, function (response) {
            $scope.ajaxDatoUsuario = response.data.message || 'Request failed';
            $scope.status = response.status;
        });


        $scope.crear = function () {
            var json = {
                id: $scope.ajaxDatoUsuario.id,
                codigo: $scope.ajaxDatoUsuario.codigo,
                descripcion: $scope.ajaxDatoUsuario.direccion,
                empresa: sessionService.getEmpresa(),
                nif: $scope.ajaxDatoUsuario.nif,
                pvp1: $scope.ajaxDatoUsuario.pvp1,
                pvp2: $scope.ajaxDatoUsuario.pvp2,
                pvp3: $scope.ajaxDatoUsuario.pvp3
            };
            $http({
                method: 'GET',
                withCredentials: true,
                url: '/json?ob=cliente&op=update',
                params: {json: JSON.stringify(json)}
            }).then(function (response) {
                $scope.status = response.status;
                $scope.mensaje = true;
            }, function (response) {
                $scope.ajaxDataUsuario = response.data.message || 'Request failed';
                $scope.status = response.status;
            });
        };




        $scope.isActive = toolService.isActive;
    }]);

