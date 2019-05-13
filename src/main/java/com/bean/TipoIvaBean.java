/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.google.gson.annotations.Expose;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author GADE
 */
public class TipoIvaBean {
    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int codigo;
    @Expose
    private String descripcion;
    @Expose
    private int porcentaje;

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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
     public TipoIvaBean fill(ResultSet oResultSet, Connection oConnection) throws Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setCodigo(oResultSet.getInt("codigo"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setPorcentaje(oResultSet.getInt("porcentaje"));
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id_auto,";
        strColumns += "codigo,";
        strColumns += "descripcion,";
        strColumns += "id_ejercicio,";
        strColumns += "porcentaje";

        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += codigo + ",";
        strColumns += EncodingHelper.quotate(descripcion) + ",";
        strColumns += empresa + ",";
        strColumns += porcentaje ;
        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "id_auto=" + id + ",";
        strPairs += "codigo=" + codigo + ",";
        strPairs += "descripcion=" + EncodingHelper.quotate(descripcion) + ",";
        strPairs += "id_ejercicio=" + empresa + ",";
        strPairs += "porcentaje=" + porcentaje ;

        return strPairs;

    }
}
