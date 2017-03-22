package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Peso;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by carlos on 19/03/17.
 */
public interface PesoRepository extends JpaRepository<Peso, Long> {
}
