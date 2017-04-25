package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Peso;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.model.UsuarioPeso;
import es.unizar.smartFoodTracker.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class PesoControllerTest {

    @Autowired
    private PesoController pesoController;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PesoRepository pesoRepository;


    @Before
    public void before(){
        dietaRepository.deleteAll();
        pesoRepository.deleteAll();
        inventarioRepository.deleteAll();
        recetaRepository.deleteAll();
        productoRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    public void getAllPesos() throws InterruptedException {
        int cantidad = pesoRepository.findAll().size();
        Usuario usuario = new Usuario("test", "", "testNombre1",
                "", false, "");
        Peso p = new Peso(new Usuario(), 80.0, new Date());
        p.setUsuario(usuario);
        pesoRepository.save(p);
        for (Peso peso: pesoController.getAllPesos()) {
            System.out.println(peso.getPeso());
        }
        assertEquals(cantidad + 1, pesoController.getAllPesos().size());
    }

    @Test
    public void getPesosUsuario() throws InterruptedException {
        int cantidad = pesoRepository.findAll().size();
        Usuario usuario = new Usuario("test", "", "testNombre1",
                "", false, "");
        Peso p = new Peso(null, 80.0, new Date());
        p.setUsuario(usuario);

        pesoRepository.save(p);
        assertEquals(cantidad + 1, pesoController.getPesos(usuario.getUsername()).size());
    }

    @Test
    public void postPesoUsuarioNoExiste() {
        int cantidad = pesoRepository.findAll().size();
        pesoController.postPeso(new UsuarioPeso("test", 80.0));
        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),
                pesoController.postPeso(new UsuarioPeso("test2", 80.0)));
        assertEquals(cantidad, pesoController.getAllPesos().size());
    }
}
