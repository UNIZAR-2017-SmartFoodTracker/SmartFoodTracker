package es.unizar.smartFoodTracker.config;

import es.unizar.smartFoodTracker.service.PesoServiceImpl;
import es.unizar.smartFoodTracker.service.UsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by carlos on 19/03/17.
 */
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
