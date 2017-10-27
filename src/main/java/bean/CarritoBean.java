/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.google.gson.annotations.Expose;

/**
 *
 * @author a023321991z
 */
public class CarritoBean {
    @Expose
    private Integer cantidad;
    @Expose
    private ProductoBean obj_prod;

    public CarritoBean(Integer stock, ProductoBean obj_prod) {
        this.cantidad = stock;
        this.obj_prod = obj_prod;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer stock) {
        this.cantidad = stock;
    }

    public CarritoBean() {
    }
    

    public ProductoBean getObj_prod() {
        return obj_prod;
    }

    public void setObj_prod(ProductoBean obj_prod) {
        this.obj_prod = obj_prod;
    }
    
    
}
