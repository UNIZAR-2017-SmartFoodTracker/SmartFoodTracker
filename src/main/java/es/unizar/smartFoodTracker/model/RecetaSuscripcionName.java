package es.unizar.smartFoodTracker.model;


public class RecetaSuscripcionName {
    private String username;
    private int idReceta;
    private double valoracion;

    public RecetaSuscripcionName() {
    }

    public RecetaSuscripcionName(String username, int idReceta, double valoracion) {
        this.username = username;
        this.idReceta = idReceta;
        this.valoracion = valoracion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
}
