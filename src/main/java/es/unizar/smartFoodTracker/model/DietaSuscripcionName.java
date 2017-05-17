package es.unizar.smartFoodTracker.model;

/**
 * Created by carlos on 17/05/17.
 */
public class DietaSuscripcionName {
    private String username;
    private int idDieta;
    private double valoracion;

    public DietaSuscripcionName() {
    }

    public DietaSuscripcionName(String username, int idDieta, double valoracion) {
        this.username = username;
        this.idDieta = idDieta;
        this.valoracion = valoracion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
}
