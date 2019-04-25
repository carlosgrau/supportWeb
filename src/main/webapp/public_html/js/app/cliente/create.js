'use strict';
moduleCliente.controller('clienteCreateController', ['$scope', '$http', '$location', 'toolService', '$routeParams', 'sessionService',
    function ($scope, $http, $location, toolService, $routeParams, sessionService) {

        $scope.ejercicio = sessionService.getEmpresa();
        var host ='http://localhost:8081/';
        $scope.crear = function () {
            var json = {
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
                url: host+'json?ob=cliente&op=create',
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

