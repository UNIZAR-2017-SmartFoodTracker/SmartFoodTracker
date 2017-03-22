package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
