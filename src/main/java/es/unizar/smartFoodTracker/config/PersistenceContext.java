package es.unizar.smartFoodTracker.config;

import es.unizar.smartFoodTracker.model.DietaSuscripcion;
import es.unizar.smartFoodTracker.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;


@Configuration
@EnableAsync
@EnableScheduling
public class PersistenceContext {

    @Bean
    UsuarioServiceImpl usuarioService(){
        return new UsuarioServiceImpl();
    }

    @Bean
    PesoServiceImpl pesoService(){
        return new PesoServiceImpl();
    }

    @Bean
    ProductoServiceImpl productoService(){ return new ProductoServiceImpl(); }

    @Bean
    InventarioServiceImpl inventarioService(){ return new InventarioServiceImpl(); }

    @Bean
    CosteServiceImpl costeService() { return new CosteServiceImpl(); }

    @Bean
    RecetaServiceImpl recetaService() { return new RecetaServiceImpl(); }

    @Bean
    DietaServiceImpl dietaService() { return new DietaServiceImpl(); }

    @Bean
    RecetaSusctipcionServiceImpl recetaSusctipcionService() { return new RecetaSusctipcionServiceImpl(); }

    @Bean
    DietaSuscripcionServiceImpl dietaSuscripcionService() { return new DietaSuscripcionServiceImpl(); }

    @Bean
    SimpleMailMessage templateMessage(){
        SimpleMailMessage smm= new SimpleMailMessage();
        smm.setFrom("urlshortener.g3@gmail.com");
        smm.setSubject("Change");
        return smm;
    }

    @Bean
    MailSender mailSender() {
        JavaMailSenderImpl jmsi = new JavaMailSenderImpl();
        Properties property = new Properties();
        property.setProperty("mail.smtp.starttls.enable", "true");
        property.setProperty("mail.smtp.auth", "true");
        property.setProperty("mail.smtp.port", "587");
        jmsi.setHost("smtp.gmail.com");
        jmsi.setUsername("urlshortener.g3@gmail.com");
        jmsi.setPassword("sanzpablo");
        jmsi.setJavaMailProperties(property);

        return jmsi;
    }

}
