/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.FormaPagoDao;
import com.dao.RepresentanteDao;
import com.google.gson.annotations.Expose;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author a021792876p
 */
public class ClienteBean {

    @Expose
    private int id;
    @Expose
    private int empresa;
    @Expose
    private int codigo;
    @Expose
    private String nif;
    @Expose
    private String nombre;
    @Expose
    private String razonsocial;
    @Expose
    private String telefono;
    @Expose
    private String direccion;
    @Expose
    private String email;
    @Expose
    private String iban;
    @Expose
    private String codigopostal;
    @Expose
    private String poblacion;
    @Expose
    private String provincia;
    @Expose
    private Integer formapago;
    @Expose
    private Integer representate;
    @Expose
    private Integer tarifa;
    @Expose
    private RepresentanteBean objRepresentante;
    @Expose
    private FormaPagoBean objFormaPago;

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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
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

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

    public Integer getFormapago() {
        return formapago;
    }

    public void setFormapago(Integer formapago) {
        this.formapago = formapago;
    }

    public Integer getRepresentate() {
        return representate;
    }

    public void setRepresentate(Integer representate) {
        this.representate = representate;
    }

    public RepresentanteBean getObjRepresentante() {
        return objRepresentante;
    }

    public void setObjRepresentante(RepresentanteBean objRepresentante) {
        this.objRepresentante = objRepresentante;
    }

    public FormaPagoBean getObjFormaPago() {
        return objFormaPago;
    }

    public void setObjFormaPago(FormaPagoBean objFormaPago) {
        this.objFormaPago = objFormaPago;
    }

    public ClienteBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
        this.setId(oResultSet.getInt("id_auto"));
        this.setCodigo(oResultSet.getInt("clicodigo"));
        this.setDireccion(oResultSet.getString("clidireccion"));
        this.setEmail(oResultSet.getString("cliemail"));
        this.setEmpresa(oResultSet.getInt("id_ejercicio"));
        this.setNif(oResultSet.getString("clinif"));
        this.setNombre(oResultSet.getString("clinombre"));
        this.setRazonsocial(oResultSet.getString("clirazonsocial"));
        this.setTelefono(oResultSet.getString("clitelefono1"));
        this.setIban(oResultSet.getString("iban"));
        this.setCodigopostal(oResultSet.getString("clicodpostal"));
        this.setPoblacion(oResultSet.getString("clipoblacion"));
        this.setProvincia(oResultSet.getString("cliprovincia"));
        this.setRepresentate(oResultSet.getInt("clirepresentante"));
        this.setFormapago(oResultSet.getInt("clifpago"));
        this.setTarifa(oResultSet.getInt("clitarifahabitual"));
        if (expand > 0) {
            RepresentanteDao oRepresentanteDao = new RepresentanteDao(oConnection, "dat005a");
            FormaPagoDao oFormaPagoDao = new FormaPagoDao(oConnection, "dat006a");
            this.setObjRepresentante(oRepresentanteDao.get(oResultSet.getInt("clirepresentante"), oResultSet.getInt("id_ejercicio"), 1));
            this.setObjFormaPago(oFormaPagoDao.get(oResultSet.getInt("clifpago"), oResultSet.getInt("id_ejercicio"), expand - 1));
        }
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id_auto,";
        strColumns += "clicodigo,";
        strColumns += "clidireccion,";
        strColumns += "cliemail,";
        strColumns += "id_ejercicio,";
        strColumns += "clinif,";
        strColumns += "clinombre,";
        strColumns += "clirazonsocial,";
        strColumns += "clitelefono1,";
        strColumns += "iban,";
        strColumns += "clicodpostal,";
        strColumns += "clipoblacion,";
        strColumns += "cliprovincia,";
        strColumns += "clirepresentante,";
        strColumns += "clifpago,";
        strColumns += "clitarifahabitual";

        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += codigo + ",";
        strColumns += EncodingHelper.quotate(direccion) + ",";
        strColumns += EncodingHelper.quotate(email) + ",";
        strColumns += empresa + ",";
        strColumns += EncodingHelper.quotate(nif) + ",";
        strColumns += EncodingHelper.quotate(nombre) + ",";
        strColumns += EncodingHelper.quotate(razonsocial) + ",";
        strColumns += EncodingHelper.quotate(telefono) + ",";
        strColumns += EncodingHelper.quotate(iban) + ",";
        strColumns += EncodingHelper.quotate(codigopostal) + ",";
        strColumns += EncodingHelper.quotate(poblacion) + ",";
        strColumns += EncodingHelper.quotate(provincia) + ",";
        strColumns += representate + ",";
        strColumns += formapago;
        strColumns += tarifa;

        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "id_auto=" + id + ",";
        strPairs += "clicodigo=" + codigo + ",";
        strPairs += "clidireccion=" + EncodingHelper.quotate(direccion) + ",";
        strPairs += "cliemail=" + EncodingHelper.quotate(email) + ",";
        strPairs += "id_ejercicio=" + empresa + ",";
        strPairs += "clinif=" + EncodingHelper.quotate(nif) + ",";
        strPairs += "clinombre=" + EncodingHelper.quotate(nombre) + ",";
        strPairs += "clirazonsocial=" + EncodingHelper.quotate(razonsocial) + ",";
        strPairs += "clitelefono1=" + EncodingHelper.quotate(telefono) + ",";
        strPairs += "iban=" + EncodingHelper.quotate(iban) + ",";
        strPairs += "clicodpostal=" + EncodingHelper.quotate(codigopostal) + ",";
        strPairs += "clipoblacion=" + EncodingHelper.quotate(poblacion) + ",";
        strPairs += "cliprovincia=" + EncodingHelper.quotate(provincia) + ",";
        strPairs += "clirepresentante=" + representate + ",";
        strPairs += "clifpago=" + formapago + ",";
        strPairs += "clitarifahabitual=" + formapago;
        return strPairs;

    }
}
