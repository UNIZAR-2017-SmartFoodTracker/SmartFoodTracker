package es.unizar.smartFoodTracker.repository;

import es.unizar.smartFoodTracker.model.Inventario;
import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByUsuario(Usuario usuario);

    Inventario findByUsuarioAndProducto(Usuario usuario, Producto producto);

    void delete(Inventario inventario);
}
