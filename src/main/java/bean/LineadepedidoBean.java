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
public class LineadepedidoBean {

    @Expose
    private Integer id;
    @Expose(serialize = false)
    private Integer id_prod;
    @Expose(serialize = false)
    private Integer id_ped;
    @Expose(deserialize = false)
    private ProductoBean obj_prod;
    @Expose(deserialize = false)
    private PedidoBean obj_ped;

    public LineadepedidoBean(Integer id, ProductoBean obj_prod, PedidoBean obj_ped) {
        this.id = id;
        this.obj_prod = obj_prod;
        this.obj_ped = obj_ped;
    }

    public LineadepedidoBean(Integer id) {
        this.id = id;
    }

    public Integer getId_prod() {
        return id_prod;
    }

    public void setId_prod(Integer id_prod) {
        this.id_prod = id_prod;
    }

    public Integer getId_ped() {
        return id_ped;
    }

    public void setId_ped(Integer id_ped) {
        this.id_ped = id_ped;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductoBean getObj_prod() {
        return obj_prod;
    }

    public void setObj_prod(ProductoBean obj_prod) {
        this.obj_prod = obj_prod;
    }

    public PedidoBean getObj_ped() {
        return obj_ped;
    }

    public void setObj_ped(PedidoBean obj_ped) {
        this.obj_ped = obj_ped;
    }

    public LineadepedidoBean() {
    }

}
