package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by carlos on 19/03/17.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    Usuario findByUsername (String username);

}
