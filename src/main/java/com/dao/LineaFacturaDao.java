/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.LineaFacturaBean;
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
public class LineaFacturaDao {

    Connection oConnection;
    String ob = null;

    public LineaFacturaDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public LineaFacturaBean get(String id, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
        LineaFacturaBean oLineaFacturaBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, EncodingHelper.quotate(id));
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oLineaFacturaBean = new LineaFacturaBean();
                oLineaFacturaBean.setPrecio_Total(oResultSet.getFloat("cantidad") * oResultSet.getFloat("preciounitario"));
                oLineaFacturaBean.fill(oResultSet, oConnection, 1);
            } else {
                oLineaFacturaBean = null;
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
        return oLineaFacturaBean;
    }

    public ArrayList<LineaFacturaBean> getpage(int iRpp, int iPage, int empresa, int Factura) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " where id_ejercicio= " + empresa + " and id_dat140a = " + Factura;
        ArrayList<LineaFacturaBean> alLineaFacturaBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alLineaFacturaBean = new ArrayList<LineaFacturaBean>();
                while (oResultSet.next()) {
                    LineaFacturaBean oLineaFacturaBean = new LineaFacturaBean();
                    oLineaFacturaBean = new LineaFacturaBean();
                    oLineaFacturaBean.setPrecio_Total(oResultSet.getFloat("cantidad") * oResultSet.getFloat("importe"));
                    oLineaFacturaBean.fill(oResultSet, oConnection, 1);
                    alLineaFacturaBean.add(oLineaFacturaBean);
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
        return alLineaFacturaBean;

    }
}
