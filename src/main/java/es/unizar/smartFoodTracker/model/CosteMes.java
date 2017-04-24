package es.unizar.smartFoodTracker.model;


public class CosteMes {

    private String fecha;
    private double coste;
    public CosteMes(){
        coste = 0.0;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
}
