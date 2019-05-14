'use strict';

moduleFactura.controller('presupuestoNewController', ['$scope', 'toolService', '$http', 'sessionService', '$routeParams', '$location', 'countcarritoService', '$filter',
    function ($scope, toolService, $http, sessionService, $routeParams, $location, countcarritoService, $filter) {
        $(document).ready(function () {
            $scope.id = $routeParams.id;
            var host = 'http://localhost:8081/';
            $scope.linea = 1;
            var fecha;

            $http({
                method: 'GET',
                url: host + 'json?ob=carrito&op=empty'
            }).then(function (response) {
                $scope.status = response.status;
                $scope.ajaxDatoLineas = response.data.message;
            }, function (response) {
                $scope.status = response.status;
                $scope.ajaxDatoLineas = response.data.message || 'Request failed';
            });

            $scope.update = function () {
                $location.url(`factura/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
            };
            $scope.isActive = toolService.isActive;

            $scope.atras = function () {
                window.history.back();
            };
            $scope.sacarlineas = function () {
                $http({
                    method: 'GET',
                    url: host + 'json?ob=carrito&op=show'
                }).then(function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDatoLineas = response.data.message;
                }, function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDatoLineas = response.data.message || 'Request failed';
                });
                $http({
                    method: 'GET',
                    url: host + 'json?ob=carrito&op=show'
                }).then(function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDatoLineas = response.data.message;
                }, function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDatoLineas = response.data.message || 'Request failed';
                });
                $http({
                    method: 'GET',
                    url: host + 'json?ob=presupuesto&op=getpresupuesto&ejercicio=' + sessionService.getEmpresa()
                }).then(function (response) {
                    $scope.status = response.status;
                    $scope.ajaxPresupuestoId = response.data.message;
                }, function (response) {
                    $scope.status = response.status;
                    $scope.ajaxPresupuestoId = response.data.message || 'Request failed';
                });
                $http({
                    method: 'GET',
                    withCredentials: true,
                    url: host + 'json?ob=cliente&op=get&ejercicio=' + sessionService.getEmpresa() + '&id=' + $scope.ajaxDatoProducto.obj_cliente.codigo
                }).then(function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDataUsuarios = response.data.message;
                }, function (response) {
                    $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
                    $scope.status = response.status;
                });
                var length = $scope.ajaxDatoLineas.length;
                $scope.precioBruto = 0;
                $scope.precioIva = 0;
                for (var i = 0; i < length; i++) {
                    console.log($scope.ajaxDatoLineas[i]);
                    $scope.precioBruto += $scope.ajaxDatoLineas[i].precio;
                    $scope.iva = 21;
                    $scope.precioIva += ($scope.precioBruto * $scope.iva) / 100;
                }
            };
            $scope.addlineas = function () {
                $http({
                    method: 'GET',
                    url: host + 'json?ob=carrito&op=add&ejercicio=' + sessionService.getEmpresa() + '&codigo=' + $scope.ajaxProducto.obj_cliente.referencia + '&cantidad=' + $scope.ajaxProducto.cantidad + '&descuento=' + $scope.ajaxProducto.descuento + '&precio=' + $scope.ajaxProducto.precio
                }).then(function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDatoLineasAdd = response.data.message;
                    $(':input', '#lineasForm')
                        .not(':button, :submit, :reset, :hidden')
                        .val('')
                        .prop('checked', false)
                        .prop('selected', false);
                }, function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDatoLineasAdd = response.data.message || 'Request failed';
                });

            };
            $scope.grabar = function () {
                var json = {
                    id_cliente: $scope.ajaxDatoProducto.obj_cliente.codigo,
                    nombre: $scope.ajaxDatoProducto.obj_cliente.nombre,
                    empresa: sessionService.getEmpresa(),
                    representante: $scope.ajaxDatoProducto.obj_cliente.codrepre,
                    fpago: $scope.ajaxDatoProducto.obj_cliente.codfpago,
                    tarifa: $scope.ajaxDatoProducto.obj_cliente.tarifa,
                    fecha: $filter('date')(new Date())

                };
                $http({
                    method: 'GET',
                    withCredentials: true,
                    url: host + 'json?ob=carrito&op=buypresupuesto&ejercicio=' + sessionService.getEmpresa(),
                    params: { json: JSON.stringify(json) }
                }).then(function (response) {
                    $scope.status = response.status;
                    $scope.ajaxDataUsuarios = response.data.message;
                }, function (response) {
                    $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
                    $scope.status = response.status;
                });

            };

            $scope.grabarcabecera = function () {

                if ($scope.ajaxDatoProducto.obj_cliente.fecha === undefined) {
                    fecha = $filter('date')(new Date(), "yyyy-MM-dd");
                } else {
                    fecha = $filter('date')($scope.ajaxDatoProducto.obj_cliente.fecha, "yyyy-MM-dd");;
                }

                var codigocliente = $scope.ajaxDatoProducto.obj_cliente.codigo;
                var nombrecliente = $scope.ajaxDatoProducto.obj_cliente.nombre;
                var empresa = sessionService.getEmpresa();
                var codigorespresentante = $scope.ajaxDatoProducto.obj_cliente.codrepre;
                var codigoformapago = $scope.ajaxDatoProducto.obj_cliente.codfpago;
                var codigotarifa = $scope.ajaxDatoProducto.obj_cliente.tarifa;
            };

            $scope.formulario = function (paso) {
                if (paso === 1) {
                    cabecera.style = 'display:content;;';
                    lineas.style = 'display:none;';
                    plist.style = 'display:none;';
                    final.style = 'display:none;';
                } else if (paso === 2) {
                    $scope.grabarcabecera();
                    cabecera.style = 'display:none;';
                    lineas.style = 'display:content;';
                    plist.style = 'display:none;';
                    final.style = 'display:none;';
                } else if (paso === 3) {
                    cabecera.style = 'display:none;';
                    lineas.style = 'display:none;';
                    plist.style = 'display:content;';
                    $scope.sacarlineas();
                    final.style = 'display:none;';
                } else if (paso === 4) {
                    cabecera.style = 'display:none;';
                    lineas.style = 'display:none;';
                    plist.style = 'display:none;';
                    $scope.sacarlineas();
                    final.style = 'display:content;';
                }
            }

        })
    }
]);