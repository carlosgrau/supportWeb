SuportWeb4All.config(['$routeProvider', function ($routeProvider) {
        //ADMINISTRADOR
        //USUARIO
        $routeProvider.when('/cliente/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/cliente/plist.html', controller: 'clientePlistController'});
        $routeProvider.when('/cliente/plist', {templateUrl: 'js/app/cliente/plist.html', controller: 'clientePlistController'});
        $routeProvider.when('/cliente/view/:id?', {templateUrl: 'js/app/cliente/view.html', controller: 'clienteViewController'});
        $routeProvider.when('/cliente/edit/:id?', {templateUrl: 'js/app/cliente/edit.html', controller: 'clienteEditController'});
        $routeProvider.when('/cliente/remove/:id?', {templateUrl: 'js/app/cliente/remove.html', controller: 'clienteRemoveController'});
        $routeProvider.when('/cliente/create/', {templateUrl: 'js/app/cliente/create.html', controller: 'clienteCreateController'});
        $routeProvider.when('/cliente/plistfactura/:id?/:rpp?/:page?/:order?', {templateUrl: 'js/app/cliente/plistfactura.html', controller: 'clientePlistFacturaController'});
        $routeProvider.when('/cliente/new', {templateUrl: 'js/app/cliente/new.html', controller: 'clienteNewController'});

//        //TIPOUSUARIO
//        $routeProvider.when('/tipousuario/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/tipousuario/plist.html', controller: 'tipousuarioPlistControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipousuario/edit/:id?', {templateUrl: 'js/app/tipousuario/edit.html', controller: 'tipousuarioEditControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipousuario/view/:id?', {templateUrl: 'js/app/tipousuario/view.html', controller: 'tipousuarioViewControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipousuario/remove/:id?', {templateUrl: 'js/app/tipousuario/remove.html', controller: 'tipousuarioRemoveControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipousuario/new', {templateUrl: 'js/app/tipousuario/new.html', controller: 'tipousuarioNewControllerAdm', resolve: {auth: autenticacionAdministrador}});
//
//        //FACTURA
//        $routeProvider.when('/factura/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/factura/plist.html', controller: 'facturaPlistControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/factura/edit/:id?', {templateUrl: 'js/app/factura/edit.html', controller: 'facturaEditControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/factura/plistlinea/:id?/:rpp?/:page?/:order?', {templateUrl: 'js/app/factura/plistlinea.html', controller: 'facturaViewControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/factura/remove/:id?', {templateUrl: 'js/app/factura/remove.html', controller: 'facturaRemoveControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/factura/new', {templateUrl: 'js/app/factura/new.html', controller: 'facturaNewControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/factura/newfacturauser/:id?', {templateUrl: 'js/app/factura/newfacturauser.html', controller: 'facturaNewUserControllerAdm', resolve: {auth: autenticacionAdministrador}});
//
//
//        //TIPOPORDUCTO
//        $routeProvider.when('/tipoproducto/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/tipoproducto/plist.html', controller: 'tipoproductoPlistControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipoproducto/edit/:id?', {templateUrl: 'js/app/tipoproducto/edit.html', controller: 'tipoproductoEditControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipoproducto/view/:id?', {templateUrl: 'js/app/tipoproducto/view.html', controller: 'tipoproductoViewControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipoproducto/remove/:id?', {templateUrl: 'js/app/tipoproducto/remove.html', controller: 'tipoproductoRemoveControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/tipoproducto/new', {templateUrl: 'js/app/tipoproducto/new.html', controller: 'tipoproductoNewControllerAdm', resolve: {auth: autenticacionAdministrador}});
//
//        //PRODUCTO
        $routeProvider.when('/producto/plist/:rpp?/:page?/:order?', {templateUrl: 'js/app/producto/plist.html', controller: 'productoPlistController'});
        //$routeProvider.when('/producto/plist_1/:rpp?/:page?/:order?', {templateUrl: 'js/app/producto/plist_1.html', controller: 'productoPlistControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/producto/view/:id?', {templateUrl: 'js/app/producto/view.html', controller: 'productoViewController'});
//        $routeProvider.when('/producto/edit/:id?', {templateUrl: 'js/app/producto/edit.html', controller: 'productoEditController'});
//        $routeProvider.when('/producto/remove/:id?', {templateUrl: 'js/app/producto/remove.html', controller: 'productoRemoveController'});
//        $routeProvider.when('/producto/new', {templateUrl: 'js/app/producto/new.html', controller: 'productoNewController'});
//
//        //LINEA
//        $routeProvider.when('/linea/edit/:id?', {templateUrl: 'js/app/linea/edit.html', controller: 'lineaEditControllerAdm', resolve: {auth: autenticacionAdministrador}});
//        $routeProvider.when('/linea/lineafactura/:id?', {templateUrl: 'js/app/linea/lineafactura.html', controller: 'lineaNewControllerAdm', resolve: {auth: autenticacionAdministrador}});
//
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
