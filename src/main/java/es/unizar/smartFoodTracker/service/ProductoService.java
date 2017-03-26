package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Producto;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public interface ProductoService {

    Producto findByNombre(String nombre);

    List<Producto> findAll();

    void save(Producto producto);

}
