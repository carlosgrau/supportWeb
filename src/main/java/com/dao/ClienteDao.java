/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.ClienteBean;
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
public class ClienteDao {

    Connection oConnection;
    String ob = null;

    public ClienteDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public ClienteBean get(int id, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE clicodigo = ? and id_ejercicio = ?";
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
                oClienteBean.fill(oResultSet, oConnection);
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

    public ArrayList<ClienteBean> getpage(int iRpp, int iPage, int empresa, HashMap<String, String> hmOrder) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ?";
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
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
                    oClienteBean.fill(oResultSet, oConnection);
                    alClienteBean.add(oClienteBean);
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
        return alClienteBean;

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

    public ClienteBean create(ClienteBean oClienteBean) throws Exception {
        String strSQL = "INSERT INTO " + ob;
        strSQL += "(" + oClienteBean.getColumns() + ")";
        strSQL += " VALUES ";
        strSQL += "(" + oClienteBean.getValues() + ")";
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oClienteBean.setId(oResultSet.getInt(1));
            } else {
                oClienteBean.setId(0);
                oClienteBean.setCodigo(0);
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
        return oClienteBean;
    }

    public int update(ClienteBean oClienteBean) throws Exception {
        int iResult = 0;
        String strSQL = "UPDATE " + ob + " SET ";
        strSQL += oClienteBean.getPairs();
        strSQL = strSQL + " WHERE dat001a.CliCodigo = " + oClienteBean.getCodigo()
                + " AND dat001a.id_ejercicio=" + oClienteBean.getEmpresa()
                + " AND dat001a.id_auto=" + oClienteBean.getId();
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
    public int remove(int id,int empresa) throws Exception {
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
