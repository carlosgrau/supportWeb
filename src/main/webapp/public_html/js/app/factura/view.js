'use strict';

moduleFactura.controller('facturaViewController', ['$scope', 'toolService', '$http', 'sessionService', '$routeParams', '$location',
    function ($scope, toolService, $http, sessionService, $routeParams, $location) {
        $scope.id = $routeParams.id;
        var host ='http://localhost:8081/';
        $http({
            method: 'GET',
            url: host+'json?ob=factura&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataFactura = response.data.message;
            $scope.ajaxDataLineaFactura = response.data.message.obj_LineaFactura;
            $scope.ajaxDataCliente = response.data.message.obj_Cliente;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataLineaFactura = response.data.message || 'Request failed';
            $scope.ajaxDataCliente = response.data.message || 'Request failed';
            $scope.ajaxDataFactura = response.data.message || 'Request failed';
        });

        $scope.update = function () {
            $location.url(`factura/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
        };
        $scope.isActive = toolService.isActive;

$scope.atras = function () {
            window.history.back();
        };

    }
]);