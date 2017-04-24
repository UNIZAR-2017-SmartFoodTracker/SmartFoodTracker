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
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private int cantidadMinima;
    private int cantidad;
    private Date fechaCaducidad;
    private double coste;

    public Inventario() {
    }

    public Inventario(Usuario usuario, Producto producto, int cantidadMinima, int cantidad, Date fechaCaducidad,
                      double coste) {
        this.usuario = usuario;
        this.producto = producto;
        this.cantidadMinima = cantidadMinima;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
        this.coste = coste;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
}

