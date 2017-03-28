package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by carlos on 26/03/17.
 */
@Controller
@RequestMapping(value = "/api")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    private final Logger log = Logger.getLogger(UsuarioController.class.getName());

    @GetMapping(value = "/producto")
    public @ResponseBody List<Producto> getProductos(){
        log.info("Enviado lista con todos los productos");
        return productoService.findAll();
    }

    //Comprobar si existe o no un producto.
    @GetMapping(value = "/producto/{nombre:.*}")
    public @ResponseBody String getProductos(@PathVariable String nombre){
        if (productoService.findByNombre(nombre) == null){
            return "{}";
        }
        return productoService.findByNombre(nombre).toString();
    }

    @PostMapping(value = "/producto")
    public ResponseEntity<?> postProducto (@RequestBody Producto producto){
        if (productoService.findByNombre(producto.getNombre()) == null) {
            productoService.save(producto);
            log.info("Se ha guardado un producto con nombre: " + producto.getNombre());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        log.info("El producto " + producto.getNombre() + " ya existe.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
