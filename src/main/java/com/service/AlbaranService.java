/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.AlbaranBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.dao.AlbaranDao;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a021792876p
 */
public class AlbaranService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public AlbaranService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        if ("albaran".equals(oRequest.getParameter("ob"))) {
            ob = "dat031a";
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

            AlbaranDao oAlbaranDao = new AlbaranDao(oConnection, ob);
            AlbaranBean oAlbaranBean = oAlbaranDao.get(id, empresa,1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oAlbaranBean));
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

            AlbaranDao oAlbaranDao = new AlbaranDao(oConnection, ob);

            ArrayList<AlbaranBean> alAlbaranBean = oAlbaranDao.getpage(iRpp, iPage, empresa,1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alAlbaranBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
            oUsuarioBean.disposeConnection();
        }

        return oReplyBean;

    }
}
