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
            var navListItems = $('div.setup-panel div a'), // tab nav items
                allWells = $('.setup-content'), // content div
                allNextBtn = $('.nextBtn'); // next button

            allWells.hide(); // hide all contents by defauld

            navListItems.click(function (e) {
                e.preventDefault();
                var $target = $($(this).attr('href')),
                    $item = $(this);

                if (!$item.hasClass('disabled')) {
                    navListItems.removeClass('btn-primary').addClass('btn-default');
                    $item.addClass('btn-primary');
                    allWells.hide();
                    $target.show();
                    $target.find('input:eq(0)').focus();
                }
            });
            // next button
            allNextBtn.click(function () {
                var curStep = $(this).closest(".setup-content"),
                    curStepBtn = curStep.attr("id"),
                    nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
                    curInputs = curStep.find("input[type='text'],input[type='email'],input[type='password'],input[type='url']"),
                    isValid = true;
                // Validation
                $(".form-group").removeClass("has-error");
                for (var i = 0; i < curInputs.length; i++) {
                    if (!curInputs[i].validity.valid) {
                        isValid = false;
                        $(curInputs[i]).closest(".form-group").addClass("has-error");
                    }
                }
                // move to next step if valid
                if (isValid)
                    nextStepWizard.removeAttr('disabled').trigger('click');
            });

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
                var paso1 = true;
                var paso2 = false;
                var paso3 = false;
                if (paso === 1) {
                    paso1 === true;
                    paso2 === false;
                    paso3 === false
                } else if (paso === 2) {
                    paso1 === false;
                    paso2 === true;
                    paso3 === false
                } else if (paso === 3) {
                    paso1 === false;
                    paso2 === false;
                    paso3 === true;}

                    return paso;
                }

            })
    }
]);