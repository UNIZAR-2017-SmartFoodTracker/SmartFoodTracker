package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.model.Receta;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.ProductoRepository;
import es.unizar.smartFoodTracker.repository.RecetaRepository;
import es.unizar.smartFoodTracker.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class RecetaControllerTest {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Before
    public void before () {
        recetaRepository.deleteAll();
        productoRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    public void getAll() {
        productoRepository.save(new Producto("nombre", "descripcion", 20));
        Usuario usuario = new Usuario("test", "", "testNombre1",
                "", false, "");
        recetaRepository.save(new Receta("nombreReceta", productoRepository.findAll(),
                "descr"));
        assertEquals(1, recetaRepository.findAll().size());
    }
}
