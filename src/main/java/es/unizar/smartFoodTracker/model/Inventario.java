package es.unizar.smartFoodTracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by carlos on 16/03/17.
 */

@Entity
@IdClass(InventarioID.class)
public class Inventario {

    @Id
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto idProducto;

    private int cantidadMinima;
    private int cantidad;
    private Date fechaCaducidad;

    public Inventario() {
    }

    public Inventario(Usuario idUsuario, Producto idProducto, int cantidadMinima, int cantidad, Date fechaCaducidad) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.cantidadMinima = cantidadMinima;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}

