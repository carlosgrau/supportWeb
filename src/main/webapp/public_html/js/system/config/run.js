SuportWeb4All.run(['$rootScope', 'sessionService', '$location', '$http', 'countcarritoService',
    function ($rootScope, oSessionService, $location, $http, countcarritoService) {
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            var nextUrl = next.$$route.originalPath;
            var host ='http://localhost:8081/';
            $http({
                method: 'GET',
                url: host+'json?ob=usuario&op=check'
            }).then(function (response) {
                if (response.data.status === 200) {
                    oSessionService.setSessionActive();
                    if (response.data.message[0].empresa !== undefined) {
                        oSessionService.setEmpresa(response.data.message[0].empresa);
                    } else {
                        oSessionService.setEmpresa(0);
                    }
                    if (response.data.message[0].loginCli !== undefined) {
                        oSessionService.setUserName(response.data.message[0].loginCli);
                    } else {
                        oSessionService.setUserName(response.data.message[1].loginCli);
                    }
//                    oSessionService.setUserId(response.data.message.id);
//                    oSessionService.setTipoUserId(response.data.message.obj_tipoUsuario.id);
                } else {
                    oSessionService.setSessionInactive;
                    if (nextUrl !== '/' && nextUrl !== '/home' && nextUrl !== '/login') {
                        $location.path("/");
                    }
                }
            }, function (response) {
                oSessionService.setSessionInactive;
                if (nextUrl !== '/' && nextUrl !== '/home' && nextUrl !== '/login') {
                    $location.path("/");
                }

            });
            countcarritoService.updateCarrito();
        });
    }]);
    