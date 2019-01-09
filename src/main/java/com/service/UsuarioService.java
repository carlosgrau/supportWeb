/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

/*import com.bean.ReplyBean;
import net.daw.dao.UsuarioDao;
import net.daw.bean.UsuarioBean;*/
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;

import com.connection.publicinterface.ConnectionInterface;
import com.constant.ConnectionConstants;
import com.dao.UsuarioDao;
import com.factory.ConnectionFactory;
import com.helper.EncodingHelper;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a021792876p
 */
public class UsuarioService {

    HttpServletRequest oRequest;
    String ob = null;

    public UsuarioService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }

    public ReplyBean login() throws Exception {
        ReplyBean oReplyBean;
        ConnectionInterface oConnectionPool = null;
        Connection oConnection;
        String strLogin = oRequest.getParameter("user");
        String strPassword = oRequest.getParameter("pass");
        try {
            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
            oConnection = oConnectionPool.newConnection();
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, ob);

            UsuarioBean oUsuarioBean = oUsuarioDao.login(strLogin, strPassword);
            if (oUsuarioBean.getId() > 0) {
                oRequest.getSession().setAttribute("user", oUsuarioBean);
                Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
                oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
            } else {
                oReplyBean = new ReplyBean(401, "Bad Authentication");
            }
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: login method: " + ob + " object", ex);
        } finally {
            oConnectionPool.disposeConnection();
        }
        return oReplyBean;
    }

    public ReplyBean logout() throws Exception {
        oRequest.getSession().invalidate();
        return new ReplyBean(200, EncodingHelper.quotate("OK"));
    }

    public ReplyBean check() throws Exception {
        ReplyBean oReplyBean;
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
        } else {
            oReplyBean = new ReplyBean(401, "No active session");
        }
        return oReplyBean;
    }

}
