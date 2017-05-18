package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Dieta;
import es.unizar.smartFoodTracker.model.DietaSuscripcion;
import es.unizar.smartFoodTracker.model.Usuario;

import java.util.List;

/**
 * Created by carlos on 17/05/17.
 */
public interface DietaSuscripcionService {

    List<DietaSuscripcion> findAll();
    List<DietaSuscripcion> findByUsuario(Usuario usuario);
    DietaSuscripcion findByUsuarioAndDieta(Usuario usuario, Dieta dieta);

    void save(DietaSuscripcion dietaSuscripcion);


}
