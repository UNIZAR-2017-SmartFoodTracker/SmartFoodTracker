package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Inventario;
import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.model.ProductoInventario;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.repository.InventarioRepository;
import es.unizar.smartFoodTracker.repository.ProductoRepository;
import es.unizar.smartFoodTracker.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class InventarioControllerTest {

    @Autowired
    private InventarioController inventarioController;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Before
    public void before () {
        inventarioRepository.deleteAll();
        usuarioRepository.deleteAll();
        productoRepository.deleteAll();
    }

    @Test
    public void getAll(){
        int cantidad = inventarioRepository.findAll().size();
        Usuario usuario = new Usuario("test", "", "testNombre1",
                "", false, "");
        Producto p = new Producto("patata", "", 100);
        Inventario i = new Inventario(usuario, p, 1, 5, new Date(), 10.0);
        inventarioRepository.save(i);
        assertEquals(cantidad + 1, inventarioController.getInventarios().size());
    }

    @Test
    public void getInventarioUsuario(){
        int cantidad = inventarioRepository.findAll().size();
        Usuario usuario = new Usuario("test", "", "testNombre1",
                "", false, "");
        Producto p = new Producto("patata", "", 100);
        Inventario i = new Inventario(usuario, p, 1, 5, new Date(), 10.0);
        inventarioRepository.save(i);
        assertEquals(cantidad + 1, inventarioController.getProductos
                (usuario.getUsername()).size());
    }

    @Test
    public void getInventarioNoExiste(){
        int cantidad = inventarioRepository.findAll().size();
        assertEquals(0,
                inventarioController.getProductos("noExiste").size());
        assertEquals(cantidad, inventarioController.getInventarios().size());
    }

    @Test
    public void postInventarioUsuarioNoExiste() throws ParseException {
        int cantidad = inventarioRepository.findAll().size();
        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),
                inventarioController.postProducto(new ProductoInventario("test",
                        "patata", "02/02/2002",
                        2, 1, 10.5)));
        assertEquals(cantidad, inventarioController.getInventarios().size());
    }


}
