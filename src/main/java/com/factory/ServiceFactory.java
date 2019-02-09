package com.factory;

import com.bean.ReplyBean;
import com.service.AlbaranService;
import com.service.ClienteService;
import com.service.EmpresaService;
import com.service.FacturaService;
import com.service.PresupuestoService;
import com.service.ProductoService;
import com.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;

public class ServiceFactory {

    public static ReplyBean executeService(HttpServletRequest oRequest) throws Exception {

        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBean oReplyBean = null;

        switch (ob) {
            case "usuario":
                UsuarioService oUsuarioService = new UsuarioService(oRequest);
                switch (op) {
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "check":
                        oReplyBean = oUsuarioService.check();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "empresa":
                EmpresaService oEmpresaService = new EmpresaService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oEmpresaService.get();
                        break;
                    case "getpage":
                        oReplyBean = oEmpresaService.getpage();
                        break;
                    case "select":
                        oReplyBean = oEmpresaService.select();
                        break;

                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;

            case "cliente":
                ClienteService oClienteService = new ClienteService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oClienteService.get();
                        break;
                    case "create":
                        oReplyBean = oClienteService.create();
                        break;
                    case "update":
                        oReplyBean = oClienteService.update();
                        break;
                    case "getpage":
                        oReplyBean = oClienteService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oClienteService.getcount();
                        break;

                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "producto":
                ProductoService oProductoService = new ProductoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oProductoService.get();
                        break;
                    case "getpage":
                        oReplyBean = oProductoService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oProductoService.getcount();
                        break;

                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "albaran":
                AlbaranService oAlbaranService = new AlbaranService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oAlbaranService.get();
                        break;
                    case "getpage":
                        oReplyBean = oAlbaranService.getpage();
                        break;
                    case "getpagexusuario":
                        oReplyBean = oAlbaranService.getpageXusuario();
                        break;
                    case "create":
                        oReplyBean = oAlbaranService.create();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;

            case "presupuesto":
                PresupuestoService oPresupuestoService = new PresupuestoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oPresupuestoService.get();
                        break;
                    case "getpage":
                        oReplyBean = oPresupuestoService.getpage();
                        break;
                    case "getpagexusuario":
                        oReplyBean = oPresupuestoService.getpageXusuario();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "factura":
                FacturaService oFacturaService = new FacturaService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oFacturaService.get();
                        break;
                    case "getpage":
                        oReplyBean = oFacturaService.getpage();
                        break;
                    case "getpagexusuario":
                        oReplyBean = oFacturaService.getpageXusuario();
                        break;
                    case "getcount":
                        oReplyBean = oFacturaService.getcount();
                        break;

                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            default:
                oReplyBean = new ReplyBean(500, "Object doesn't exist");
                break;
        }
        return oReplyBean;

    }

}
