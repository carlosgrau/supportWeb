package com.factory;

import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.service.ClienteService;
import com.service.EmpresaService;
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
            default:
                oReplyBean = new ReplyBean(500, "Object doesn't exist");
                break;
        }
        return oReplyBean;

    }

}
