package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Dieta;
import es.unizar.smartFoodTracker.model.DietaSuscripcion;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.DietaSuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by carlos on 17/05/17.
 */
public class DietaSuscripcionServiceImpl implements DietaSuscripcionService {

    @Autowired
    private DietaSuscripcionRepository dietaSuscripcionRepository;

    @Override
    public List<DietaSuscripcion> findAll() {
        return dietaSuscripcionRepository.findAll();
    }

    @Override
    public List<DietaSuscripcion> findByUsuario(Usuario usuario) {
        return dietaSuscripcionRepository.findByUsuario(usuario);
    }

    @Override
    public DietaSuscripcion findByUsuarioAndDieta(Usuario usuario, Dieta dieta) {
        return dietaSuscripcionRepository.findByUsuarioAndDieta(usuario, dieta);
    }

    @Override
    public void save(DietaSuscripcion dietaSuscripcion) {
        dietaSuscripcionRepository.save(dietaSuscripcion);
    }

}
