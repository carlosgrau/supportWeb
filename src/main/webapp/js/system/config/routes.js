SuportWeb4All.config(['$routeProvider', function ($routeProvider) {
        //USUARIO
        $routeProvider.when('/cliente/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/cliente/plist.html', controller: 'clientePlistController'});
        $routeProvider.when('/cliente/plist', {templateUrl: 'js/app/cliente/plist.html', controller: 'clientePlistController'});
        $routeProvider.when('/cliente/view/:id?', {templateUrl: 'js/app/cliente/view.html', controller: 'clienteViewController'});
        $routeProvider.when('/cliente/update/:id?', {templateUrl: 'js/app/cliente/update.html', controller: 'clienteEditController'});
        $routeProvider.when('/cliente/create', {templateUrl: 'js/app/cliente/create.html', controller: 'clienteCreateController'});

//        //FACTURA
        $routeProvider.when('/factura/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/factura/plist.html', controller: 'facturaPlistController'});
        $routeProvider.when('/factura/view/:id?', {templateUrl: 'js/app/factura/view.html', controller: 'facturaViewController'});

//        //PRODUCTO
        $routeProvider.when('/producto/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/producto/plist.html', controller: 'productoPlistController'});
        $routeProvider.when('/producto/view/:id?', {templateUrl: 'js/app/producto/view.html', controller: 'productoViewController'});


        //LOGIN
        $routeProvider.when('/login', {templateUrl: 'js/app/login.html', controller: 'loginController'});
        $routeProvider.when('/empresa', {templateUrl: 'js/app/empresa.html', controller: 'empresaController'});

        //OTROS
        $routeProvider.when('/', {templateUrl: 'js/app/common/home.html', controller: 'homeController'});
        $routeProvider.when('/home', {templateUrl: 'js/app/common/home.html', controller: 'homeController'});
        $routeProvider.when('/carrito', {templateUrl: 'js/app/common/carrito.html', controller: 'carritoController'});


        $routeProvider.otherwise({redirectTo: '/'});
    }]);
