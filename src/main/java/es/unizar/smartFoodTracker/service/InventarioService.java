package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Inventario;
import es.unizar.smartFoodTracker.model.Usuario;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public interface InventarioService {

    List<Inventario> findByUsuario(Usuario usuario);

    void save(Inventario inventario);

    List<Inventario> findAll();
}
