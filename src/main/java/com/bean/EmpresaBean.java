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

    @Expose(serialize = false)
    private int id;
    @Expose
    private Integer empresa;
    @Expose(serialize = false)
    private String nombre;
    @Expose(serialize = false)
    private String nif;
    @Expose
    private Integer ejercicio;
    @Expose
    private String direccion;
    @Expose
    private String telefono;
    @Expose
    private String poblacion;
    @Expose
    private String provincia;
    @Expose
    private Integer cp;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public EmpresaBean fill(ResultSet oResultSet, Connection oConnection) throws Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setEmpresa(oResultSet.getInt("empresa"));
        this.setEjercicio(oResultSet.getInt("ejercicio"));
        this.setNif(oResultSet.getString("nif"));
        this.setCp(oResultSet.getInt("cp"));
        this.setDireccion(oResultSet.getString("direccion"));
        this.setPoblacion(oResultSet.getString("poblacion"));
        this.setProvincia(oResultSet.getString("provincia"));
        return this;
    }

    @Override
    public String toString() {
        return "EmpresaBean{" + "id=" + id + ", empresa=" + empresa + ", nombre=" + nombre + ", nif=" + nif + ", ejercicio=" + ejercicio + '}';
    }
    
    
    
}
