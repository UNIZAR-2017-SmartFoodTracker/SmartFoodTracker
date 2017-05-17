package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Dieta;
import es.unizar.smartFoodTracker.service.DietaService;
import es.unizar.smartFoodTracker.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class DietaController {

    @Autowired
    private DietaService dietaService;
    @Autowired
    private RecetaService recetaService;

    @GetMapping(value = "/dietas")
    public @ResponseBody List<Dieta> getRecetas () {

        //dietaService.save(new Dieta("Adelgazamiento extremo", recetaService.findAll(),"Ideal para perder peso. Contiene cacahuetes."));
        //dietaService.save(new Dieta("Adelgazamiento moderado", recetaService.findAll(),"Coger primero receta abuela y luego receta canelones. Contiene soja."));

        return dietaService.findAll();
    }
}
