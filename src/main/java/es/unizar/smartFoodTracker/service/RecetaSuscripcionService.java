package es.unizar.smartFoodTracker.service;


import es.unizar.smartFoodTracker.model.Receta;
import es.unizar.smartFoodTracker.model.RecetaSuscripcion;
import es.unizar.smartFoodTracker.model.Usuario;

import java.util.List;

public interface RecetaSuscripcionService {

    List<RecetaSuscripcion> findAll();
    List<RecetaSuscripcion> findByUsuario(Usuario usuario);
    RecetaSuscripcion findByUsuarioAndReceta(Usuario usuario, Receta receta);

    void save(RecetaSuscripcion recetaSuscripcion);





}
