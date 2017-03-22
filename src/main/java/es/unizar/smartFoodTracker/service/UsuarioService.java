package es.unizar.smartFoodTracker.service;


import es.unizar.smartFoodTracker.model.Usuario;

/**
 * Created by carlos on 19/03/17.
 */
public interface UsuarioService  {

    void save (Usuario usuario);
    Usuario findByUsername(String username);
}
