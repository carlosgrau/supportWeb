/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.EmpresaBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.connection.publicinterface.ConnectionInterface;
import com.dao.EmpresaDao;
import com.google.gson.Gson;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Carlos
 */
public class EmpresaService {

    HttpServletRequest oRequest;
    String ob = null;

    public EmpresaService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }
    
    
    public ReplyBean get() throws Exception {
		ReplyBean oReplyBean;
		Connection oConnection;
                UsuarioBean oUsuarioBean = null;
		try {
			Integer id = Integer.parseInt(oRequest.getParameter("id"));
			oConnection = oUsuarioBean.newConnectionClient();
			EmpresaDao oEmpresaDao = new EmpresaDao(oConnection, ob);
			EmpresaBean oEmpresaBean = oEmpresaDao.get(id);
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(oEmpresaBean));
		} catch (Exception ex) {
			throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
    
    
}
