package es.unizar.smartFoodTracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by carlos on 16/03/17.
 */
@Entity (name = "peso")
@IdClass(PesoID.class)
public class Peso {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
    private double peso;

    @Id
    private Date fecha;

    public Peso() {
    }

    public Peso(Usuario usuario, double peso, Date fecha) {
        this.usuario = usuario;
        this.peso = peso;
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String fechaToString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
