/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.RepresentanteBean;
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
public class RepresentanteDao {

    Connection oConnection;
    String ob = null;

    public RepresentanteDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public RepresentanteBean get(int id, int empresa, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE forcodigo = ? and id_ejercicio = ?";
        RepresentanteBean oRepresentanteBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oPreparedStatement.setInt(2, empresa);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oRepresentanteBean = new RepresentanteBean();
                oRepresentanteBean.fill(oResultSet, oConnection);
            } else {
                oRepresentanteBean = null;
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

        return oRepresentanteBean;
    }

    public ArrayList<RepresentanteBean> getpage(int iRpp, int iPage, int empresa, HashMap<String, String> hmOrder) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_ejercicio= ?";
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<RepresentanteBean> alRepresentanteBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, empresa);
                oResultSet = oPreparedStatement.executeQuery();
                alRepresentanteBean = new ArrayList<RepresentanteBean>();
                while (oResultSet.next()) {
                    RepresentanteBean oRepresentanteBean = new RepresentanteBean();
                    oRepresentanteBean = new RepresentanteBean();
                    oRepresentanteBean.fill(oResultSet, oConnection);
                    alRepresentanteBean.add(oRepresentanteBean);
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
        return alRepresentanteBean;

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

    public RepresentanteBean create(RepresentanteBean oRepresentanteBean) throws Exception {
        String strSQL = "INSERT INTO " + ob;
        strSQL += "(" + oRepresentanteBean.getColumns() + ")";
        strSQL += " VALUES ";
        strSQL += "(" + oRepresentanteBean.getValues() + ")";
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oRepresentanteBean.setId(oResultSet.getInt(1));
            } else {
                oRepresentanteBean.setId(0);
                oRepresentanteBean.setCodigo(0);
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
        return oRepresentanteBean;
    }

    public int update(RepresentanteBean oRepresentanteBean) throws Exception {
        int iResult = 0;
        String strSQL = "UPDATE " + ob + " SET ";
        strSQL += oRepresentanteBean.getPairs();
        strSQL = strSQL + " WHERE dat006a.ForCodigo = " + oRepresentanteBean.getCodigo()
                + " AND dat006a.id_ejercicio=" + oRepresentanteBean.getEmpresa()
                + " AND dat006a.id_auto=" + oRepresentanteBean.getId();
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
