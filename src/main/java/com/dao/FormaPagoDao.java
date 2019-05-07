/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.FormaPagoBean;
import com.helper.SqlBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author a021792876p
 */
public class FormaPagoDao {

    Connection oConnection;
    String ob = null;

    public FormaPagoDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public FormaPagoBean get(int id, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE forcodigo = ? and id_ejercicio = ?";
        FormaPagoBean oFormaPagoBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oPreparedStatement.setInt(2, empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oFormaPagoBean = new FormaPagoBean();
                oFormaPagoBean.fill(oResultSet, oConnection);
            } else {
                oFormaPagoBean = null;
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

        return oFormaPagoBean;
    }

    public ArrayList<FormaPagoBean> getpage(int iRpp, int iPage, int empresa, HashMap<String, String> hmOrder) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ?";
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<FormaPagoBean> alFormaPagoBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oResultSet = oPreparedStatement.executeQuery();
                alFormaPagoBean = new ArrayList<FormaPagoBean>();
                while (oResultSet.next()) {
                    FormaPagoBean oFormaPagoBean = new FormaPagoBean();
                    oFormaPagoBean = new FormaPagoBean();
                    oFormaPagoBean.fill(oResultSet, oConnection);
                    alFormaPagoBean.add(oFormaPagoBean);
                }
            } catch (SQLException e) {
                throw new Exception("Error en Dao getpage de " + ob + "------" + e, e);
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
        //oConnection.close();
        return alFormaPagoBean;

    }

    public int getcount(int empresa) throws Exception {
        String strSQL = "SELECT COUNT(id_auto) FROM " + ob + " WHERE id_ejercicio= ?";
        int res = 0;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                res = oResultSet.getInt(1);
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
        //oConnection.close();
        return res;
    }

    public FormaPagoBean create(FormaPagoBean oFormaPagoBean) throws Exception {
        String strSQL = "INSERT INTO " + ob;
        strSQL += "(" + oFormaPagoBean.getColumns() + ")";
        strSQL += " VALUES ";
        strSQL += "(" + oFormaPagoBean.getValues() + ")";
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oFormaPagoBean.setId(oResultSet.getInt(1));
            } else {
                oFormaPagoBean.setId(0);
                oFormaPagoBean.setCodigo(0);
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
        return oFormaPagoBean;
    }

    public int update(FormaPagoBean oFormaPagoBean) throws Exception {
        int iResult = 0;
        String strSQL = "UPDATE " + ob + " SET ";
        strSQL += oFormaPagoBean.getPairs();
        strSQL = strSQL + " WHERE dat006a.ForCodigo = " + oFormaPagoBean.getCodigo()
                + " AND dat006a.id_ejercicio=" + oFormaPagoBean.getEmpresa()
                + " AND dat006a.id_auto=" + oFormaPagoBean.getId();
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            iResult = oPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error en Dao update de " + ob, e);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    public int remove(int id, int empresa) throws Exception {
        int iRes = 0;
        String strSQL = "DELETE FROM " + ob + " WHERE id_auto=?";
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            iRes = oPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en Dao remove de " + ob, e);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iRes;
    }
}
