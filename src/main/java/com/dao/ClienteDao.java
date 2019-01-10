/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.ClienteBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author a021792876p
 */
public class ClienteDao {

    Connection oConnection;
    String ob = null;

    public ClienteDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public ClienteBean get(int id, int empresa) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_auto = ? and id_ejercicio = ?";
        ClienteBean oClienteBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oPreparedStatement.setInt(2, empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oClienteBean = new ClienteBean();
                oClienteBean.setId(oResultSet.getInt("id_auto"));
                oClienteBean.setEmpresa(oResultSet.getInt("id_ejercicio"));
                oClienteBean.setCodigo(oResultSet.getInt("clicodigo"));
                oClienteBean.setNombre(oResultSet.getString("clinombre"));
                oClienteBean.setRazonsocial(oResultSet.getString("clirazonsocial"));
                oClienteBean.setTelefono(oResultSet.getString("clitelefono1"));
                oClienteBean.setNif(oResultSet.getString("clinif"));
                oClienteBean.setDireccion(oResultSet.getString("clidireccion"));
                oClienteBean.setEmail(oResultSet.getString("cliemail"));
            } else {
                oClienteBean = null;
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
        return oClienteBean;
    }

    public ArrayList<ClienteBean> getpage(int iRpp, int iPage, int empresa) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ?";
        ArrayList<ClienteBean> alClienteBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oResultSet = oPreparedStatement.executeQuery();
                alClienteBean = new ArrayList<ClienteBean>();
                while (oResultSet.next()) {
                    ClienteBean oClienteBean = new ClienteBean();
                    oClienteBean = new ClienteBean();
                    oClienteBean.setId(oResultSet.getInt("id_auto"));
                    oClienteBean.setEmpresa(oResultSet.getInt("id_ejercicio"));
                    oClienteBean.setCodigo(oResultSet.getInt("clicodigo"));
                    oClienteBean.setNombre(oResultSet.getString("clinombre"));
                    oClienteBean.setRazonsocial(oResultSet.getString("clirazonsocial"));
                    oClienteBean.setTelefono(oResultSet.getString("clitelefono1"));
                    oClienteBean.setNif(oResultSet.getString("clinif"));
                    oClienteBean.setDireccion(oResultSet.getString("clidireccion"));
                    oClienteBean.setEmail(oResultSet.getString("cliemail"));
                    alClienteBean.add(oClienteBean);
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
        return alClienteBean;

    }
}
