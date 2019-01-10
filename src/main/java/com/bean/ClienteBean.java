/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.google.gson.annotations.Expose;
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
        this.setId(oResultSet.getInt("id"));
        this.setCodigo(oResultSet.getInt("codigo"));
        this.setDireccion(oResultSet.getString("direccion"));
        this.setEmail(oResultSet.getString("email"));
        this.setEmpresa(oResultSet.getInt("empresa"));
        this.setNif(oResultSet.getString("nif"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setRazonsocial(oResultSet.getString("razonsocial"));
        this.setTelefono(oResultSet.getString("telefono"));
        return this;
    }
}
