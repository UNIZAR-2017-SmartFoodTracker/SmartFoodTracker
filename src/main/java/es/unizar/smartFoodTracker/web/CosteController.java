package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Coste;
import es.unizar.smartFoodTracker.model.CosteMes;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.service.CosteService;
import es.unizar.smartFoodTracker.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/api")
public class CosteController {

    @Autowired
    private CosteService costeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/coste/{username:.*}/{fecha:.*}")
    public @ResponseBody List<Coste> getCostes (@PathVariable String username,
                           @PathVariable String fecha) {
        Usuario u = usuarioService.findByUsername(username);
        List<Coste> l = costeService.findByUsuario(u);
        List<Coste> lc = new ArrayList<>();
        for (Coste coste: l) {
            if (coste.getFecha().equals(fecha)) {
                lc.add(coste);
            }
        }
        return lc;
    }

    @GetMapping(value = "/coste/{username:.*}")
    public @ResponseBody List<CosteMes> getAllCostes (@PathVariable String username) {
        Usuario u = usuarioService.findByUsername(username);
        List<Coste> l = costeService.findByUsuario(u);
        List<String> lMes = new ArrayList<>();
        List<CosteMes> lc = new ArrayList<>();
        for (Coste coste: l) {
            if (!lMes.contains(coste.getFecha())) {
                lMes.add(coste.getFecha());
            }
        }

        for (String fecha: lMes) {
            CosteMes cm = new CosteMes();
            cm.setFecha(fecha);
            for (Coste coste: l) {
                if (fecha.equals(coste.getFecha())) {
                    cm.setCoste(cm.getCoste() + coste.getCoste());
                }
            }
            lc.add(cm);
        }
        return lc;
    }

}
