package es.unizar.smartFoodTracker.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by carlos on 16/03/17.
 */
@Entity (name = "peso")
@IdClass(PesoID.class)
public class Peso {

    @Id
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;
    private double peso;
    @Id
    private Date fecha;

    public Peso() {
    }

    public Peso(Usuario idUsuario, double peso, Date fecha) {
        this.idUsuario = idUsuario;
        this.peso = peso;
        this.fecha = fecha;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
