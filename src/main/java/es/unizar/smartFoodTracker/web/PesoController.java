package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Peso;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.model.UsuarioPeso;
import es.unizar.smartFoodTracker.service.PesoService;
import es.unizar.smartFoodTracker.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by carlos on 23/03/17.
 */
@Controller
@RequestMapping("/api")
public class PesoController {

    private final Logger log = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    private PesoService pesoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/peso")
    public @ResponseBody List<Peso> getAllPesos(){
        return pesoService.findAll();
    }

    @GetMapping("/peso/{username:.*}")
    public @ResponseBody List<Peso> getPesos(@PathVariable String username){
        Usuario usuario = usuarioService.findByUsername(username);
        if (usuario == null) {
            log.info("Usuario " + username + " no existe.");
        }
        return pesoService.getPesos(usuario);
    }

    @PostMapping("/peso/{username:.*}")
    public ResponseEntity<?> postPeso(@RequestBody UsuarioPeso usuarioPeso){
        Usuario usuario = usuarioService.findByUsername(usuarioPeso.getUsername());
        if (usuario == null) {
            log.info("Usuario " + usuarioPeso.getUsername() + " no existe.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Date date = new Date();
        Peso p = new Peso(usuario, usuarioPeso.getPeso(), date);
        pesoService.save(p);
        log.info("Introducido nuevo peso de " + usuarioPeso.getPeso() + " al usuario " +
                usuarioPeso.getUsername() + " a fecha " + p.fechaToString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
