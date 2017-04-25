package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by carlos on 24/04/17.
 */
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findAll();
}
