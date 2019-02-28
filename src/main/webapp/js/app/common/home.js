'use strict'

moduleCommon.controller('homeController', ['$scope', '$location', 'toolService', 'sessionService', '$http',
    function ($scope, $location, toolService, sessionService, $http) {

        $http({
            method: 'GET',
            url: '/json?ob=usuario&op=check'
        }).then(function (response) {
            if (response.status === 200) {
                if (sessionService.isSessionActive) {
                    $scope.usuariologeado = sessionService.getUserName();
                    if ($scope.usuariologeado === "") {
                        $scope.ocultar = false;
                    } else {
                        $scope.ocultar = true;
                    }
                }
            } else if (response.status === 401 || response.status === 500) {
                if (sessionService.setSessionInactive) {
                    $scope.usuariologeado = sessionService.getUserName();
                    if ($scope.usuariologeado === "") {
                        $scope.ocultar = true;
                    } else {
                        $scope.ocultar = false;
                    }
                }
            }
        });
        $scope.validar = function () {
            $scope.ob = "usuario";
            $http({
                method: 'GET',
                url: 'json?ob=' + $scope.ob + '&op=login&user=' + $scope.login + '&pass=' + forge_sha256($scope.password)
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDataUsuarios = response.data.message;
                if (response.status === 200) {
                    if (response.data.status === 401) {
                        $scope.mensaje = false;
                        $scope.mensajeError = true;
                    } else {
                        $scope.mensajeError = false;
                        /* sessionService.setSessionActive();
                         sessionService.setUserName(response.data.message.nombre + ' ' + response.data.message.ape1);
                         sessionService.setUserId(response.data.message.id);
                         $scope.idUsuariologeado= sessionService.getUserId();
                         $scope.usuariologeado = sessionService.getUserName();
                         sessionService.setTipoUserId(response.data.message.obj_tipoUsuario.id);*/
                        $scope.mensaje = true;
                    }
                }
                $location.url('/empresa');
            }, function (response) {
                $scope.mensajeError = true;
                $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
                $scope.status = response.status;
            });

        };

        $scope.ruta = $location.path();

        $scope.isActive = toolService.isActive;

    }
]);