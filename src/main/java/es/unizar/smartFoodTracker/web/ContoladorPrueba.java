package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Peso;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by carlos on 19/03/17.
 */
@Controller
public class ContoladorPrueba {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/test")
    public void test () {
        Usuario u = new Usuario("carlos", "c@c.com", "Carlos",
                "Tolon", false, "sisi");
        usuarioService.save(u);
    }
}
