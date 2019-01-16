/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.ClienteDao;
import com.dao.LineaFacturaDao;
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
public class FacturaBean {

    @Expose
    private int id;

    @Expose
    private int empresa;

    @Expose
    private int factura;

    @Expose
    private int num_albaran;

    @Expose
    private Date fecha;

    @Expose(serialize = false)
    private int id_cliente;

    @Expose(serialize = false)
    private String nombre_cliente;

    @Expose
    private String contabilizada;

    @Expose
    private float total_precio;

    @Expose(deserialize = false)
    private ClienteBean obj_Cliente;

    @Expose(deserialize = false)
    private ArrayList<LineaFacturaBean> obj_LineaFactura;

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

    public int getNum_albaran() {
        return num_albaran;
    }

    public void setNum_albaran(int num_albaran) {
        this.num_albaran = num_albaran;
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

    public String getContabilizada() {
        return contabilizada;
    }

    public void setContabilizada(String contabilizada) {
        this.contabilizada = contabilizada;
    }

    public float getTotal_precio() {
        return total_precio;
    }

    public void setTotal_precio(float total_precio) {
        this.total_precio = total_precio;
    }

    public ClienteBean getObj_Cliente() {
        return obj_Cliente;
    }

    public void setObj_Cliente(ClienteBean obj_Cliente) {
        this.obj_Cliente = obj_Cliente;
    }

    public ArrayList<LineaFacturaBean> getObj_LineaFactura() {
        return obj_LineaFactura;
    }

    public void setObj_LineaFactura(ArrayList<LineaFacturaBean> obj_LineaFactura) {
        this.obj_LineaFactura = obj_LineaFactura;
    }

    public FacturaBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setFactura(oResultSet.getInt("factura"));
        this.setNum_albaran(oResultSet.getInt("numalbaran"));
        this.setFecha(oResultSet.getDate("fecha"));
        this.setId_cliente(oResultSet.getInt("cliente"));
        this.setNombre_cliente(oResultSet.getString("nombre"));
        this.setContabilizada(oResultSet.getString("contabilizada"));
        this.setTotal_precio(oResultSet.getFloat("totalfactura"));

        if (expand > 0) {
            ClienteDao oClienteDao = new ClienteDao(oConnection, "dat001a");
            LineaFacturaDao oLineaFacturaDao = new LineaFacturaDao(oConnection, "dat141a");
            this.setObj_LineaFactura(oLineaFacturaDao.getpage(1000, 1, oResultSet.getInt("id_ejercicio"), oResultSet.getInt("id_auto")));
            this.setObj_Cliente(oClienteDao.get(oResultSet.getInt("cliente"), oResultSet.getInt("id_ejercicio"), expand - 1));
        }
        return this;

    }
}
