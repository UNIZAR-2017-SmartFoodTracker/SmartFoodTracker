package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Receta;
import es.unizar.smartFoodTracker.model.RecetaSuscripcion;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.RecetaSuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by carlos on 17/05/17.
 */
public class RecetaSusctipcionServiceImpl implements RecetaSuscripcionService {

    @Autowired
    private RecetaSuscripcionRepository recetaSuscripcionRepository;


    @Override
    public List<RecetaSuscripcion> findAll() {
        return recetaSuscripcionRepository.findAll();
    }

    @Override
    public List<RecetaSuscripcion> findByUsuario(Usuario usuario) {
        return recetaSuscripcionRepository.findByUsuario(usuario);
    }

    @Override
    public RecetaSuscripcion findByUsuarioAndReceta(Usuario usuario, Receta receta) {
        return recetaSuscripcionRepository.findByUsuarioAndReceta(usuario, receta);
    }

    @Override
    public void save(RecetaSuscripcion recetaSuscripcion) {
        recetaSuscripcionRepository.save(recetaSuscripcion);
    }
}
