/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.FacturaBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.dao.FacturaDao;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a021792876p
 */
public class FacturaService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public FacturaService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        if ("factura".equals(oRequest.getParameter("ob"))) {
            ob = "dat140a";
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

            FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
            FacturaBean oFacturaBean = oFacturaDao.get(id, empresa,1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oFacturaBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);
        } finally {
            oConnection.close();
            oUsuarioBean.disposeConnection();
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
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = oUsuarioBean.newConnection(usuario, password, conexion);

            FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);

            ArrayList<FacturaBean> alFacturaBean = oFacturaDao.getpage(iRpp, iPage, empresa,1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alFacturaBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
            oUsuarioBean.disposeConnection();
        }

        return oReplyBean;

    }
}
