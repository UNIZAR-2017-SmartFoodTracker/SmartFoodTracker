package es.unizar.smartFoodTracker.auth.repository;

import es.unizar.smartFoodTracker.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by carlos on 19/03/17.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername (String username);

}
