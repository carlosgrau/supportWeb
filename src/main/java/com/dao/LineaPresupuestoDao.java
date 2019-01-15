/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.LineaPresupuestoBean;
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
public class LineaPresupuestoDao {

    Connection oConnection;
    String ob = null;

    public LineaPresupuestoDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public LineaPresupuestoBean get(String id, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
        LineaPresupuestoBean oLineaPresupuestoBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, EncodingHelper.quotate(id));
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oLineaPresupuestoBean = new LineaPresupuestoBean();
                oLineaPresupuestoBean.setPrecio_Total(oResultSet.getFloat("cantidad") * oResultSet.getFloat("preciounitario"));
                oLineaPresupuestoBean.fill(oResultSet, oConnection, 1);
            } else {
                oLineaPresupuestoBean = null;
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
        return oLineaPresupuestoBean;
    }

    public ArrayList<LineaPresupuestoBean> getpage(int iRpp, int iPage, int empresa, int presupuesto) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " where id_ejercicio= " + empresa + " and id_dat032a = " + presupuesto;
        ArrayList<LineaPresupuestoBean> alLineaPresupuestoBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alLineaPresupuestoBean = new ArrayList<LineaPresupuestoBean>();
                while (oResultSet.next()) {
                    LineaPresupuestoBean oLineaPresupuestoBean = new LineaPresupuestoBean();
                    oLineaPresupuestoBean = new LineaPresupuestoBean();
                    oLineaPresupuestoBean.setPrecio_Total(oResultSet.getFloat("cantidad") * oResultSet.getFloat("preciounitario"));
                    oLineaPresupuestoBean.fill(oResultSet, oConnection, 1);
                    alLineaPresupuestoBean.add(oLineaPresupuestoBean);
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
        return alLineaPresupuestoBean;

    }
}
