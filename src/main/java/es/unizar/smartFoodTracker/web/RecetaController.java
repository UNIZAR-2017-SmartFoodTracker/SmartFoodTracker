package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.model.Receta;
import es.unizar.smartFoodTracker.service.ProductoService;
import es.unizar.smartFoodTracker.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;
    @Autowired
    private ProductoService productoService;

    @GetMapping(value = "/recetas")
    public @ResponseBody List<Receta> getRecetas () {

        List<Producto> productos = productoService.findAll();

        //recetaService.save(new Receta("Pastel de la abuela", productos,"Primero coger tomate, luego echarlo, luego polvos magicos y voila!"));
        //recetaService.save(new Receta("Canelones", productos,"Comprarlos congelados, meterlos en el microondas, y voila!"));

        return recetaService.findAll();
    }

}
