package ejercicio2;

public class Zona {
    private String mineral;
    private int cantidad;
    private double pureza;

    public Zona(String mineral, int cantidad, double pureza) {
        this.mineral = mineral;
        this.cantidad = cantidad;
        this.pureza = pureza;
    }

    public String getMineral() {
        return mineral;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPureza() {
        return pureza;
    }
}