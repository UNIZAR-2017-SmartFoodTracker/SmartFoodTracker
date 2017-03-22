package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by carlos on 19/03/17.
 */
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
