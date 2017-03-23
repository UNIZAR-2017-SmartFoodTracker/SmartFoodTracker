package es.unizar.smartFoodTracker.service;


import es.unizar.smartFoodTracker.model.Usuario;

import java.util.List;

/**
 * Created by carlos on 19/03/17.
 */
public interface UsuarioService  {

    void save (Usuario usuario);
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    List<Usuario> findAll ();
}
