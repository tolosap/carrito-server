/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.interfaces.TableServiceInterface;
import service.interfaces.EmptyServiceInterface;
import service.interfaces.ViewServiceInterface;
import bean.PedidoBean;
import bean.ProductoBean;
import com.google.gson.Gson;
import bean.ReplyBean;
import bean.UsuarioBean;
import connection.ConnectionInterface;
import dao.PedidoDao;
import dao.UsuarioDao;
import helper.AppConfigurationHelper;
import helper.FilterBeanHelper;
import helper.Log4j;
import helper.ParameterCook;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;

public class PedidoService implements EmptyServiceInterface, ViewServiceInterface, TableServiceInterface {

    HttpServletRequest oRequest = null;

    public PedidoService(HttpServletRequest request) {
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

    /*
    * http://127.0.0.1:8081/conexion/json?ob=usuario&op=get&id=1
     */
    @Override
    public ReplyBean get() throws Exception {
        if (this.checkPermission("get")) {
            int id = Integer.parseInt(oRequest.getParameter("id"));
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                
                PedidoBean oBean = new PedidoBean(id);
                PedidoDao oDao = new PedidoDao(oConnection);
                oBean = oDao.get(oBean, AppConfigurationHelper.getJsonMsgDepth());
                
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(oBean);
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

    @Override
    public ReplyBean getpage() throws Exception {
        if (this.checkPermission("getpage")) {
            int np = Integer.parseInt(oRequest.getParameter("np"));
            int rpp = Integer.parseInt(oRequest.getParameter("rpp"));
            String strOrder = oRequest.getParameter("order");
            String strFilter = oRequest.getParameter("filter");
            LinkedHashMap<String, String> hmOrder = ParameterCook.getOrderParams(strOrder);
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            ArrayList<PedidoBean> aloBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                
                PedidoDao oDao = new PedidoDao(oConnection);
                aloBean = oDao.getPage(rpp, np, hmOrder, alFilter);
                
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(aloBean);
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

    @Override
    public ReplyBean getcount() throws Exception {
        if (this.checkPermission("getcount")) {
            Long lResult;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            String strFilter = oRequest.getParameter("filter");
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                
                PedidoDao oDao = new PedidoDao(oConnection);
                lResult = oDao.getCount(alFilter);
                
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(lResult);
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

    @Override
    public ReplyBean set() throws Exception {
        if (this.checkPermission("set")) {
            String jason = oRequest.getParameter("jason");
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            PedidoBean oBean = new PedidoBean();
            Gson oGson = AppConfigurationHelper.getGson();
            oBean = oGson.fromJson(jason, oBean.getClass());
            if (oBean == null) {
                throw new Exception("Bean null en service set");
            }
            int iResult = 0;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                
                PedidoDao oDao = new PedidoDao(oConnection);
                iResult = oDao.set(oBean);
                
                String strJson = oGson.toJson(iResult);
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

    @Override
    public ReplyBean remove() throws Exception {
        if (this.checkPermission("remove")) {
            int id = Integer.parseInt(oRequest.getParameter("id"));
            Boolean iResult = false;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                
                PedidoDao oDao = new PedidoDao(oConnection);
                iResult = oDao.remove(id);
                
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(iResult);
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
    
    public ReplyBean getpagexusuario() throws Exception {
        if (this.checkPermission("getpage")) {
            int np = Integer.parseInt(oRequest.getParameter("np"));
            int rpp = Integer.parseInt(oRequest.getParameter("rpp"));
            int id = Integer.parseInt(oRequest.getParameter("id"));
            String strOrder = oRequest.getParameter("order");
            String strFilter = oRequest.getParameter("filter");
            LinkedHashMap<String, String> hmOrder = ParameterCook.getOrderParams(strOrder);
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            ArrayList<PedidoBean> aloBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                
                PedidoDao oDao = new PedidoDao(oConnection);
                aloBean = oDao.getPagexusuario(rpp, np, hmOrder, alFilter, id);
                Gson oGson = AppConfigurationHelper.getGson();
                
                String strJson = oGson.toJson(aloBean);
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
    
    public ReplyBean getcountxusuario() throws Exception {
        if (this.checkPermission("getcount")) {
            Long lResult;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            String strFilter = oRequest.getParameter("filter");
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
            int id = Integer.parseInt(oRequest.getParameter("id"));
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                
                PedidoDao oDao = new PedidoDao(oConnection);
                lResult = oDao.getCountxusuario(alFilter, id);
                
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(lResult);
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
    
}
