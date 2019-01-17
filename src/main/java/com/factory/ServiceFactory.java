package com.factory;

import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.service.AlbaranService;
import com.service.ClienteService;
import com.service.EmpresaService;
import com.service.FacturaService;
import com.service.LineaAlbaranService;
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
                    case "getpage":
                        oReplyBean = oClienteService.getpage();
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

                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
//            case "lineaalbaran":
//                LineaAlbaranService oLineaAlbaranService = new LineaAlbaranService(oRequest);
//                switch (op) {
//                    case "get":
//                        oReplyBean = oLineaAlbaranService.get();
//                        break;
//                    case "getpage":
//                        oReplyBean = oLineaAlbaranService.getpage();
//                        break;
//
//                    default:
//                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
//                        break;
//                }
//                break;
            case "presupuesto":
                PresupuestoService oPresupuestoService = new PresupuestoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oPresupuestoService.get();
                        break;
                    case "getpage":
                        oReplyBean = oPresupuestoService.getpage();
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
