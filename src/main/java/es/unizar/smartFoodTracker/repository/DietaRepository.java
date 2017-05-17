package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DietaRepository extends JpaRepository<Dieta, Long> {

    List<Dieta> findAll();
    Dieta findById(int id);
}
