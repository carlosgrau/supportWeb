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
public class LineaPresupuestoBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int id_dat032a;
    @Expose
    private int id_presupuesto;
    @Expose
    private String referencia;
    @Expose
    private String descripcion;
    @Expose
    private float precio;
    @Expose
    private float cantidad;
    @Expose
    private float precio_Total;
    @Expose
    private ProductoBean obj_Producto;
    @Expose
    private double descuento;

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

    public int getId_dat032a() {
        return id_dat032a;
    }

    public void setId_dat032a(int id_dat032a) {
        this.id_dat032a = id_dat032a;
    }

     public int getId_presupuesto() {
        return id_presupuesto;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public float getPrecio_Total() {
        return precio_Total;
    }

    public void setPrecio_Total(float precio_Total) {
        this.precio_Total = precio_Total;
    }

    public ProductoBean getObj_Producto() {
        return obj_Producto;
    }

    public void setObj_Producto(ProductoBean obj_Producto) {
        this.obj_Producto = obj_Producto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public LineaPresupuestoBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setCantidad(oResultSet.getInt("cantidad"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setId_dat032a(oResultSet.getInt("id_dat032a"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setId_presupuesto(oResultSet.getInt("presupuestos"));
        this.setPrecio(oResultSet.getFloat("preciounitario"));
        this.setReferencia(oResultSet.getString("referencia"));
        this.setDescuento(oResultSet.getDouble("d"));
        if (expand > 0) {
            ProductoDao oProductoDao = new ProductoDao(oConnection, "dat004a");
            this.setObj_Producto(oProductoDao.get(oResultSet.getString("referencia"), expand - 1, this.getEmpresa()));
        }
        return this;

    }

}
