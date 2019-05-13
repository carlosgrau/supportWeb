/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.TipoIvaBean;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GADE
 */
public class TipoIvaDao {

    Connection oConnection;
    String ob = null;

    public TipoIvaDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public TipoIvaBean get(int id, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE codigo=? and id_ejercicio=?";
        TipoIvaBean oTipoIvaBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1,id);
            oPreparedStatement.setInt(2,empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oTipoIvaBean = new TipoIvaBean();
                oTipoIvaBean.fill(oResultSet, oConnection);
            } else {
                oTipoIvaBean = null;
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
        return oTipoIvaBean;
    }

    public ArrayList<TipoIvaBean> getpage(int iRpp, int iPage, int empresa, int presupuesto) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " where id_ejercicio= " + empresa + " and presupuestos = " + presupuesto;
        ArrayList<TipoIvaBean> alTipoIvaBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alTipoIvaBean = new ArrayList<TipoIvaBean>();
                while (oResultSet.next()) {
                    TipoIvaBean oTipoIvaBean = new TipoIvaBean();
                    oTipoIvaBean = new TipoIvaBean();
                    oTipoIvaBean.fill(oResultSet, oConnection);
                    alTipoIvaBean.add(oTipoIvaBean);
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
        return alTipoIvaBean;

    }

    public TipoIvaBean create(TipoIvaBean oTipoIvaBean) throws Exception {
        String strSQL = "INSERT INTO " + ob;
        strSQL += "(" + oTipoIvaBean.getColumns() + ")";
        strSQL += " VALUES ";
        strSQL += "(" + oTipoIvaBean.getValues() + ")";
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oTipoIvaBean.setId(oResultSet.getInt(1));
            } else {
                oTipoIvaBean.setId(0);
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
        return oTipoIvaBean;
    }
}
