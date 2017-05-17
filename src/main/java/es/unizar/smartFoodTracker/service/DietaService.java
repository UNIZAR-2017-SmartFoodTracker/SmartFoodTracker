package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Dieta;

import java.util.List;

/**
 * Created by carlos on 24/04/17.
 */
public interface DietaService {

    List<Dieta> findAll();
    Dieta findById(int id);

    void save(Dieta dieta);
}
