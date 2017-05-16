package es.unizar.smartFoodTracker.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    @ManyToMany
    private List<Producto> productos;
    private String descripcion;

    public Receta() {
    }

    public Receta(String nombre, List<Producto> productos, String descripcion) {
        this.nombre = nombre;
        this.productos = productos;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}