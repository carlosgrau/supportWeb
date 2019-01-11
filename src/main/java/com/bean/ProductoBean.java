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

    public ProductoBean fill(ResultSet oResultSet, Connection oConnection) throws Exception {
        this.setId(oResultSet.getInt("id"));
        this.setCodigo(oResultSet.getString("codigo"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setEmpresa(oResultSet.getInt("empresa"));
        this.setExistencia_albaran(oResultSet.getInt("existencias_albaran"));
        this.setExistencias(oResultSet.getInt("existencias"));
        this.setPvp1(oResultSet.getInt("pvp1"));
        this.setPvp2(oResultSet.getInt("pvp2"));
        this.setPvp3(oResultSet.getInt("pvp3"));
        return this;
    }

}
