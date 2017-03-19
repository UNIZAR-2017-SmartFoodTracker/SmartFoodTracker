package es.unizar.smartFoodTracker.auth.service;

import es.unizar.smartFoodTracker.auth.model.Usuario;

/**
 * Created by carlos on 19/03/17.
 */
public interface UsuarioService  {

    void save (Usuario usuario);
    Usuario findByUsername(String username);
}
