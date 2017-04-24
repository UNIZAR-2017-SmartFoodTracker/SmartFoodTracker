package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Dieta;
import es.unizar.smartFoodTracker.service.DietaService;
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

    @GetMapping(value = "/dietas")
    public @ResponseBody List<Dieta> getRecetas () {
        return dietaService.findAll();
    }
}
