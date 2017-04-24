package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.*;
import es.unizar.smartFoodTracker.repository.CosteRepository;
import es.unizar.smartFoodTracker.repository.InventarioRepository;
import es.unizar.smartFoodTracker.repository.ProductoRepository;
import es.unizar.smartFoodTracker.repository.UsuarioRepository;
import es.unizar.smartFoodTracker.service.CosteService;
import es.unizar.smartFoodTracker.service.UsuarioService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
public class CosteControllerTest {

    @Autowired
    private CosteController costeController;

    @Autowired
    private CosteRepository costeRepository;

    @Before
    public void before(){
        costeRepository.deleteAll();
    }

    @Test
    public void getAllVacio(){
        List<Coste> costes = costeRepository.findAll();
        assertEquals(0,costes.size());
    }

    @Test
    public void getCosteUsuarioNoExiste(){
        Usuario aux = new Usuario("noexisto","falla@falla.com","asdf","asdf",false,"asdf");
        List<CosteMes> costes = costeController.getAllCostes("noexisto");
        assertEquals(0,costes.size());
    }

}
