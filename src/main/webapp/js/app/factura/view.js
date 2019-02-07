'use strict';

moduleFactura.controller('facturaViewController', ['$scope', 'toolService', '$http', 'sessionService', '$routeParams', '$location',
    function ($scope, toolService, $http, sessionService, $routeParams, $location) {
        $scope.id= $routeParams.id;
        $http({
            method: 'GET',
            url: '/json?ob=factura&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataLineaFactura = response.data.message.obj_LineaFactura;
            $scope.ajaxDataCliente = response.data.message.obj_Cliente;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataLineaFactura = response.data.message || 'Request failed';
            $scope.ajaxDataCliente = response.data.message || 'Request failed';
        });

        $scope.update = function () {
            $location.url(`factura/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
        };
        $scope.isActive = toolService.isActive;



    }
]);