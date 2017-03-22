package es.unizar.smartFoodTracker.model;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by carlos on 19/03/17.
 */
public class InventarioID implements Serializable{
    private Usuario idUsuario;
    private Producto idProducto;

    public InventarioID(Usuario idUsuario, Producto idProducto) {
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }
}
