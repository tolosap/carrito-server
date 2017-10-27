/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Tolosap
 */
public class ProductoBean {
    @Expose
    private int id;
    @Expose
    private int stock;
    @Expose
    private double precio;
    @Expose
    private String nombre;

    public ProductoBean(int id, int stock, double precio, String nombre) {
        this.id = id;
        this.stock = stock;
        this.precio = precio;
        this.nombre = nombre;
    }

    public ProductoBean(int id) {
        this.id = id;
    }

    public ProductoBean() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
