package es.unizar.smartFoodTracker.model;

import javax.persistence.*;

/**
 * Created by carlos on 16/03/17.
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String username;
    private String email;
    private String nombre;
    private String apellidos;
    private boolean aviso;
    private String password;

    public Usuario() {
    }

    public Usuario(String username, String email, String nombre, String apellidos, boolean aviso, String password) {
        this.username = username;
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.aviso = aviso;
        this.password = password;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isAviso() {
        return aviso;
    }

    public void setAviso(boolean aviso) {
        this.aviso = aviso;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\"username\":\"" + username + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"nombre\":\"" + nombre + '\"' +
                ", \"apellidos\":\"" + apellidos + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"aviso\":\"" + aviso + '\"' +
                '}';
    }
}
