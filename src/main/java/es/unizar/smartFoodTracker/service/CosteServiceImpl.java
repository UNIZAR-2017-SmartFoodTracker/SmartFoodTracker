package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Coste;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.CosteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CosteServiceImpl implements CosteService {

    @Autowired
    private CosteRepository costeRepository;

    @Override
    public List<Coste> findByUsuario(Usuario usuario) {
        return costeRepository.findByUsuario(usuario);
    }

    @Override
    public void save(Coste coste) {
        costeRepository.save(coste);
    }
}
