package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Producto;
import es.unizar.smartFoodTracker.repository.InventarioRepository;
import es.unizar.smartFoodTracker.repository.ProductoRepository;
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
    private ProductoRepository productoRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Before
    public void before(){
        inventarioRepository.deleteAll();
        productoRepository.deleteAll();
    }

    @Test
    public void getAll() throws JSONException {
        int cantidad = productoRepository.findAll().size();
        productoRepository.save(new Producto("patata"));
        productoRepository.save(new Producto("tomate"));
        productoRepository.save(new Producto("judia"));
        assertEquals(cantidad + 3, productoController.getProductos().size());
    }

    @Test
    public void getProductoOK() throws JSONException {
        String nombre = "patata";
        productoRepository.save(new Producto(nombre));
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
        productoController.postProducto(new Producto("patata"));
        String resp = new JSONObject(productoController.getProducto("patata"))
                .get("nombre").toString();
        assertEquals("patata", resp);
        assertEquals(cantidad + 1, productoRepository.findAll().size());
    }

    @Test
    public void postProductoExists() throws JSONException {
        int cantidad = productoRepository.findAll().size();
        productoController.postProducto(new Producto("patata"));
        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),
                productoController.postProducto(new Producto("patata")));
        assertEquals(cantidad + 1, productoRepository.findAll().size());
    }

}
