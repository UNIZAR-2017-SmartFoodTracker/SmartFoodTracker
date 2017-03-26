package es.unizar.smartFoodTracker.model;

import java.util.Date;

/**
 * Created by carlos on 26/03/17.
 */
public class ProductoInventario {

    private String nombreUsuario;
    private String nombreProducto;
    private String fechaCaducidad;
    private int cantidad;
    private int cantidadMinima;

    public ProductoInventario(){}

    public ProductoInventario(String nombreUsuario, String nombreProducto, String fechaCaducidad, int cantidad, int cantidadMinima) {
        this.nombreUsuario = nombreUsuario;
        this.nombreProducto = nombreProducto;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
        this.cantidadMinima = cantidadMinima;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }
}
