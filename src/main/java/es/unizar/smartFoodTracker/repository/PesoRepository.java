package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Peso;
import es.unizar.smartFoodTracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public interface PesoRepository extends JpaRepository<Peso, Long> {
    List<Peso> findByUsuario(Usuario usuario);
    List<Peso> findAll();
}
