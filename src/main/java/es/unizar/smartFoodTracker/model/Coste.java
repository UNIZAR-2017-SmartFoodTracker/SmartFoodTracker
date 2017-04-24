package es.unizar.smartFoodTracker.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "costes")
public class Coste implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int num;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private String fecha;
    private double coste;

    public Coste() {
    }

    public Coste(Usuario usuario, String fecha, double coste) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.coste = coste;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
}
