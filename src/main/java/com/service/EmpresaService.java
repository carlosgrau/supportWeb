/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.EmpresaBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.dao.EmpresaDao;
import com.google.gson.Gson;
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

        try {
            Integer id = Integer.parseInt(oRequest.getParameter("id"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = oUsuarioBean.newConnection(usuario, password, conexion);

            EmpresaDao oEmpresaDao = new EmpresaDao(oConnection, ob);
            EmpresaBean oEmpresaBean = oEmpresaDao.get(id);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oEmpresaBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);
        } finally {
            oConnection.close();
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
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = oUsuarioBean.newConnection(usuario, password, conexion);

            EmpresaDao oEmpresaDao = new EmpresaDao(oConnection, ob);

            ArrayList<EmpresaBean> alEmpresaBean = oEmpresaDao.getpage(iRpp, iPage);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alEmpresaBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
        }

        return oReplyBean;

    }

}
