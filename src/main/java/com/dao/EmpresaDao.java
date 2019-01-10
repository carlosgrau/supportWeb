/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.EmpresaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class EmpresaDao {

    Connection oConnection;
    String ob = null;

    public EmpresaDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public EmpresaBean get(int id) throws Exception {
        String strSQL = "SELECT * FROM " + ob+" WHERE id_auto = ?";
        EmpresaBean oEmpresaBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oEmpresaBean = new EmpresaBean();
                oEmpresaBean.setId(oResultSet.getInt("id_auto"));
                oEmpresaBean.setEjercicio(oResultSet.getInt("ejercicio"));
                oEmpresaBean.setEmpresa(oResultSet.getInt("empresa"));
                oEmpresaBean.setNombre(oResultSet.getString("nombre"));
                oEmpresaBean.setNif(oResultSet.getString("nif"));
            } else {
                oEmpresaBean = null;
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oEmpresaBean;
    }
    
    public ArrayList<EmpresaBean> getpage(int iRpp, int iPage) throws Exception {
		String strSQL = "SELECT * FROM " + ob;
		ArrayList<EmpresaBean> alEmpresaBean;
		if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
			strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
			ResultSet oResultSet = null;
			PreparedStatement oPreparedStatement = null;
			try {
				oPreparedStatement = oConnection.prepareStatement(strSQL);
				oResultSet = oPreparedStatement.executeQuery();
				alEmpresaBean = new ArrayList<EmpresaBean>();
				while (oResultSet.next()) {
					EmpresaBean oEmpresaBean = new EmpresaBean();
                                        oEmpresaBean.setId(oResultSet.getInt("id_auto"));
                                        oEmpresaBean.setEjercicio(oResultSet.getInt("ejercicio"));
                                        oEmpresaBean.setEmpresa(oResultSet.getInt("empresa"));
                                        oEmpresaBean.setNombre(oResultSet.getString("nombre"));
                                        oEmpresaBean.setNif(oResultSet.getString("nif"));
					alEmpresaBean.add(oEmpresaBean);
				}
			} catch (SQLException e) {
				throw new Exception("Error en Dao getpage de " + ob, e);
			} finally {
				if (oResultSet != null) {
					oResultSet.close();
				}
				if (oPreparedStatement != null) {
					oPreparedStatement.close();
				}
			}
		} else {
			throw new Exception("Error en Dao getpage de " + ob);
		}
		return alEmpresaBean;

	}
}
