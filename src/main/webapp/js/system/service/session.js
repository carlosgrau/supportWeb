'use strict';
moduleService.service('sessionService', ['$location', function ($location) {
        var isSessionActive = false;
        var userName = "";
        var userId = '';
        var tipoUserId = '';
        var carrito = 0;
        var observerCallbacks = [];
        var empresaEjercicio;
        return {
            getUserName: function () {
                return userName;
            },
            setUserName: function (name) {
                angular.forEach(observerCallbacks, function (callback) {
                    callback();
                });
                userName = name;
            },
            getEmpresa: function () {
                return empresaEjercicio;
            },
            setEmpresa: function (empresa) {
                angular.forEach(observerCallbacks, function (callback) {
                    callback();
                });
                empresaEjercicio = empresa;
            },
            isSessionActive: function () {

                return isSessionActive;
            },
            setSessionActive: function (name) {
                angular.forEach(observerCallbacks, function (callback) {
                    callback();
                });
                isSessionActive = true;
            },
            setSessionInactive: function (name) {
                angular.forEach(observerCallbacks, function (callback) {
                    callback();
                });
                isSessionActive = false;
            },
            getUserId: function () {
                return userId;
            },
            setUserId: function (id) {
                userId = id;
            },
            getTipoUserId: function () {
                return tipoUserId;
            },
            setTipoUserId: function (idTipoUsuario) {
                tipoUserId = idTipoUsuario;
            },
            setCountCarrito: function (cantidad) {
                carrito = cantidad;
                //Para que sirve el callback()
                //https://www.quora.com/What-is-the-call-back-function-in-AngularJS
                angular.forEach(observerCallbacks, function (callback) {
                    callback();
                });
            },
            getCountCarrito: function () {
                return carrito;
            },
            registerObserverCallback: function (callback) {
                observerCallbacks.push(callback);
            }

        };

    }]);