package es.unizar.smartFoodTracker.service.mailService;

import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.service.UsuarioService;
import es.unizar.smartFoodTracker.web.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;

@Component
public class EmailService {

    private final Logger log = Logger.getLogger(UsuarioController.class.getName());
    private MailSenderManager sender;

    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage templateMessage;
    @Autowired
    private UsuarioService usuarioService;

    @PostConstruct
    private void mailConfig () {
        this.sender = new MailSenderManager();
        sender.setTemplateMessage(templateMessage);
        sender.setMailSender(mailSender);
    }

//    @Scheduled(fixedDelay = 604800000)     //Comprobación cada 1 semana (604800000 ms)
    public void check() {
        log.info("Comprobando aviso peso.");
        List<Usuario> usuarios = usuarioService.findAll();
        for (Usuario usuario : usuarios) {
            if(usuario.isAviso()) {
                sendEmails(usuario.getEmail());
            }
        }
    }

    private void sendEmails(String email) {
        sender.send(email, "Hola,\n\nRecuerde que debe introducir su peso semanalmente para poder realizar el " +
                "seguimiento de su dieta.\n\n¡Gracias por contar con nosotros!\n\nEl equipo de SmartFoodTracker.");
        log.info("Mensaje enviado.");
    }
}
