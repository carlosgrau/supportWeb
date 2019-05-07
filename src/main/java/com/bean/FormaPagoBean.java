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
 * @author a021792876p
 */
public class FormaPagoBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int codigo;
    @Expose
    private String nombre;


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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public FormaPagoBean fill(ResultSet oResultSet, Connection oConnection) throws Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setCodigo(oResultSet.getInt("forcodigo"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setNombre(oResultSet.getString("fornombre"));
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id_auto,";
        strColumns += "forcodigo,";
        strColumns += "id_ejercicio,";
        strColumns += "fornombre";

        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += codigo + ",";
        strColumns += empresa + ",";
        strColumns += EncodingHelper.quotate(nombre);
        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "id_auto=" + id + ",";
        strPairs += "forcodigo=" + codigo + ",";
        strPairs += "id_ejercicio=" + empresa + ",";
        strPairs += "fornombre=" + EncodingHelper.quotate(nombre);
        return strPairs;

    }
}
