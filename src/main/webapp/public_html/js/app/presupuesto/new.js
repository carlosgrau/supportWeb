'use strict';

moduleFactura.controller('presupuestoNewController', ['$scope', 'toolService', '$http', 'sessionService', '$routeParams', '$location', 'countcarritoService',
    function ($scope, toolService, $http, sessionService, $routeParams, $location, countcarritoService) {
        $(document).ready(function () {
            $scope.id = $routeParams.id;
            var host = 'http://localhost:8081/';
            $scope.linea=1;

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
                    referencia: 'item',
                    description: 'item description',
                    qty: 5,
                    price: 5.5,
                    dto:0
                }]
            };
            $scope.add = function () {
                var longitud;
                $scope.invoice.items.push({
                    name: '',
                    description: '',
                    qty: 1,
                    price: 0,
                    dto:0
                });

                if (longitud !== $scope.invoice.items.length) {
                    for (var i = 0; i < $scope.invoice.items.length; i++) {
                        var referencia = $scope.invoice.items[i].referencia;
                        longitud = ($scope.invoice.items.length -1);
                        if (referencia != undefined) {
                            $http({
                                method: 'GET',
                                url: host + 'json?ob=carrito&op=add&codigo=' + referencia + '&cant=1&ejercicio=' + sessionService.getEmpresa()
                            }).then(function (response) {
                                $scope.status = response.status;
                                $scope.ajaxCarrito = response.data.message;
                                countcarritoService.updateCarrito();
                                $scope.carrito = true;
                                $scope.cantidadTotal = 0;
                                $scope.precioTotalProd = 0.0;
                                if (($scope.ajaxCarrito === "Carrito vacio") || ($scope.ajaxCarrito === null)) {
                                    $scope.carrito = false;
                                } else {
                                    for (var i = 0; i < $scope.ajaxCarrito.length; i++) {
                                        $scope.cantidadTotal += response.data.message[i].cantidad;
                                        $scope.precioTotalProd += (response.data.message[i].obj_producto.precio * response.data.message[i].cantidad);
                                    }
                                }
                            }, function (response) {
                                $scope.status = response.status;
                                $scope.ajaxCarrito = response.data.message || 'Request failed';
                            });
                        };
                    }
                };
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