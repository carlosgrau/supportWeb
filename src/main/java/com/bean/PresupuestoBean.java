/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.ClienteDao;
import com.dao.LineaPresupuestoDao;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author a021792876p
 */
public class PresupuestoBean {

    @Expose
    private int id;
    
    @Expose
    private int empresa;
    
    @Expose
    private int presupuesto;
    
    @Expose
    private int id_cliente;
    
    @Expose
    private String nombre;
    
    @Expose
    private Date fecha;
    
    @Expose
    private float precio_Bruto;
    
    @Expose
    private float total_Precio;
    
    @Expose(deserialize = false)
    private ClienteBean obj_Cliente;
    
    @Expose(deserialize = false)
    private ArrayList<LineaPresupuestoBean> obj_LineaPresupuesto;

    
    
    
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

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio_Bruto() {
        return precio_Bruto;
    }

    public void setPrecio_Bruto(float precio_Bruto) {
        this.precio_Bruto = precio_Bruto;
    }

    public float getTotal_Precio() {
        return total_Precio;
    }

    public void setTotal_Precio(float total_Precio) {
        this.total_Precio = total_Precio;
    }

    public ClienteBean getObj_Cliente() {
        return obj_Cliente;
    }

    public void setObj_Cliente(ClienteBean obj_Cliente) {
        this.obj_Cliente = obj_Cliente;
    }

    public ArrayList<LineaPresupuestoBean> getObj_LineaPresupuesto() {
        return obj_LineaPresupuesto;
    }

    public void setObj_LineaPresupuesto(ArrayList<LineaPresupuestoBean> obj_LineaPresupuesto) {
        this.obj_LineaPresupuesto = obj_LineaPresupuesto;
    }

    public PresupuestoBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setFecha(oResultSet.getDate("fecha"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setPresupuesto(oResultSet.getInt("presupuesto"));
        this.setId_cliente(oResultSet.getInt("cliente"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPrecio_Bruto(oResultSet.getFloat("totalbruto"));
        this.setTotal_Precio(oResultSet.getFloat("total"));

        if (expand > 0) {
            ClienteDao oClienteDao = new ClienteDao(oConnection, "dat001a");
            LineaPresupuestoDao oLineaPresupuestoDao = new LineaPresupuestoDao(oConnection, "dat033a");
            this.setObj_LineaPresupuesto(oLineaPresupuestoDao.getpage(1000, 1, oResultSet.getInt("id_ejercicio"), oResultSet.getInt("presupuesto")));
            this.setObj_Cliente(oClienteDao.get(oResultSet.getInt("cliente"), oResultSet.getInt("id_ejercicio"), expand - 1));
        }
        return this;

    }

}
