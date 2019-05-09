'use strict';
moduleService.service('countcarritoService', ['$http', 'sessionService', function ($http, sessionService) {
    return {

        updateCarrito: function () {
            var host = 'http://localhost:8081/';
            $http({
                method: "GET",
                url: host + "json?ob=carrito&op=show",
                dataType: "jsonp"
            }).then(function (response) {
                var cantidad = 0;
                if (response.data.message !== null) {
                    for (var i = 0; i < response.data.message.length; i++) {
                        if (response.data.message[i].cantidad !== 0 && response.data.message[i].cantidad !== undefined) {
                            cantidad += response.data.message[i].cantidad;
                        }
                    }
                }
                sessionService.setCountCarrito(cantidad);
            }, function (response) {
            });
        }
    };
}]);