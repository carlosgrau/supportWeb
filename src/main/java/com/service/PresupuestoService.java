/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.PresupuestoBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.dao.PresupuestoDao;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a021792876p
 */
public class PresupuestoService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public PresupuestoService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        if ("presupuesto".equals(oRequest.getParameter("ob"))) {
            ob = "dat032a";
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

            PresupuestoDao oPresupuestoDao = new PresupuestoDao(oConnection, ob);
            PresupuestoBean oPresupuestoBean = oPresupuestoDao.get(id, empresa, 1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oPresupuestoBean));
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

            PresupuestoDao oPresupuestoDao = new PresupuestoDao(oConnection, ob);

            ArrayList<PresupuestoBean> alPresupuestoBean = oPresupuestoDao.getpage(iRpp, iPage, empresa, 1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alPresupuestoBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
            oUsuarioBean.disposeConnection();
        }

        return oReplyBean;

    }
}