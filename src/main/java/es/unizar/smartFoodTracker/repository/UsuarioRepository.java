package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by carlos on 19/03/17.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername (String username);

}
