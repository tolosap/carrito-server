/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CarritoBean;
import bean.LineadepedidoBean;
import bean.PedidoBean;
import bean.ProductoBean;
import bean.ReplyBean;
import bean.UsuarioBean;
import com.google.gson.Gson;
import connection.ConnectionInterface;
import dao.LineadepedidoDao;
import dao.PedidoDao;
import dao.ProductoDao;
import helper.AppConfigurationHelper;
import helper.Log4j;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import service.interfaces.EmptyServiceInterface;

/**
 *
 * @author a023321991z
 */
public class CarritoService implements EmptyServiceInterface {

    HttpServletRequest oRequest = null;

    public CarritoService(HttpServletRequest request) {
        oRequest = request;
    }

    private Boolean checkPermission(String strMethodName) {
        UsuarioBean oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            return true;
        } else {
            return false;
        }
    }

    private CarritoBean find(ArrayList<CarritoBean> alCarrito, CarritoBean oCarritoBean) {
        Iterator<CarritoBean> iterator = alCarrito.iterator();
        while (iterator.hasNext()) {
            CarritoBean oCart = iterator.next();
            if (oCarritoBean.getObj_prod().getId() == (oCart.getObj_prod().getId())) {
                return oCart;
            }
        }
        return null;
    }

    private CarritoBean findRemove(ArrayList<CarritoBean> alCarrito, int idremove) {
        Iterator<CarritoBean> iterator = alCarrito.iterator();
        while (iterator.hasNext()) {
            CarritoBean oCart = iterator.next();
            if (idremove == (oCart.getObj_prod().getId())) {
                return oCart;
            }
        }
        return null;
    }

    public ReplyBean add() throws Exception {
        if (this.checkPermission("add")) {
            ArrayList<CarritoBean> alCart = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBean oReplyBean = null;
            CarritoBean oCarBean = null;
            int intid = Integer.parseInt(oRequest.getParameter("id"));
            int newstock = Integer.parseInt(oRequest.getParameter("cantidad"));
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                //DAO
                ProductoBean oBean = new ProductoBean(intid);
                ProductoDao oDao = new ProductoDao(oConnection);
                oBean = oDao.get(oBean, AppConfigurationHelper.getJsonMsgDepth());
                oCarBean = new CarritoBean(newstock, oBean);

                //COMPROBAMOS SU EXISTENCIA
                CarritoBean oCarrito = find(alCart, oCarBean);

                //EN CASO DE QUE NO EXISTA, LO CREAMOS Y ANYADIMOS
                if (oCarrito == null) {
                    CarritoBean oCartBean = new CarritoBean(newstock, oBean);
                    alCart.add(oCartBean);
                } //SI YA EXISTE, MODIFICAMOS LOS DATOS
                else {
                    Integer oldStock = oCarrito.getCantidad();
                    oCarrito.setCantidad(oldStock + newstock);
                }

                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(alCart);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
    }

    public ReplyBean list() throws Exception {
        if (this.checkPermission("list")) {
            ArrayList<CarritoBean> alCart = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBean oReplyBean = null;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(alCart);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
    }

    public ReplyBean remove() throws Exception {
        if (this.checkPermission("remove")) {
            ArrayList<CarritoBean> alCart = (ArrayList) oRequest.getSession().getAttribute("carrito");
            int idremove = Integer.parseInt(oRequest.getParameter("id"));
            ReplyBean oReplyBean = null;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                //recorremos el carrito para ver si existe algun objeto con el id identico al que pedimos
                CarritoBean oCarrito = findRemove(alCart, idremove);
                alCart.remove(oCarrito);

                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(alCart);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
    }

    public ReplyBean empty() throws Exception {
        if (this.checkPermission("empty")) {
            ArrayList<CarritoBean> alCart = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBean oReplyBean = null;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                alCart.clear();

                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(alCart);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
    }

    public ReplyBean buy() throws Exception {
        if (this.checkPermission("buy")) {
            ArrayList<CarritoBean> alCart = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBean oReplyBean = null;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            Date fecha = new Date(2017 / 10 / 26);
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                /*if (alCart == null) {

                } else {*/
                UsuarioBean oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

                Integer longArray = alCart.size();
                PedidoBean oPedBean = new PedidoBean(0, fecha, oUsuarioBean);
                PedidoDao oPedDao = new PedidoDao(oConnection);
                oPedBean.setId(oPedDao.set(oPedBean));
                for (int i = 0; i < longArray; i++) {
                    LineadepedidoBean oBean = new LineadepedidoBean(0, alCart.get(i).getObj_prod(), oPedBean);
                    LineadepedidoDao oLinea = new LineadepedidoDao(oConnection);
                    oLinea.set(oBean);
                    
                    ProductoDao oProdDao = new ProductoDao(oConnection);
                    oProdDao.comprar(alCart.get(i).getObj_prod().getId(), alCart.get(i).getCantidad());
                }
                alCart.clear();
                //}
                //Gson oGson = AppConfigurationHelper.getGson();
                //String strJson = oGson.toJson(alCart);
                oReplyBean = new ReplyBean(200, "Compra realizada correctamente");
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBean(401, "Unauthorized");
        }
    }

}
