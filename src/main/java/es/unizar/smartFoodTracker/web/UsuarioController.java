package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * API usuario.
 */
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    private final Logger log = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuario")
    public @ResponseBody String getAllUsuarios(){
        return usuarioService.findAll().toString();
    }

    @GetMapping(value = "/usuario/{username:.*}")
    public @ResponseBody String getUsuario (@PathVariable String username) {
        Usuario test = usuarioService.findByUsername(username);
        if (test == null) return "{}";
        return test.toString();
    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<?> postUsuario (@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.findByEmail(usuario.getEmail());
        if (nuevo == null) {
            nuevo = usuarioService.findByUsername(usuario.getUsername());
            if (nuevo == null) {
                usuarioService.save(usuario);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            //Casos en los que el usuario ya existe.
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
