package es.unizar.smartFoodTracker.web;

import es.unizar.smartFoodTracker.model.Usuario;
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
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Before
    public void before(){
        usuarioRepository.deleteAll();
    }

    /**
     *
     */
    @Test
    public void getUsuarioOK () throws JSONException {
        usuarioRepository.save(new Usuario("test", "email@com.com", "testNombre",
                "testApellidos", false, "pass"));
        JSONObject test =  new JSONObject(usuarioController.getUsuario("test"));
        String username = test.get("username").toString();
        assertEquals("test", username);
    }

    @Test
    public void getUsuarioNotFound () throws JSONException {
        String test =  new JSONObject(usuarioController.getUsuario("carlos")).toString();
        assertEquals("{}", test);
    }

    @Test
    public void getAllUsuarios () throws JSONException {
        int cantidad = usuarioRepository.findAll().size();
        usuarioRepository.save(new Usuario("test", "", "testNombre1",
                "", false, ""));
        usuarioRepository.save(new Usuario("test2", "", "testNombre2",
                "", false, ""));
        usuarioRepository.save(new Usuario("test3", "", "testNombre3",
                "", false, ""));

        assertEquals(cantidad + 3, usuarioController.getAllUsuarios().size());
    }

    @Test
    public void postUsuarioOK () throws JSONException {
        int cantidad = usuarioRepository.findAll().size();
        String username = "test";
        usuarioController.postUsuario(new Usuario(username, "", "testNombre1",
                "", false, ""));
        String usernameResp = new JSONObject(usuarioController.getUsuario(username)).get("username").toString();
        assertEquals(username, usernameResp);
        assertEquals(cantidad + 1, usuarioRepository.findAll().size());
    }

    /**
     * Se comprueba que no se pueden añadir 2 usuarios con el mismo "username". El segundo usuario no se añade
     * @throws JSONException
     */
    @Test
    public void postUsuarioExists () throws JSONException {
        int cantidad = usuarioRepository.findAll().size();
        String username = "test";
        usuarioController.postUsuario(new Usuario(username, "", "testNombre1",
                "", false, ""));
        assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), usuarioController.postUsuario(
                new Usuario(username,"", "testNombre1",
                "", false, "")));
        assertEquals(cantidad + 1, usuarioRepository.findAll().size());
    }

}
