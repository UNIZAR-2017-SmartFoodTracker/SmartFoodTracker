package es.unizar.smartFoodTracker.model;

import javax.persistence.*;

@Entity(name = "recetaSuscripcion")
public class RecetaSuscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Receta receta;
    private double valoracion;

    public RecetaSuscripcion() {
    }

    public RecetaSuscripcion(Usuario usuario, Receta receta, double valoracion) {
        this.usuario = usuario;
        this.receta = receta;
        this.valoracion = valoracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
}
