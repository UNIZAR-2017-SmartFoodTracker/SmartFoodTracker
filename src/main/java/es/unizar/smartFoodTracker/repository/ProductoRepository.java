package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findByNombre(String nombre);

    List<Producto> findAll();

}
