/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.AlbaranBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author a021792876p
 */
public class AlbaranDao {

    Connection oConnection;
    String ob = null;

    public AlbaranDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public AlbaranBean get(int id, int empresa, int expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_auto = ? and id_ejercicio = ?";
        AlbaranBean oAlbaranBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oPreparedStatement.setInt(2, empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oAlbaranBean = new AlbaranBean();
                oAlbaranBean.fill(oResultSet, oConnection, expand);
            } else {
                oAlbaranBean = null;
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
        return oAlbaranBean;
    }

    public ArrayList<AlbaranBean> getpage(int iRpp, int iPage, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ?";
        ArrayList<AlbaranBean> alAlbaranBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oResultSet = oPreparedStatement.executeQuery();
                alAlbaranBean = new ArrayList<AlbaranBean>();
                while (oResultSet.next()) {
                    AlbaranBean oAlbaranBean = new AlbaranBean();
                    oAlbaranBean.fill(oResultSet, oConnection, expand);
                    alAlbaranBean.add(oAlbaranBean);
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
        return alAlbaranBean;

    }

    public ArrayList<AlbaranBean> getpageXusuario(int iRpp, int iPage, int empresa, Integer expand, Integer cliente) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ? and cliente= ?";
        ArrayList<AlbaranBean> alAlbaranBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oPreparedStatement.setInt(2, cliente);
                oResultSet = oPreparedStatement.executeQuery();
                alAlbaranBean = new ArrayList<AlbaranBean>();
                while (oResultSet.next()) {
                    AlbaranBean oAlbaranBean = new AlbaranBean();
                    oAlbaranBean.fill(oResultSet, oConnection, expand);
                    alAlbaranBean.add(oAlbaranBean);
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
        return alAlbaranBean;

    }
    public AlbaranBean create(AlbaranBean oAlbaranBean) throws Exception {
		String strSQL = "INSERT INTO " + ob + " ( "+ob+".id,  "+ob+".fecha,  "+ob+".iva, "+ob+".id_usuario) VALUES (NULL, ?,?,?); ";
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setDate(1, (Date) oAlbaranBean.getFecha());
                        oPreparedStatement.setInt(2, oAlbaranBean.getEmpresa());
                        oPreparedStatement.setInt(2, oAlbaranBean.getEstado());
                        oPreparedStatement.setInt(2, oAlbaranBean.getFactura());
                        oPreparedStatement.setInt(2, oAlbaranBean.getId_cliente());
                        oPreparedStatement.setString(2, oAlbaranBean.getNombre_cliente());
                        //oPreparedStatement.setInt(3, oAlbaranBean.getObj_Cliente());
			oPreparedStatement.executeUpdate();
			oResultSet = oPreparedStatement.getGeneratedKeys();
			if (oResultSet.next()) {
				oAlbaranBean.setId(oResultSet.getInt(1));
			} else {
				oAlbaranBean.setId(0);
			}
		} catch (SQLException e) {
			throw new Exception("Error en Dao create de " + ob, e);
		} finally {
			if (oResultSet != null) {
				oResultSet.close();
			}
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return oAlbaranBean;
	}
}
