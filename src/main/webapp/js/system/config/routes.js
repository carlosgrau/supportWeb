SuportWeb4All.config(['$routeProvider', function ($routeProvider) {
        //USUARIO
        $routeProvider.when('/cliente/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/cliente/plist.html', controller: 'clientePlistController'});
        $routeProvider.when('/cliente/plist', {templateUrl: 'js/app/cliente/plist.html', controller: 'clientePlistController'});
        $routeProvider.when('/cliente/view/:id?', {templateUrl: 'js/app/cliente/view.html', controller: 'clienteViewController'});

//        //FACTURA
        $routeProvider.when('/factura/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/factura/plist.html', controller: 'facturaPlistController'});
        $routeProvider.when('/factura/view/:id?', {templateUrl: 'js/app/factura/view.html', controller: 'facturaViewController'});

//        //PRODUCTO
        $routeProvider.when('/producto/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/producto/plist.html', controller: 'productoPlistController'});

        //USUARIOS
        $routeProvider.when('/usr/cliente/view/:id?', {templateUrl: 'js/app/usr/cliente/view.html', controller: 'clienteViewController'});
        $routeProvider.when('/usr/factura/plistlinea/:id?/:rpp?/:page?/:order?', {templateUrl: 'js/app/usr/factura/plistlinea.html', controller: 'clienteViewController'});
        $routeProvider.when('/usr/producto/view/:id?', {templateUrl: 'js/app/usr/producto/view.html', controller: 'productoViewController'});
        $routeProvider.when('/usr/producto/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/usr/producto/plist.html', controller: 'productoPlistController'});
        $routeProvider.when('/usr/cliente/plistfactura/:id?/:rpp?/:page?/:order?', {templateUrl: 'js/app/usr/cliente/plistfactura.html', controller: 'usuarioPlistFacturaController'});


        //LOGIN
        $routeProvider.when('/login', {templateUrl: 'js/app/login.html', controller: 'loginController'});
        $routeProvider.when('/empresa', {templateUrl: 'js/app/empresa.html', controller: 'empresaController'});

        //OTROS
        $routeProvider.when('/', {templateUrl: 'js/app/common/home.html', controller: 'homeController'});
        $routeProvider.when('/home', {templateUrl: 'js/app/common/home.html', controller: 'homeController'});
        $routeProvider.when('/carrito', {templateUrl: 'js/app/common/carrito.html', controller: 'carritoController'});


        $routeProvider.otherwise({redirectTo: '/'});
    }]);
