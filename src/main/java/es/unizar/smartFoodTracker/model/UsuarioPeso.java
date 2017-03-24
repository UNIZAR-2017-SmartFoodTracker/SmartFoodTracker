package es.unizar.smartFoodTracker.model;

/**
 * Created by carlos on 23/03/17.
 */
public class UsuarioPeso {

    private String username;
    private double peso;

    public UsuarioPeso(){

    }

    public UsuarioPeso(String username, double peso) {
        this.username = username;
        this.peso = peso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
