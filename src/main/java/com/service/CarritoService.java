/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.FacturaBean;
import com.bean.ItemBean;
import com.bean.LineaFacturaBean;
import com.bean.LineaPresupuestoBean;
import com.bean.PresupuestoBean;
import com.bean.ProductoBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.connection.publicinterface.ConnectionInterface;
import com.connection.specificimplementation.HikariConnectionForUser;
import com.dao.FacturaDao;
import com.dao.LineaFacturaDao;
import com.dao.LineaPresupuestoDao;
import com.dao.PresupuestoDao;
import com.dao.ProductoDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class CarritoService {

    Gson oGson = new Gson();
    ArrayList<ItemBean> carrito = null;
    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public CarritoService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
    }

    public ReplyBean buyFactura() throws Exception {

        ReplyBean oReplyBean = null;
        ConnectionInterface oConnectionPool = null;
        //Obtenemos la sesion actual
        HttpSession sesion = oRequest.getSession();
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();
        try {

            Integer id = Integer.parseInt(oRequest.getParameter("id"));
            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            oConnection.setAutoCommit(false);
            carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");

            FacturaBean oFacturaBean = new FacturaBean();
            FacturaDao oFacturaDao = new FacturaDao(oConnection, "dat140a");
            FacturaBean oFacturaBeanCreada = (FacturaBean) oFacturaDao.create(oFacturaBean);
            int id_factura = oFacturaBeanCreada.getFactura();

            LineaFacturaDao oLineaFacturaDao;
            LineaFacturaBean oLineaFacturaBean;

            ProductoDao oProductoDao = new ProductoDao(oConnection, "dat004a");
            oLineaFacturaDao = new LineaFacturaDao(oConnection, "dat141a");
            ProductoBean oProductoBean;

            for (ItemBean ib : carrito) {

                float cant = ib.getCantidad();

                oLineaFacturaBean = new LineaFacturaBean();

                oLineaFacturaBean.setId_factura(id_factura);
                oLineaFacturaBean.setEmpresa(empresa);
                oLineaFacturaBean.setObj_Producto(ib.getObj_producto());
                oLineaFacturaBean.setCantidad(cant);

                oLineaFacturaDao.create(oLineaFacturaBean);

                oProductoBean = new ProductoBean();

                oProductoBean.setId(ib.getObj_producto().getId());

                oProductoBean = ib.getObj_producto();

                oProductoBean.setExistencias((int) (oProductoBean.getExistencias() - cant));

                oProductoDao.update(oProductoBean);

            }

            oConnection.commit();

            carrito.clear();
            sesion.setAttribute("carrito", carrito);

            oReplyBean = new ReplyBean(200, oGson.toJson(id_factura));
        } catch (Exception e) {

            try {
                oConnection.rollback();
            } catch (SQLException excep) {

            }

            oReplyBean = new ReplyBean(500, "Error en buy carritoService: " + e.getMessage());
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;

    }

    public ReplyBean buyPresupuesto() throws Exception {

        ReplyBean oReplyBean = null;
        ConnectionInterface oConnectionPool = null;
        //Obtenemos la sesion actual
        HttpSession sesion = oRequest.getSession();
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();
        try {

            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();
            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            oConnection.setAutoCommit(false);
            carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");

            PresupuestoBean oPresupuestoBean = new PresupuestoBean();
            PresupuestoDao oPresupuestoDao = new PresupuestoDao(oConnection, "dat032a");
            String strJsonFromClient = oRequest.getParameter("json");
            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            oPresupuestoBean = oGson.fromJson(strJsonFromClient, PresupuestoBean.class);

            //SACO EL ULTIMO NUMERO DEL PRESUPUESTO
            Integer presupuesto = oPresupuestoDao.getpresupuesto(empresa);
            oPresupuestoBean.setPresupuesto(presupuesto + 1);

            //ANTES DE CREAR LA CABECERA DE PRESUPESTO TENEMOS QUE METER EN EL BEAN LOS DATOS DEL JSON
            PresupuestoBean oPresupuestoBeanCreada = (PresupuestoBean) oPresupuestoDao.create(oPresupuestoBean);
            int id_presupuesto = oPresupuestoBeanCreada.getId();

            LineaPresupuestoDao oLineaPresupuestoDao;
            LineaPresupuestoBean oLineaPresupuestoBean;

            ProductoDao oProductoDao = new ProductoDao(oConnection, "dat004a");
            oLineaPresupuestoDao = new LineaPresupuestoDao(oConnection, "dat033a");
            ProductoBean oProductoBean = null;

            for (ItemBean ib : carrito) {

                float cant = ib.getCantidad();
                float descuento = ib.getDescuento();
                float precio = ib.getPrecio();
                float preciototal = cant * precio;
                ProductoBean obprod = ib.getObj_producto();
                oLineaPresupuestoBean = new LineaPresupuestoBean();

                oLineaPresupuestoBean.setId_presupuesto(presupuesto + 1);
                oLineaPresupuestoBean.setId_dat032a(id_presupuesto);
                oLineaPresupuestoBean.setEmpresa(empresa);
                oLineaPresupuestoBean.setObj_Producto(ib.getObj_producto());
                oLineaPresupuestoBean.setCantidad(cant);
                oLineaPresupuestoBean.setDescuento(descuento);
                oLineaPresupuestoBean.setPrecio(precio);

                if (descuento == 0) {

                    oLineaPresupuestoBean.setPrecio_Total(preciototal);
                } else if (descuento > 0) {
                    oLineaPresupuestoBean.setPrecio_Total(preciototal - (preciototal * (descuento / 100)));
                }
                oLineaPresupuestoBean.setReferencia(obprod.getCodigo());
                oLineaPresupuestoBean.setDescripcion(obprod.getDescripcion());

                oLineaPresupuestoDao.create(oLineaPresupuestoBean);

                oProductoBean = new ProductoBean();

                oProductoBean.setId(ib.getObj_producto().getId());

                oProductoBean = ib.getObj_producto();

                oProductoBean.setExistencias((int) (oProductoBean.getExistencias() - cant));

                oProductoDao.update(oProductoBean);

            }

            oConnection.commit();

            carrito.clear();
            sesion.setAttribute("carrito", carrito);

            oReplyBean = new ReplyBean(200, oGson.toJson(id_presupuesto));
        } catch (Exception e) {

            try {
                oConnection.rollback();
            } catch (SQLException excep) {

            }

            oReplyBean = new ReplyBean(500, "Error en buy carritoService: " + e.getMessage());
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;

    }

    public ReplyBean add() throws Exception {
        ReplyBean oReplyBean = null;
        ConnectionInterface oConnectionPool = null;
        //Obtenemos la sesion actual
        HttpSession sesion = oRequest.getSession();
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();

        try {
            Integer empresa = Integer.parseInt(oRequest.getParameter("ejercicio"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();

            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);
            //Si no existe la sesion creamos al carrito
            if (sesion.getAttribute("carrito") == null) {
                carrito = new ArrayList<ItemBean>();
            } else {
                carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");
            }

            //Obtenemos el producto que deseamos a�adir al carrito
            String codigo = oRequest.getParameter("codigo");
            float cantidadprod = Float.parseFloat(oRequest.getParameter("cantidad"));
            float descuentoprod = Float.parseFloat(oRequest.getParameter("descuento"));
            float precioprod = Float.parseFloat(oRequest.getParameter("precio"));

            ProductoDao oProductoDao = new ProductoDao(oConnection, "dat004a");
            ProductoBean oProductoBean = (ProductoBean) oProductoDao.get(codigo, 1, empresa);

            //Para saber si tenemos agregado el producto al carrito de compras
            int indice = -1;
            //recorremos todo el carrito de compras
            for (int i = 0; i < carrito.size(); i++) {
                if (oProductoBean.getId() == carrito.get(i).getObj_producto().getId()) {
                    //Si el producto ya esta en el carrito, obtengo el indice dentro
                    //del arreglo para actualizar al carrito de compras
                    indice = i;
                    break;
                }
            }
            ItemBean oItemBean = new ItemBean();
            if (indice == -1) {
                //Si es -1 es porque voy a registrar
                
                    oItemBean.setObj_producto(oProductoBean);
                    oItemBean.setCantidad(cantidadprod);
                    oItemBean.setDescuento(descuentoprod);
                    oItemBean.setPrecio(precioprod);
                    carrito.add(oItemBean);
            }
            //Actualizamos la sesion del carrito de compras
            sesion.setAttribute("carrito", carrito);

            oReplyBean = new ReplyBean(200, oGson.toJson(carrito));

        } catch (Exception ex) {
            // Logger.getLogger(CarritoService.class.getName()).log(Level.SEVERE, null, ex);
            oReplyBean = new ReplyBean(500, "Error en add carrito: " + ex.getMessage());
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }
        return oReplyBean;

    }

    public ReplyBean show() throws Exception {
        ReplyBean oReplyBean;

        HttpSession sesion = oRequest.getSession();

        if (sesion.getAttribute("carrito") == null) {
            oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Carrito vacio"));
        } else {
            oReplyBean = new ReplyBean(200, oGson.toJson(sesion.getAttribute("carrito")));
        }

        return oReplyBean;

    }

    public ReplyBean empty() {
        ReplyBean oReplyBean;

        HttpSession sesion = oRequest.getSession();

        if (sesion.getAttribute("carrito") == null) {
            oReplyBean = new ReplyBean(200, EncodingHelper.quotate("El carrito ya esta vacio"));
        } else {
            sesion.setAttribute("carrito", null);
            oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Carrito vacio"));
        }

        return oReplyBean;

    }

    public ReplyBean reduce() throws Exception {

        ReplyBean oReplyBean;

        HttpSession sesion = oRequest.getSession();

        if (sesion.getAttribute("carrito") == null) {
            oReplyBean = new ReplyBean(200, EncodingHelper.quotate("No hay carrito"));
        } else {
            carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");
            Integer id = Integer.parseInt(oRequest.getParameter("prod"));

            int indice = -1;

            for (int i = 0; i < carrito.size(); i++) {
                if (id == carrito.get(i).getObj_producto().getId()) {
                    indice = i;
                    break;
                }
            }

            if (indice == -1) {
                oReplyBean = new ReplyBean(200, EncodingHelper.quotate("El producto no esta en el carrito"));
            } else {
                float cantidad = carrito.get(indice).getCantidad();
                if (carrito.get(indice).getCantidad() > 1) {
                    carrito.get(indice).setCantidad(cantidad - 1);
                    sesion.setAttribute("carrito", carrito);
                    oReplyBean = new ReplyBean(200, oGson.toJson(carrito));
                } else {
                    carrito.remove(indice);
                }

                if (carrito.size() < 1) {
                    sesion.setAttribute("carrito", null);
                    oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Carrito vacio"));

                } else {
                    sesion.setAttribute("carrito", carrito);
                    oReplyBean = new ReplyBean(200, oGson.toJson(carrito));
                }

            }
        }
        return oReplyBean;

    }

}
