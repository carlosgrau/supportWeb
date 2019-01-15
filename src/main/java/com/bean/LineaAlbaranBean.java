/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.ProductoDao;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author a021792876p
 */
public class LineaAlbaranBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int codigo;
    @Expose
    private int id_albaran;
    @Expose
    private String descripcion;
    @Expose
    private String referencia;
    @Expose
    private float precio;
    @Expose
    private float cantidad;
    @Expose
    private ProductoBean obj_Producto;

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

    public int getId_albaran() {
        return id_albaran;
    }

    public void setId_albaran(int id_albaran) {
        this.id_albaran = id_albaran;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoBean getObj_Producto() {
        return obj_Producto;
    }

    public void setObj_Producto(ProductoBean obj_Producto) {
        this.obj_Producto = obj_Producto;
    }

    public LineaAlbaranBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setCantidad(oResultSet.getInt("cantidad"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setCodigo(oResultSet.getInt("codigo"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setId_albaran(oResultSet.getInt("id_dat031a"));
        //this.setPrecio(oResultSet.getFloat("preciounitario")*oResultSet.getFloat('cantidad'));
        this.setReferencia(oResultSet.getString("referencia"));

        if (expand > 0) {
            ProductoDao oProductoDao = new ProductoDao(oConnection, "producto");
            this.setObj_Producto(oProductoDao.get(oResultSet.getInt("codigo"), expand - 1));
        }
        return this;

    }
}
