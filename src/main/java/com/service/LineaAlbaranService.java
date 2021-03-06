/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.LineaAlbaranBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.connection.publicinterface.ConnectionInterface;
import com.connection.specificimplementation.HikariConnectionForUser;
import com.dao.LineaAlbaranDao;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author GADE
 */
public class LineaAlbaranService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public LineaAlbaranService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        if ("lineaalbaran".equals(oRequest.getParameter("ob"))) {
            ob = "dat131a";
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
            LineaAlbaranDao oLineaAlbaranDao = new LineaAlbaranDao(oConnection, ob);
            LineaAlbaranBean oLineaAlbaranBean = oLineaAlbaranDao.get(id, empresa, 1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oLineaAlbaranBean));
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
            Integer albaran = Integer.parseInt(oRequest.getParameter("albaran"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            LineaAlbaranDao oLineaAlbaranDao = new LineaAlbaranDao(oConnection, ob);

            ArrayList<LineaAlbaranBean> alLineaAlbaranBean = oLineaAlbaranDao.getpage(iRpp, iPage, empresa, albaran);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(alLineaAlbaranBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: getpage method: " + ob + " object", ex);
        } finally {
            oConnection.close();
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;

    }
}
