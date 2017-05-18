package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Dieta;
import es.unizar.smartFoodTracker.model.DietaSuscripcion;
import es.unizar.smartFoodTracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by carlos on 17/05/17.
 */
public interface DietaSuscripcionRepository extends JpaRepository<DietaSuscripcion, Long> {
    List<DietaSuscripcion> findByUsuario(Usuario usuario);
    DietaSuscripcion findByUsuarioAndDieta(Usuario usuario, Dieta dieta);

}
