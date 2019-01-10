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
 * @author Carlos
 */
public class EmpresaBean {

    @Expose
    private int id;
    @Expose
    private Integer empresa;
    @Expose
    private String nombre;
    @Expose
    private String nif;
    @Expose
    private Integer ejercicio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public EmpresaBean fill(ResultSet oResultSet, Connection oConnection) throws Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setEmpresa(oResultSet.getInt("empresa"));
        this.setEjercicio(oResultSet.getInt("ejercicio"));
        this.setNif(oResultSet.getString("nif"));
        return this;
    }
}
