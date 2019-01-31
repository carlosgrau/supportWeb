moduloDirectivas.component('headerComponent', {
    templateUrl: 'js/components/header.html',
    bindings: {
    },
    controllerAs: 'c',
    controller: js
});

function js(toolService, sessionService, $http, $route, $location) {

    var self = this;
    self.ocultar = sessionService.isSessionActive();
    self.isActive = toolService.isActive;
    self.carrito = sessionService.getCountCarrito();
    self.usuario = sessionService.getUserName();

    sessionService.registerObserverCallback(function () {
        self.carrito = sessionService.getCountCarrito();
    });

    self.logout = function () {
        $http({
            method: 'GET',
            url: '/json?ob=usuario&op=logout'
        }).then(function (response) {
            if (response.status === 200) {
                sessionService.setTipoUserId('');
                sessionService.setUserId('');
                sessionService.setSessionInactive();
                sessionService.setUserName("");
                $route.reload();
                $location.path('/home');
            }
        });
    };
}