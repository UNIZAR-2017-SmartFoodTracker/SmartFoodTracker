package es.unizar.smartFoodTracker.config;

import es.unizar.smartFoodTracker.service.PesoService;
import es.unizar.smartFoodTracker.service.PesoServiceImpl;
import es.unizar.smartFoodTracker.service.UsuarioService;
import es.unizar.smartFoodTracker.service.UsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by carlos on 19/03/17.
 */
@Configuration
public class PersistenceContext {

    @Bean
    UsuarioServiceImpl usuarioService(){
        return new UsuarioServiceImpl();
    }

    @Bean
    PesoServiceImpl pesoService(){
        return new PesoServiceImpl();
    }


}
