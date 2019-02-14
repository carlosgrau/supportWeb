moduloDirectivas.component('headerComponent', {
    templateUrl: 'js/components/header.html',
    bindings: {
    },
    controllerAs: 'c',
    controller: js
});

function js(toolService, sessionService, $http, $route, $location) {

    var self = this;

    self.usuario = sessionService.getUserName();
    self.isActive = toolService.isActive;
    self.ocultar = sessionService.isSessionActive();

    sessionService.registerObserverCallback(function () {
    });

    self.logout = function () {
        $http({
            method: 'GET',
            url: '/json?ob=usuario&op=logout'
        }).then(function (response) {
            if (response.status === 200) {
                swal({title: "Hasta pronto!",
                    imageUrl: "http://www.gifsanimados.org/data/media/81/mano-imagen-animada-0080.gif"
                });
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