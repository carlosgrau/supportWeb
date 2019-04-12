'use strict';

moduleCliente.controller('clienteViewController', ['$scope', '$http', 'toolService', '$routeParams', 'sessionService', '$location',
    function ($scope, $http, toolService, $routeParams, sessionService, $location) {
        var host = 'http://localhost:8081/';
        $http({
            method: 'GET',
            withCredentials: true,
            url: host + 'json?ob=cliente&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message;
        }, function (response) {
            $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $scope.isActive = toolService.isActive;

        $scope.atras = function () {
            window.history.back();
        };
        $scope.editar = function (id) {
            $location.path("/cliente/update/" + id);
        };

        $scope.borrar = function (id) {
            swal({
                title: "Estás seguro que quieres borrar este registro?",
                text: "Una vez borres este registro no podrás recuperarlo.",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        $http({
                            method: 'GET',
                            withCredentials: true,
                            url: host + 'json?ob=cliente&op=remove&ejercicio=' + sessionService.getEmpresa() + '&id=' + id
                        }).then(function (response) {
                            $scope.status = response.status;
                            $scope.ajaxDataUsuarios = response.data.message;
                            swal("El cliente ha sido borrado correctamente!", {
                                icon: "success",
                            });
                            $location.path("/cliente/plist/");
                        }, function (response) {
                            $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
                            $scope.status = response.status;
                        });

                    } else {
                        swal("Ha habido un error y no se ha podido borrar.");
                    }
                });
        }




    }]);