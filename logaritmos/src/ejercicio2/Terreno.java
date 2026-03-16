package ejercicio2;
public class Terreno {

    Zona[][] matriz;
    int filas;
    int columnas;

    public Terreno(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = new Zona[filas][columnas];
    }

    public void agregarZona(int fila, int columna, Zona z) {
        matriz[fila][columna] = z;
    }

    public Zona obtenerZona(int fila, int columna) {
        return matriz[fila][columna];
    }
}
