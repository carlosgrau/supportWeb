/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.TipoIvaDao;
import com.google.gson.annotations.Expose;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author a021792876p
 */
public class ProductoBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private String codigo;
    @Expose
    private String descripcion;
    @Expose
    private int existencias;
    @Expose
    private int existencia_albaran;
    @Expose
    private float pvp1;
    @Expose
    private float pvp2;
    @Expose
    private float pvp3;
    @Expose
    private int tipoiva;
    @Expose
    private TipoIvaBean obj_tipoIva;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getExistencia_albaran() {
        return existencia_albaran;
    }

    public void setExistencia_albaran(int existencia_albaran) {
        this.existencia_albaran = existencia_albaran;
    }

    public float getPvp1() {
        return pvp1;
    }

    public void setPvp1(float pvp1) {
        this.pvp1 = pvp1;
    }

    public float getPvp2() {
        return pvp2;
    }

    public void setPvp2(float pvp2) {
        this.pvp2 = pvp2;
    }

    public float getPvp3() {
        return pvp3;
    }

    public void setPvp3(float pvp3) {
        this.pvp3 = pvp3;
    }

    public TipoIvaBean getObj_tipoIva() {
        return obj_tipoIva;
    }

    public void setObj_tipoIva(TipoIvaBean obj_tipoIva) {
        this.obj_tipoIva = obj_tipoIva;
    }

    public int getTipoiva() {
        return tipoiva;
    }

    public void setTipoiva(int tipoiva) {
        this.tipoiva = tipoiva;
    }

    public ProductoBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setCodigo(oResultSet.getString("artcodigo"));
        this.setDescripcion(oResultSet.getString("artdescripcion"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setExistencia_albaran(oResultSet.getInt("artalbaranes"));
        this.setExistencias(oResultSet.getInt("artexistencias"));
        this.setPvp1(oResultSet.getInt("artpvp1"));
        this.setPvp2(oResultSet.getInt("artpvp2"));
        this.setPvp3(oResultSet.getInt("artpvp3"));
        this.setTipoiva(oResultSet.getInt("arttipoiva1"));

        if (expand > 0) {
            TipoIvaDao oTipoIvaDao = new TipoIvaDao(oConnection, "dat007a");
            this.setObj_tipoIva(oTipoIvaDao.get(oResultSet.getInt("arttipoiva1"), oResultSet.getInt("id_ejercicio"), expand - 1));
        }
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id_auto,";
        strColumns += "artcodigo,";
        strColumns += "artdescripcion,";
        strColumns += "id_ejercicio,";
        strColumns += "artalbaranes,";
        strColumns += "artexistencias,";
        strColumns += "artpvp1,";
        strColumns += "artpvp2,";
        strColumns += "artpvp3,";
        strColumns += "arttipoiva1";
        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(codigo) + ",";
        strColumns += EncodingHelper.quotate(descripcion) + ",";
        strColumns += empresa + ",";
        strColumns += existencia_albaran + ",";
        strColumns += existencias + ",";
        strColumns += pvp1 + ",";
        strColumns += pvp2 + ",";
        strColumns += pvp3;
        strColumns += tipoiva;

        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "id_auto=" + id + ",";
        strPairs += "artcodigo=" + EncodingHelper.quotate(codigo) + ",";
        strPairs += "artdescripcion=" + EncodingHelper.quotate(descripcion) + ",";
        strPairs += "id_ejercicio=" + empresa + ",";
        strPairs += "artalbaranes=" + existencia_albaran + ",";
        strPairs += "artexistencias=" + existencias + ",";
        strPairs += "artpvp1=" + pvp1 + ",";
        strPairs += "artpvp2=" + pvp2 + ",";
        strPairs += "artpvp2=" + pvp3 + ",";
        strPairs += "arttipoiva1=" + tipoiva;
        return strPairs;

    }
}
