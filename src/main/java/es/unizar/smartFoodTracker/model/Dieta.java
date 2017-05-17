package es.unizar.smartFoodTracker.model;

import javax.persistence.*;
import java.util.List;

@Entity (name = "dieta")
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    @ManyToMany
    private List<Receta> recetas;
    private String descripcion;

    public Dieta() {
    }

    public Dieta(String nombre, List<Receta> recetas, String descripcion) {
        this.nombre = nombre;
        this.recetas = recetas;
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

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
