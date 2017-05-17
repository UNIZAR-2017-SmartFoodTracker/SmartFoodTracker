package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Receta;
import es.unizar.smartFoodTracker.model.RecetaSuscripcion;
import es.unizar.smartFoodTracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by carlos on 17/05/17.
 */
public interface RecetaSuscripcionRepository extends JpaRepository<RecetaSuscripcion, Long> {

    List<RecetaSuscripcion> findByUsuario (Usuario usuario);
    RecetaSuscripcion findByUsuarioAndReceta (Usuario usuario, Receta receta);
}
