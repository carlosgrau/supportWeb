/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.LineaAlbaranBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GADE
 */
public class LineaAlbaranDao {

    Connection oConnection;
    String ob = null;

    public LineaAlbaranDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public LineaAlbaranBean get(int id, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
        LineaAlbaranBean oLineaAlbaranBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oLineaAlbaranBean = new LineaAlbaranBean();
                oLineaAlbaranBean.setPrecio_Total(oResultSet.getFloat("cantidad") * oResultSet.getFloat("preciounitario"));
                oLineaAlbaranBean.fill(oResultSet, oConnection, 1);
            } else {
                oLineaAlbaranBean = null;
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
        return oLineaAlbaranBean;
    }

    public ArrayList<LineaAlbaranBean> getpage(int iRpp, int iPage, int empresa, int albaran) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " where id_ejercicio= " + empresa + " and id_dat031a = " + albaran;
        ArrayList<LineaAlbaranBean> alLineaAlbaranBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alLineaAlbaranBean = new ArrayList<LineaAlbaranBean>();
                while (oResultSet.next()) {
                    LineaAlbaranBean oLineaAlbaranBean = new LineaAlbaranBean();
                    oLineaAlbaranBean = new LineaAlbaranBean();
                    oLineaAlbaranBean.setPrecio_Total(oResultSet.getFloat("cantidad") * oResultSet.getFloat("preciounitario"));
                    oLineaAlbaranBean.fill(oResultSet, oConnection, 1);
                    alLineaAlbaranBean.add(oLineaAlbaranBean);
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
        return alLineaAlbaranBean;

    }
}
