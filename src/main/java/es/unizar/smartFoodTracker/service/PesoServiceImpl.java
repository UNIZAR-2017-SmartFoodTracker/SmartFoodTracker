package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Peso;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.PesoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public class PesoServiceImpl implements PesoService{

    @Autowired
    private PesoRepository pesoRepository;

    @Override
    public List<Peso> getPesos(Usuario usuario) {
        return pesoRepository.findByUsuario(usuario);
    }

    @Override
    public void save(Peso peso) {
        pesoRepository.save(peso);
    }

    @Override
    public List<Peso> findAll(){
        return pesoRepository.findAll();
    }


}
