/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.ProductoBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.connection.specificimplementation.HikariConnectionForUser;
import com.dao.ProductoDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a021792876p
 */
public class ProductoService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public ProductoService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        if ("producto".equals(oRequest.getParameter("ob"))) {
            ob = "dat004a";
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
            ProductoDao oProductoDao = new ProductoDao(oConnection, ob);
            ProductoBean oProductoBean = oProductoDao.get(id.toString(), 1, empresa);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oProductoBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);
        } finally {
            oConnection.close();
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
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            ProductoDao oProductoDao = new ProductoDao(oConnection, ob);

            ArrayList<ProductoBean> alProductoBean = oProductoDao.getpage(iRpp, iPage, empresa);
            int registros = oProductoDao.getcount(empresa);

            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alProductoBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
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
            ProductoDao oProductoDao = new ProductoDao(oConnection, ob);

            int registros = oProductoDao.getcount(empresa);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(registros));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
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
            ProductoBean oProductoBean = new ProductoBean();
            oProductoBean = oGson.fromJson(strJsonFromClient, ProductoBean.class);

            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            ProductoDao oProductoDao = new ProductoDao(oConnection, ob);

            oProductoBean = oProductoDao.create(oProductoBean);
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
            ProductoBean oProductoBean = new ProductoBean();
            oProductoBean = oGson.fromJson(strJsonFromClient, ProductoBean.class);

            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            ProductoDao oProductoDao = new ProductoDao(oConnection, ob);

            int registros = oProductoDao.update(oProductoBean);
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
