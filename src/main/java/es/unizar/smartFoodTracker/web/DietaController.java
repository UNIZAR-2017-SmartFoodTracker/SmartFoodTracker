package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.*;
import es.unizar.smartFoodTracker.service.DietaService;
import es.unizar.smartFoodTracker.service.DietaSuscripcionService;
import es.unizar.smartFoodTracker.service.RecetaService;
import es.unizar.smartFoodTracker.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class DietaController {

    @Autowired
    private DietaService dietaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DietaSuscripcionService dietaSuscripcionService;
    @Autowired
    private RecetaService recetaService;

    @GetMapping(value = "/dietas")
    public @ResponseBody List<Dieta> getRecetas () {

        //dietaService.save(new Dieta("Adelgazamiento extremo", recetaService.findAll(),"Ideal para perder peso. Contiene cacahuetes."));
        //dietaService.save(new Dieta("Adelgazamiento moderado", recetaService.findAll(),"Coger primero receta abuela y luego receta canelones. Contiene soja."));

        return dietaService.findAll();
    }


    @GetMapping(value = "/dietasHistorial")
    public @ResponseBody List<DietaSuscripcion> getHistorialRecetas () {
        return dietaSuscripcionService.findAll();
    }

    @PostMapping(value = "/dietas")
    public ResponseEntity<?> postReceta (@RequestBody Dieta dieta) {
        dietaService.save(dieta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/dietasHistorial")
    public ResponseEntity<?> postDietasHistorial (@RequestBody DietaSuscripcionName dietaSuscripcionName) {
        Usuario u = usuarioService.findByUsername(dietaSuscripcionName.getUsername());
        Dieta d = dietaService.findById(dietaSuscripcionName.getIdDieta());
        DietaSuscripcion dietaSuscripcion = dietaSuscripcionService.findByUsuarioAndDieta(u, d);
        if (dietaSuscripcion == null) {
            dietaSuscripcionService.save(new DietaSuscripcion(u, d, dietaSuscripcionName.getValoracion()));
        }
        else {
            dietaSuscripcion.setValoracion(dietaSuscripcionName.getValoracion());
            dietaSuscripcionService.save(dietaSuscripcion);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/dietasHistorial/{username:.*}")
    public @ResponseBody List<DietaSuscripcion> getHistorialRecetasByUsuario
            (@PathVariable String username) {
        Usuario usuario = usuarioService.findByUsername(username);
        return dietaSuscripcionService.findByUsuario(usuario);
    }
}
