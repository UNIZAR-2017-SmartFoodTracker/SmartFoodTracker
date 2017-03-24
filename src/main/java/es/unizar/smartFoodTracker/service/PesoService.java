package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Peso;
import es.unizar.smartFoodTracker.model.Usuario;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public interface PesoService {

    List<Peso> getPesos(Usuario usuario);

    void save (Peso peso);

    List<Peso> findAll();

}
