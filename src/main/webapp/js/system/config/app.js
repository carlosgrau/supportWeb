'use strict'

var SuportWeb4All = angular.module('MyApp', [
    'ngRoute',
    'services',
    'commonControllers',
    'loginControllers',
    'empresaControllers',
    'moduleComponent',
    'ngMaterial',
    'Directives',
    'clienteControllers',
    'productoControllers',
    'facturaControllers'

]).config(function ($mdDateLocaleProvider) {
    // Example of a Spanish localization.
    $mdDateLocaleProvider.months = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
        'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
    $mdDateLocaleProvider.shortMonths = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
        'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'];
    $mdDateLocaleProvider.days = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sábado'];
    $mdDateLocaleProvider.shortDays = ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'];
    // Can change week display to start on Monday.
    $mdDateLocaleProvider.firstDayOfWeek = 1;
    $mdDateLocaleProvider.weekNumberFormatter = function (weekNumber) {
        return 'Semana ' + weekNumber;
    };
    $mdDateLocaleProvider.msgCalendar = 'Calendario';
    $mdDateLocaleProvider.msgOpenCalendar = 'Abrir calendario';
    $mdDateLocaleProvider.formatDate = function (date) {
        return moment(date).format('DD-MM-YYYY');
    };
});

var moduleCommon = angular.module('commonControllers', []);
var moduleService = angular.module('services', []);
var moduleTipousuario = angular.module('tipousuarioControllers', []);
var moduleCliente = angular.module('clienteControllers', []);
var moduleProducto = angular.module('productoControllers', []);
var moduleFactura = angular.module('facturaControllers', []);
var moduleTipoproducto = angular.module('tipoproductoControllers', []);
var moduleCarrito = angular.module('carritoControllers', []);
var moduleLinea = angular.module('lineaControllers', []);
var moduleComponent = angular.module('moduleComponent', []);

var moduleLogin = angular.module('loginControllers', []);
var moduleEmpresa = angular.module('empresaControllers', []);

var moduloDirectivas = angular.module('Directives', []);