package es.unizar.smartFoodTracker.service;


import es.unizar.smartFoodTracker.model.Receta;

import java.util.List;

public interface RecetaService {

    List<Receta> findAll();
    Receta findById(int id);

    void save(Receta receta);
}
