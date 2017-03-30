package es.unizar.smartFoodTracker.service.mailService;

import es.unizar.smartFoodTracker.model.Inventario;
import es.unizar.smartFoodTracker.model.Usuario;
import es.unizar.smartFoodTracker.service.InventarioService;
import es.unizar.smartFoodTracker.service.UsuarioService;
import es.unizar.smartFoodTracker.web.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class EmailService {

    private final Logger log = Logger.getLogger(UsuarioController.class.getName());
    private MailSenderManager sender;
    private final long diasCaducidad = 172800000; //172800000 = 2 días de caducidad

    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage templateMessage;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private InventarioService inventarioService;

    @PostConstruct
    private void mailConfig () {
        this.sender = new MailSenderManager();
        sender.setTemplateMessage(templateMessage);
        sender.setMailSender(mailSender);
    }

//    @Scheduled(fixedDelay = 604800000)     //Comprobación cada 1 semana (604800000 ms)
    public void checkAvisoPeso() {
        log.info("Comprobando aviso peso.");
        List<Usuario> usuarios = usuarioService.findAll();

        for (Usuario usuario : usuarios) {
            if(usuario.isAviso()) {
                sendEmails(usuario.getEmail(), "peso");
            }
        }
    }

//    @Scheduled(fixedDelay = 86400000)       //Comprobación cada día (86400000 ms)
    public void checkAvisoCaducidad() {
        log.info("Comprobando aviso caducidad.");
        ArrayList<Long> enviado = new ArrayList<>();

        Date hoy = new Date();
        Long hoyMs = hoy.getTime() + diasCaducidad;
        List<Inventario> inventario = inventarioService.findAll();

        for (Inventario producto : inventario) {
            if (hoyMs > producto.getFechaCaducidad().getTime()) { // Si caduca en 2 días...
                Usuario user = producto.getUsuario();
                if (!enviado.contains(user.getIdUsuario())) { // Si no se lo hemos enviado ya...
                    log.info("Enviando correo aviso caducidad");
                    enviado.add(user.getIdUsuario());
                    sendEmails(user.getEmail(), "caducidad");
                }
            }
        }
    }

    public void faltaCantidad(Inventario inventario) {
        sendEmails(inventario.getUsuario().getEmail(), inventario.getProducto().getNombre());
    }

    private void sendEmails(String email, String tipo) {
        if (tipo.equals("peso")) {
            sender.send(email, "Hola,\n\nRecuerde que debe introducir su peso semanalmente para poder realizar el " +
                    "seguimiento de su dieta.\n\n¡Gracias por contar con nosotros!\n\nEl equipo de SmartFoodTracker.");
        } else if (tipo.equals("caducidad")) {
            sender.send(email, "Hola,\n\nLe recordamos que tiene uno o más productos a punto de caducar en su " +
                    "inventario. Recuerde actualizar la fecha de caducidad de sus productos al consumirlos." +
                    "\n\n¡Gracias por contar con nosotros!\n\nEl equipo de SmartFoodTracker.");
        } else { //producto mínimo
            sender.send(email, "Hola,\n\nLe recordamos que " + tipo + " está a punto de agotarse según " +
                    "lo establecido en sus parámetros. Recuerde actualizar la cantidad de sus productos regularmente." +
                    "\n\n¡Gracias por contar con nosotros!\n\nEl equipo de SmartFoodTracker.");
        }
        log.info("Mensaje enviado.");
    }
}
