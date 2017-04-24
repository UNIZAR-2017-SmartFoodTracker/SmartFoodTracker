package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.repository.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class ProductoControllerTest {

    @Autowired
    private ProductoController productoController;

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
    public void getAll() throws JSONException {
        int cantidad = productoRepository.findAll().size();
        productoRepository.save(new Producto("patata", "", 100));
        productoRepository.save(new Producto("tomate", "", 100));
        productoRepository.save(new Producto("judia", "", 100));
        assertEquals(cantidad + 3, productoController.getProductos().size());
    }

    @Test
    public void getProductoOK() throws JSONException {
        String nombre = "patata";
        productoRepository.save(new Producto(nombre, "", 100));
        String resp = new JSONObject(productoController.getProducto(nombre)).get("nombre").toString();
        assertEquals(nombre, resp);
    }

    @Test
    public void getProductoNotFound() throws JSONException {
        String nombreResp = new JSONObject(productoController.getProducto("patata")).toString();
        assertEquals("{}", nombreResp);
    }

    @Test
    public void postProducto() throws JSONException {
        int cantidad = productoRepository.findAll().size();
        productoController.postProducto(new Producto("patata", "", 100));
        String resp = new JSONObject(productoController.getProducto("patata"))
                .get("nombre").toString();
        assertEquals("patata", resp);
        assertEquals(cantidad + 1, productoRepository.findAll().size());
    }

    @Test
    public void postProductoExists() throws JSONException {
        int cantidad = productoRepository.findAll().size();
        productoController.postProducto(new Producto("patata", "", 100));
        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),
                productoController.postProducto(new Producto("patata", "", 100)));
        assertEquals(cantidad + 1, productoRepository.findAll().size());
    }

}
