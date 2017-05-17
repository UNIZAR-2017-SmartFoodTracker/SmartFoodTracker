package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Receta;
import es.unizar.smartFoodTracker.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    @Override
    public Receta findById(int id) {
        return recetaRepository.findById(id);
    }

    @Override
    public void save(Receta receta) {
        recetaRepository.save(receta);
    }
}
