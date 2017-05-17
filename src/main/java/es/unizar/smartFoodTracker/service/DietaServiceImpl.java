package es.unizar.smartFoodTracker.service;

import es.unizar.smartFoodTracker.model.Dieta;
import es.unizar.smartFoodTracker.repository.DietaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by carlos on 24/04/17.
 */
public class DietaServiceImpl implements DietaService{

    @Autowired
    private DietaRepository dietaRepository;

    @Override
    public List<Dieta> findAll() {
        return dietaRepository.findAll();
    }

    @Override
    public Dieta findById(int id) {
        return dietaRepository.findById(id);
    }

    @Override
    public void save(Dieta dieta) {
        dietaRepository.save(dieta);
    }
}
