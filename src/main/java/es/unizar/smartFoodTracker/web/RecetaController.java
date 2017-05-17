package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.*;
import es.unizar.smartFoodTracker.service.ProductoService;
import es.unizar.smartFoodTracker.service.RecetaService;
import es.unizar.smartFoodTracker.service.RecetaSuscripcionService;
import es.unizar.smartFoodTracker.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private RecetaSuscripcionService recetaSuscripcionService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/recetas")
    public @ResponseBody List<Receta> getRecetas () {

        List<Producto> productos = productoService.findAll();

        //recetaService.save(new Receta("Pastel de la abuela", productos,"Primero coger tomate, luego echarlo, luego polvos magicos y voila! Contiene cacahuetes y cereales con gluten"));
//        Receta r = new Receta("macarrones con Macarrones", productos,"muchos macarrones");
//        recetaService.save(r);
        return recetaService.findAll();
    }

    @GetMapping(value = "/recetasHistorial")
    public @ResponseBody List<RecetaSuscripcion> getHistorialRecetas () {
        return recetaSuscripcionService.findAll();
    }

//    @PostMapping(value = "/recetas")
//    public ResponseEntity<?> postReceta (@RequestBody Receta receta) {
//        recetaService.save(receta);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping(value = "/recetasHistorial")
    public ResponseEntity<?> postRecetasHistorial (@RequestBody RecetaSuscripcionName recetaSuscripcionName) {
        Usuario u = usuarioService.findByUsername(recetaSuscripcionName.getUsername());
        Receta r = recetaService.findById(recetaSuscripcionName.getIdReceta());
        RecetaSuscripcion recetaSuscripcion = recetaSuscripcionService.findByUsuarioAndReceta(u, r);
        if (recetaSuscripcion == null) {
            recetaSuscripcionService.save(new RecetaSuscripcion(u, r, recetaSuscripcionName.getValoracion()));
        }
        else {
            recetaSuscripcion.setValoracion(recetaSuscripcionName.getValoracion());
            recetaSuscripcionService.save(recetaSuscripcion);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping(value = "/recetasHistorial/{username:.*}")
    public @ResponseBody List<RecetaSuscripcion> getHistorialRecetasByUsuario
            (@PathVariable String username) {
        Usuario usuario = usuarioService.findByUsername(username);
        return recetaSuscripcionService.findByUsuario(usuario);
    }

}
