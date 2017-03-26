package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Inventario;
import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;


    @Override
    public List<Inventario> findByUsuario(Usuario usuario) {
        return inventarioRepository.findByUsuario(usuario);
    }

    @Override
    public void save(Inventario inventario) {
        inventarioRepository.save(inventario);
    }

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }


}
