/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.ClienteDao;
import com.dao.LineaFacturaDao;
import com.google.gson.annotations.Expose;
import com.helper.EncodingHelper;
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

    @Expose
    private double descuento;

    @Expose
    private double iva;

    @Expose
    private double total_Iva;

    @Expose
    private double total_bruto;

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

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal_Iva() {
        return total_Iva;
    }

    public void setTotal_Iva(double total_Iva) {
        this.total_Iva = total_Iva;
    }

    public double getTotal_bruto() {
        return total_bruto;
    }

    public void setTotal_bruto(double total_bruto) {
        this.total_bruto = total_bruto;
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
        this.setDescuento(oResultSet.getDouble("descuento"));
        this.setIva(oResultSet.getDouble("iva2"));
        this.setTotal_Iva(oResultSet.getDouble("totaliva2"));
        this.setTotal_bruto(oResultSet.getDouble("totalbruto"));
        if (expand > 0) {
            ClienteDao oClienteDao = new ClienteDao(oConnection, "dat001a");
            LineaFacturaDao oLineaFacturaDao = new LineaFacturaDao(oConnection, "dat141a");
            this.setObj_LineaFactura(oLineaFacturaDao.getpage(1000, 1, oResultSet.getInt("id_ejercicio"), oResultSet.getInt("id_auto")));
            this.setObj_Cliente(oClienteDao.get(oResultSet.getInt("cliente"), oResultSet.getInt("id_ejercicio"), expand - 1));
        }
        return this;

    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id_auto,";
        strColumns += "factura,";
        strColumns += "numalbaran,";
        strColumns += "fecha,";
        strColumns += "id_ejercicio,";
        strColumns += "cliente,";
        strColumns += "nombre,";
        strColumns += "contabilizada,";
        strColumns += "totalfactura,";
        strColumns += "descuento,";
        strColumns += "iva2,";
        strColumns += "totaliva2,";
        strColumns += "totalbruto";
        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "id_auto=" + id + ",";
        strPairs += "factura=" + factura + ",";
        strPairs += "numalbaran=" + num_albaran + ",";
        strPairs += "fecha=" + fecha + ",";
        strPairs += "id_ejercicio=" + empresa + ",";
        strPairs += "cliente=" + id_cliente + ",";
        strPairs += "nombre=" + EncodingHelper.quotate(nombre_cliente) + ",";
        strPairs += "contabilizada=" + EncodingHelper.quotate(contabilizada) + ",";
        strPairs += "totalfactura=" + total_precio + ",";
        strPairs += "descuento=" + descuento + ",";
        strPairs += "iva2=" + iva + ",";
        strPairs += "totaliva2=" + total_Iva + ",";
        strPairs += "totalbruto=" + total_bruto;
        return strPairs;

    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += factura + ",";
        strColumns += num_albaran + ",";
        strColumns += fecha + ",";
        strColumns += empresa + ",";
        strColumns += id_cliente + ",";
        strColumns += EncodingHelper.quotate(nombre_cliente) + ",";
        strColumns += EncodingHelper.quotate(contabilizada) + ",";
        strColumns += total_precio + ",";
        strColumns += descuento + ",";
        strColumns += iva + ",";
        strColumns += total_Iva + ",";
        strColumns += total_bruto;

        return strColumns;
    }

}
