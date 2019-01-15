/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.ProductoBean;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author a021792876p
 */
public class ProductoDao {

    Connection oConnection;
    String ob = null;

    public ProductoDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public ProductoBean get(String id,int expand, int empresa) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE artcodigo = ? and id_ejercicio = ?";
        ProductoBean oProductoBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, EncodingHelper.quotate(id));
            oPreparedStatement.setInt(2, empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oProductoBean = new ProductoBean();
                oProductoBean.setId(oResultSet.getInt("id_auto"));
                oProductoBean.setEmpresa(oResultSet.getInt("id_ejercicio"));
                oProductoBean.setCodigo(oResultSet.getString("artcodigo"));
                oProductoBean.setDescripcion(oResultSet.getString("artdescripcion"));
                oProductoBean.setPvp1(oResultSet.getInt("artpvp1"));
                oProductoBean.setPvp2(oResultSet.getInt("artpvp2"));
                oProductoBean.setPvp3(oResultSet.getInt("artpvp3"));
                oProductoBean.setExistencia_albaran(oResultSet.getInt("artalbaranes"));
                oProductoBean.setExistencias(oResultSet.getInt("artexistencias"));
            } else {
                oProductoBean = null;
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
        return oProductoBean;
    }

    public ArrayList<ProductoBean> getpage(int iRpp, int iPage, int empresa) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ?";
        ArrayList<ProductoBean> alProductoBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oResultSet = oPreparedStatement.executeQuery();
                alProductoBean = new ArrayList<ProductoBean>();
                while (oResultSet.next()) {
                    ProductoBean oProductoBean = new ProductoBean();
                    oProductoBean = new ProductoBean();
                    oProductoBean.setId(oResultSet.getInt("id_auto"));
                    oProductoBean.setEmpresa(oResultSet.getInt("id_ejercicio"));
                    oProductoBean.setCodigo(oResultSet.getString("artcodigo"));
                    oProductoBean.setDescripcion(oResultSet.getString("artdescripcion"));
                    oProductoBean.setPvp1(oResultSet.getInt("artpvp1"));
                    oProductoBean.setPvp2(oResultSet.getInt("artpvp2"));
                    oProductoBean.setPvp3(oResultSet.getInt("artpvp3"));
                    oProductoBean.setExistencia_albaran(oResultSet.getInt("artalbaranes"));
                    oProductoBean.setExistencias(oResultSet.getInt("artexistencias"));
                    alProductoBean.add(oProductoBean);
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
        return alProductoBean;

    }
}
