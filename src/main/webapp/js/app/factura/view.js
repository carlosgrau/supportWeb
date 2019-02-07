'use strict';

moduleFactura.controller('facturaViewController', ['$scope', 'toolService', '$http', 'sessionService', '$routeParams', '$location',
    function ($scope, toolService, $http, sessionService, $routeParams, $location) {

        $http({
            method: 'GET',
            url: '/json?ob=factura&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataLineaFactura = response.data.message;
            for (var i = 0; i <= $scope.ajaxDataLineaFactura.obj_LineaFactura.length - 1; i++) {
                $scope.codigo =$scope.ajaxDataLineaFactura.obj_LineaFactura[i].obj_Producto.codigo;
            }
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataLineaFactura = response.data.message || 'Request failed';
        });

        $scope.update = function () {
            $location.url(`factura/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
        };
        $scope.isActive = toolService.isActive;



    }
]);