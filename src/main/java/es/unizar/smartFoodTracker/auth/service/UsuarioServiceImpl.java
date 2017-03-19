package es.unizar.smartFoodTracker.auth.service;

import es.unizar.smartFoodTracker.auth.model.Usuario;
import es.unizar.smartFoodTracker.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by carlos on 19/03/17.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

}
