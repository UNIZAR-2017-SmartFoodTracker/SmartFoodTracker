package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Coste;
import es.unizar.smartFoodTracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CosteRepository extends JpaRepository<Coste, Long> {

    List<Coste> findByUsuario(Usuario usuario);


}
