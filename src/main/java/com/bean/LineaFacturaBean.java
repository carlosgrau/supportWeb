/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.ProductoDao;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author a021792876p
 */
public class LineaFacturaBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int id_factura;
    @Expose
    private int id_dat140a;
    @Expose
    private String descripcion;
    @Expose
    private String referencia;
    @Expose
    private float precio;
    @Expose
    private float cantidad;
    @Expose
    private float precio_Total;
    @Expose
    private int albaran;
    @Expose
    private double descuento;
    @Expose
    private Date fecha_albaran;
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

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_dat140a() {
        return id_dat140a;
    }

    public void setId_dat140a(int id_dat140a) {
        this.id_dat140a = id_dat140a;
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

    public float getPrecio_Total() {
        return precio_Total;
    }

    public void setPrecio_Total(float precio_Total) {
        this.precio_Total = precio_Total;
    }

    public int getAlbaran() {
        return albaran;
    }

    public void setAlbaran(int albaran) {
        this.albaran = albaran;
    }

    public Date getFecha_albaran() {
        return fecha_albaran;
    }

    public void setFecha_albaran(Date fecha_albaran) {
        this.fecha_albaran = fecha_albaran;
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

    public LineaFacturaBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setId_dat140a(oResultSet.getInt("factura"));
        this.setId_factura(oResultSet.getInt("id_dat140a"));
        this.setReferencia(oResultSet.getString("referencia"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setPrecio(oResultSet.getFloat("importe"));
        this.setCantidad(oResultSet.getInt("cantidad"));
        this.setPrecio_Total(oResultSet.getFloat("total"));
        this.setAlbaran(oResultSet.getInt("albaran"));
        this.setFecha_albaran(oResultSet.getDate("fechaalbaran"));
        this.setDescuento(oResultSet.getDouble("dto"));
        if (expand > 0) {
            ProductoDao oProductoDao = new ProductoDao(oConnection, "dat004a");
            this.setObj_Producto(oProductoDao.get(oResultSet.getString("referencia"), expand - 1, this.getEmpresa()));
        }
        return this;

    }
}
