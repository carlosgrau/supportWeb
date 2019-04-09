/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.PresupuestoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author a021792876p
 */
public class PresupuestoDao {

    Connection oConnection;
    String ob = null;

    public PresupuestoDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public PresupuestoBean get(int id, int empresa, int expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_auto = ? and id_ejercicio = ?";
        PresupuestoBean oPresupuestoBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oPreparedStatement.setInt(2, empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oPresupuestoBean = new PresupuestoBean();
                oPresupuestoBean.fill(oResultSet, oConnection, expand);
            } else {
                oPresupuestoBean = null;
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
        return oPresupuestoBean;
    }

    public ArrayList<PresupuestoBean> getpage(int iRpp, int iPage, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ?";
        ArrayList<PresupuestoBean> alPresupuestoBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oResultSet = oPreparedStatement.executeQuery();
                alPresupuestoBean = new ArrayList<PresupuestoBean>();
                while (oResultSet.next()) {
                    PresupuestoBean oPresupuestoBean = new PresupuestoBean();
                    oPresupuestoBean.fill(oResultSet, oConnection, expand);
                    alPresupuestoBean.add(oPresupuestoBean);
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
        return alPresupuestoBean;

    }
    public ArrayList<PresupuestoBean> getpageXusuario(int iRpp, int iPage, int empresa, Integer expand, Integer cliente) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ? and cliente= ?";
        ArrayList<PresupuestoBean> alPresupuestoBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oPreparedStatement.setInt(2, cliente);
                oResultSet = oPreparedStatement.executeQuery();
                alPresupuestoBean = new ArrayList<PresupuestoBean>();
                while (oResultSet.next()) {
                    PresupuestoBean oPresupuestoBean = new PresupuestoBean();
//                    oPresupuestoBean = new PresupuestoBean();
//                    oPresupuestoBean.setId(oResultSet.getInt("id_auto"));
//                    oPresupuestoBean.setEmpresa(oResultSet.getInt("id_ejercicio"));
//                    oPresupuestoBean.setEstado(oResultSet.getInt("estado"));
//                    oPresupuestoBean.setPresupuesto(oResultSet.getInt("id_dat140a"));
//                    oPresupuestoBean.setFecha(oResultSet.getDate("fecha"));
//                    oPresupuestoBean.setId_cliente(oResultSet.getInt("cliente"));
//                    oPresupuestoBean.setNombre_cliente(oResultSet.getString("nombre"));
                    oPresupuestoBean.fill(oResultSet, oConnection, expand);
                    alPresupuestoBean.add(oPresupuestoBean);
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
        return alPresupuestoBean;

    }
}
