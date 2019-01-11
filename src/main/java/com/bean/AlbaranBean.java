/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.ClienteDao;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author a021792876p
 */
public class AlbaranBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int factura;
    @Expose
    private int estado;
    @Expose
    private Date fecha;

    @Expose(serialize = false)
    private int id_cliente;

    @Expose(serialize = false)
    private String nombre_cliente;

    @Expose(deserialize = false)
    private ClienteBean obj_Cliente;

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

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public ClienteBean getObj_Cliente() {
        return obj_Cliente;
    }

    public void setObj_Cliente(ClienteBean obj_Cliente) {
        this.obj_Cliente = obj_Cliente;
    }

    public AlbaranBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setFecha(oResultSet.getDate("fecha"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setEstado(oResultSet.getInt("estado"));
        this.setFactura(oResultSet.getInt("id_dat140a"));
        this.setId_cliente(oResultSet.getInt("cliente"));
        this.setNombre_cliente(oResultSet.getString("nombre"));

        if (expand > 0) {
            ClienteDao oClienteDao = new ClienteDao(oConnection, "dat001a");
            this.setObj_Cliente(oClienteDao.get(oResultSet.getInt("cliente"),oResultSet.getInt("id_ejercicio"),expand -1));
        }
        return this;

    }
}
