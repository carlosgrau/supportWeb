'use strict'

moduleCommon.controller('homeController', ['$scope', '$location', 'toolService', 'sessionService', '$http',
    function ($scope, $location, toolService, sessionService, $http) {
        var host = 'http://localhost:8081/';
        $http({
            method: 'GET',
            url: host + 'json?ob=usuario&op=check'
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
        })
        $scope.validar = function () {
            $scope.ob = "usuario";
            $http({
                method: 'GET',
                url: host + 'json?ob=' + $scope.ob + '&op=login&user=' + $scope.login + '&pass=' + forge_sha256($scope.password)
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDataUsuarios = response.data.message;
                if (response.status === 200) {
                    if (response.data.status === 401) {
                        $scope.mensaje = false;
                        $scope.mensajeError = true;
                    } else {
                        $scope.mensajeError = false;
                        $scope.mensaje = true;
                        swal({
                            icon: 'success',
                            title: "Usuario logueado correctamente!"
                        });
                    }
                }else {
                    swal({
                        icon: 'error',
                        title: "Ha ocurrido un error en el presupuesto!"
                    });
                }
                $location.url('/empresa');
            }, function (response) {
                $scope.mensajeError = true;
                $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
                $scope.status = response.status;
                swal({
                    icon: 'error',
                    title: "Ha ocurrido un error en el presupuesto!"
                });
            });

        };
        $scope.ruta = $location.path();

        $scope.isActive = toolService.isActive;

    }
]);