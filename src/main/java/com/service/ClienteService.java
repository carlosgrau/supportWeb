/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.ClienteBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.dao.ClienteDao;
import com.google.gson.Gson;
import com.helper.ParameterCook;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a021792876p
 */
public class ClienteService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public ClienteService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        if ("cliente".equals(oRequest.getParameter("ob"))) {
            ob = "dat001a";
        };
    }

    public ReplyBean get() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;

        try {
            Integer id = Integer.parseInt(oRequest.getParameter("id"));
            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = oUsuarioBean.newConnection(usuario, password, conexion);

            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);
            ClienteBean oClienteBean = oClienteDao.get(id, empresa, 1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oClienteBean));
        } catch (Exception ex) {
            oUsuarioBean.disposeConnection();
            oConnection.close();
            throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);
        } finally {
            if (oUsuarioBean != null) {
                oUsuarioBean.disposeConnection();
            }
            if (oConnection != null) {
                oConnection.close();
            }
        }

        return oReplyBean;

    }

    public ReplyBean getpage() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        try {
            Integer iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
            Integer iPage = Integer.parseInt(oRequest.getParameter("page"));
            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            HashMap<String, String> hmOrder = ParameterCook.getOrderParams(oRequest.getParameter("order"));
            
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            
            oConnection = oUsuarioBean.newConnection(usuario, password, conexion);

            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);

            ArrayList<ClienteBean> alClienteBean = oClienteDao.getpage(iRpp, iPage, empresa, hmOrder);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alClienteBean));
        } catch (Exception ex) {
            oUsuarioBean.disposeConnection();
            oConnection.close();
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            if (oUsuarioBean != null) {
                oUsuarioBean.disposeConnection();
            }
            if (oConnection != null) {
                oConnection.close();
            }
        }

        return oReplyBean;

    }

    public ReplyBean getcount() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        try {

            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = oUsuarioBean.newConnection(usuario, password, conexion);

            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);

            int registros = oClienteDao.getcount(empresa);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(registros));
        } catch (Exception ex) {
            oUsuarioBean.disposeConnection();
            oConnection.close();
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
             if (oUsuarioBean != null) {
                oUsuarioBean.disposeConnection();
            }
            if (oConnection != null) {
                oConnection.close();
            }
        }

        return oReplyBean;
    }
}
