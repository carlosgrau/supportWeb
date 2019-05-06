'use strict';

moduleFactura.controller('presupuestoNewController', ['$scope', 'toolService', '$http', 'sessionService', '$routeParams', '$location',
    function ($scope, toolService, $http, sessionService, $routeParams, $location) {
        $(document).ready(function () {
            $scope.id = $routeParams.id;
            var host = 'http://localhost:8081/';

            // $http({
            //     method: 'GET',
            //     url: host+'json?ob=presupuesto&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $routeParams.id
            // }).then(function (response) {
            //     $scope.status = response.status;
            //     $scope.ajaxDataFactura = response.data.message;
            //     $scope.ajaxDataLineaFactura = response.data.message.obj_LineaFactura;
            //     $scope.ajaxDataCliente = response.data.message.obj_Cliente;
            // }, function (response) {
            //     $scope.status = response.status;
            //     $scope.ajaxDataLineaFactura = response.data.message || 'Request failed';
            //     $scope.ajaxDataCliente = response.data.message || 'Request failed';
            //     $scope.ajaxDataFactura = response.data.message || 'Request failed';
            // });

            $scope.update = function () {
                $location.url(`factura/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
            };
            $scope.isActive = toolService.isActive;

            $scope.atras = function () {
                window.history.back();
            };
            $('div.setup-panel div a.btn-primary').trigger('click');
            // $("input").prop("disabled", true); //Disable
            $scope.invoice = {
                items: [{
                    name: 'item',
                    description: 'item description',
                    qty: 5,
                    price: 5.5
                }]
            };
            $scope.add = function () {
                $scope.invoice.items.push({
                    name: '',
                    description: '',
                    qty: 1,
                    price: 0
                });
            },
                $scope.remove = function (index) {
                    $scope.invoice.items.splice(index, 1);
                },
                $scope.total = function () {
                    var total = 0;
                    angular.forEach($scope.invoice.items, function (item) {
                        total += item.qty * item.price;
                    })
                    return total;
                }
            $scope.formulario = function (paso) {
                if (paso === 1) {
                    cabecera.style = 'display:content;;';
                    lineas.style = 'display:none;';
                    final.style = 'display:none;';
                } else if (paso === 2) {
                    cabecera.style = 'display:none;';
                    lineas.style = 'display:content;';
                    final.style = 'display:none;';
                } else if (paso === 3) {
                    cabecera.style = 'display:none;';
                    lineas.style = 'display:none;';
                    final.style = 'display:content;';
                }
            }

        })
    }
]);