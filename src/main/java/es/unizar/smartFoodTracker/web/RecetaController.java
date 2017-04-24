package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Receta;
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

    @GetMapping(value = "/recetas")
    public @ResponseBody List<Receta> getRecetas () {
        return recetaService.findAll();
    }

}
