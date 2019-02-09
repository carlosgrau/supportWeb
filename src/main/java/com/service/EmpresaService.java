/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.EmpresaBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.connection.specificimplementation.HikariConnectionForUser;
import com.dao.EmpresaDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Carlos
 */
public class EmpresaService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public EmpresaService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        if ("empresa".equals(oRequest.getParameter("ob"))) {
            ob = "dat000a";
        };
    }

    public ReplyBean get() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();

        try {
            Integer id = Integer.parseInt(oRequest.getParameter("id"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            EmpresaDao oEmpresaDao = new EmpresaDao(oConnection, ob);
            EmpresaBean oEmpresaBean = oEmpresaDao.get(id);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oEmpresaBean));
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
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            EmpresaDao oEmpresaDao = new EmpresaDao(oConnection, ob);

            ArrayList<EmpresaBean> alEmpresaBean = oEmpresaDao.getpage(iRpp, iPage);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alEmpresaBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;

    }

    public ReplyBean select() throws Exception {
        ReplyBean oReplyBean;
        EmpresaBean oEmpresaBean = null;
        try {
            if (oRequest.getSession().getAttribute("ejercicio") != null) {
                oRequest.getSession().setAttribute("ejercicio", null);
            }
            String strJsonFromClient = oRequest.getParameter("json");

            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            oEmpresaBean = oGson.fromJson(strJsonFromClient, EmpresaBean.class);
            oRequest.getSession().setAttribute("ejercicio", oEmpresaBean);
            oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Empresa seleccionada correctamente"));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        }
        return oReplyBean;
    }
;
}
