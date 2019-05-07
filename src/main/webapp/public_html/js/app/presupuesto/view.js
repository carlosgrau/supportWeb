'use strict';

modulePresupuesto.controller('presupuestoViewController', ['$scope', 'toolService', '$http', 'sessionService', '$routeParams', '$location',
    function ($scope, toolService, $http, sessionService, $routeParams, $location) {
        $scope.id = $routeParams.id;
        var host ='http://localhost:8081/';
        $http({
            method: 'GET',
            url: host+'json?ob=presupuesto&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataPresupuesto = response.data.message;
            $scope.ajaxDataLineaPresupuesto = response.data.message.obj_LineaPresupuesto;
            $scope.ajaxDataCliente = response.data.message.obj_Cliente;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataLineaPresupuesto = response.data.message || 'Request failed';
            $scope.ajaxDataCliente = response.data.message || 'Request failed';
            $scope.ajaxDataPresupuesto = response.data.message || 'Request failed';
        });

        $scope.update = function () {
            $location.url(`presupuesto/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
        };
        $scope.isActive = toolService.isActive;

$scope.atras = function () {
            window.history.back();
        };

    }
]);