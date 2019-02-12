'use strict';
moduleCliente.controller('clienteEditController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 'sessionService',
    function ($scope, $http, $location, toolService, $routeParams, sessionService) {

        $scope.ejercicio = sessionService.getEmpresa();
        var id = $routeParams.id;
        $http({
            method: 'GET',
            withCredentials: true,
            url: 'json?ob=cliente&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
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
                direccion: $scope.ajaxDatoUsuario.direccion,
                email: $scope.ajaxDatoUsuario.email,
                empresa: sessionService.getEmpresa(),
                nif: $scope.ajaxDatoUsuario.nif,
                nombre: $scope.ajaxDatoUsuario.nombre,
                razonsocial: $scope.ajaxDatoUsuario.razonsocial,
                telefono: $scope.ajaxDatoUsuario.telefono
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
                $scope.mensajeError = true;
            });
        };




        $scope.isActive = toolService.isActive;
    }]);

