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
    private Long usuario;
    private Long producto;


    public InventarioID() {
    }

}
