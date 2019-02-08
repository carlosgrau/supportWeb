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
public class ClienteBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int codigo;
    @Expose
    private String nif;
    @Expose
    private String nombre;
    @Expose
    private String razonsocial;
    @Expose
    private String telefono;
    @Expose
    private String direccion;
    @Expose
    private String email;

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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClienteBean fill(ResultSet oResultSet, Connection oConnection) throws Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setCodigo(oResultSet.getInt("clicodigo"));
        this.setDireccion(oResultSet.getString("clidireccion"));
        this.setEmail(oResultSet.getString("cliemail"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setNif(oResultSet.getString("clinif"));
        this.setNombre(oResultSet.getString("clinombre"));
        this.setRazonsocial(oResultSet.getString("clirazonsocial"));
        this.setTelefono(oResultSet.getString("clitelefono1"));
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id_auto,";
        strColumns += "clicodigo,";
        strColumns += "clidireccion,";
        strColumns += "cliemail,";
        strColumns += "id_ejercicio,";
        strColumns += "clinif,";
        strColumns += "clinombre,";
        strColumns += "clirazonsocial,";
        strColumns += "clitelefono1";
        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += codigo + ",";
        strColumns += EncodingHelper.quotate(direccion) + ",";
        strColumns += EncodingHelper.quotate(email) + ",";
        strColumns += empresa + ",";
        strColumns += EncodingHelper.quotate(nif) + ",";
        strColumns += EncodingHelper.quotate(nombre) + ",";
        strColumns += EncodingHelper.quotate(razonsocial) + ",";
        strColumns += EncodingHelper.quotate(telefono);

        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "id_auto=" + id + ",";
        strPairs += "clicodigo=" + codigo + ",";
        strPairs += "clidireccion=" + EncodingHelper.quotate(direccion) + ",";
        strPairs += "cliemail=" + EncodingHelper.quotate(email) + ",";
        strPairs += "id_ejercicio=" + empresa + ",";
        strPairs += "clinif=" + EncodingHelper.quotate(nif) + ",";
        strPairs += "clinombre=" + EncodingHelper.quotate(nombre) + ",";
        strPairs += "clirazonsocial=" + EncodingHelper.quotate(razonsocial) + ",";
        strPairs += "clitelefono1=" + EncodingHelper.quotate(telefono);
        return strPairs;

    }
}
