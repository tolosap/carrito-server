/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.google.gson.annotations.Expose;
import java.sql.Date;

/**
 *
 * @author Tolosap
 */
public class PedidoBean {
    @Expose
    private int id;
    @Expose
    private Date fecha;
    @Expose(serialize = false)
    private int id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;

    public PedidoBean(int id, Date fecha, UsuarioBean obj_usuario) {
        this.id = id;
        this.fecha = fecha;
        this.obj_usuario = obj_usuario;
    }

    

    public UsuarioBean getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(UsuarioBean obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    public PedidoBean(int id) {
        this.id = id;
    }

    public PedidoBean() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
}
