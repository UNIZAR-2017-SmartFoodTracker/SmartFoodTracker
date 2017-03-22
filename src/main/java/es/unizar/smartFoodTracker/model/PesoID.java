package es.unizar.smartFoodTracker.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by carlos on 19/03/17.
 */
public class PesoID implements Serializable {

    private Usuario idUsuario;
    private Date fecha;

    public PesoID(Usuario idUsuario, Date fecha) {
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
