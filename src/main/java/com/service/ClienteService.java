/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.ClienteBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.connection.specificimplementation.HikariConnectionForUser;
import com.dao.ClienteDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();

        try {
            Integer id = Integer.parseInt(oRequest.getParameter("id"));
            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();

            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);

            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);
            ClienteBean oClienteBean = oClienteDao.get(id, empresa, 1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oClienteBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;

    }

    public ReplyBean getpage() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();
        try {
            Integer iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
            Integer iPage = Integer.parseInt(oRequest.getParameter("page"));
            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            HashMap<String, String> hmOrder = ParameterCook.getOrderParams(oRequest.getParameter("order"));

            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();

            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);

            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);

            ArrayList<ClienteBean> alClienteBean = oClienteDao.getpage(iRpp, iPage, empresa, hmOrder);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alClienteBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;

    }

    public ReplyBean getcount() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();
        try {

            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);

            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);

            int registros = oClienteDao.getcount(empresa);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(registros));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;
    }

    public ReplyBean create() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();

        try {
            String strJsonFromClient = oRequest.getParameter("json");
            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            ClienteBean oClienteBean = new ClienteBean();
            oClienteBean = oGson.fromJson(strJsonFromClient, ClienteBean.class);

            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);

            oClienteBean = oClienteDao.create(oClienteBean);
            oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: create method: " + ob + " object", ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;
    }

    public ReplyBean update() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();

        try {
            String strJsonFromClient = oRequest.getParameter("json");
            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            ClienteBean oClienteBean = new ClienteBean();
            oClienteBean = oGson.fromJson(strJsonFromClient, ClienteBean.class);

            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            ClienteDao oClienteDao = new ClienteDao(oConnection, ob);

            int registros = oClienteDao.update(oClienteBean);
            oReplyBean = new ReplyBean(200, oGson.toJson(registros));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: create method: " + ob + " object", ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;
    }
}
