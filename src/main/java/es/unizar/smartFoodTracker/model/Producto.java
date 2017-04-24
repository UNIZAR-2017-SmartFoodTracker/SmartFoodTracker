package es.unizar.smartFoodTracker.model;

import javax.persistence.*;

/**
 * Created by carlos on 16/03/17.
 */
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_producto")
    private Long idProducto;
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    private String descripcion;
    private int calorias;

    public Producto() {
    }

    public Producto(String nombre, String description, int calorias) {
        this.nombre = nombre;
        this.descripcion = description;
        this.calorias = calorias;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Producto\", " +
                "\"idProducto\":" + (idProducto == null ? "null" : "\"" + idProducto + "\"") + ", " +
                "\"nombre\":" + (nombre == null ? "null" : "\"" + nombre + "\"") + ", " +
                "\"desc\":" + (descripcion == null ? "null" : "\"" + descripcion + "\"") + ", " +
                "\"calorias\":\"" + calorias + "\"" +
                "}";
    }
}
