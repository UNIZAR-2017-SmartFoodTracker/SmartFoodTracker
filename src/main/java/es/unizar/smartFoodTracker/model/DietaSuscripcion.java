package es.unizar.smartFoodTracker.model;

import javax.persistence.*;

@Entity(name = "dietaSuscripcion")
public class DietaSuscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Dieta dieta;
    private double valoracion;

    public DietaSuscripcion() {
    }

    public DietaSuscripcion(Usuario usuario, Dieta dieta, double valoracion) {
        this.usuario = usuario;
        this.dieta = dieta;
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

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
}

