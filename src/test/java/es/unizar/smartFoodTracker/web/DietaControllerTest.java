package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Dieta;
import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.model.Receta;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.DietaRepository;
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
public class DietaControllerTest {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DietaRepository dietaRepository;

    @Before
    public void before () {
        recetaRepository.deleteAll();
        productoRepository.deleteAll();
        dietaRepository.deleteAll();
    }

    @Test
    public void getAll() {
        productoRepository.save(new Producto("nombre", "descripcion", 20));
        recetaRepository.save(new Receta("nombreReceta", productoRepository.findAll(),
                "descr"));
        dietaRepository.save(new Dieta("receta", recetaRepository.findAll(),
                "recetaDescp"));
        assertEquals(1, dietaRepository.findAll().size());
    }
}