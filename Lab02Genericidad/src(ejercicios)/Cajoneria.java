package Lab02Genericidad.src;
import java.util.ArrayList;

public class Cajoneria<T> {

    private ArrayList<Caja<T>> cajas;

    public Cajoneria() {
        cajas = new ArrayList<>();
    }

    public void addCaja(Caja<T> caja) {
        cajas.add(caja);
    }

    public String search(T elemento) {
        for (int i = 0; i < cajas.size(); i++) {
            if (cajas.get(i).getContenido().equals(elemento)) {
                return "Posición: " + (i + 1) +
                       " | Color: " + cajas.get(i).getColor();
            }
        }
        return "Elemento no encontrado";
    }

    public T delete(T elemento) {
        for (int i = 0; i < cajas.size(); i++) {
            if (cajas.get(i).getContenido().equals(elemento)) {
                T eliminado = cajas.get(i).getContenido();
                cajas.remove(i);
                return eliminado;
            }
        }
        return null;
    }
    public int countOccurrences(T elemento) {
        int contador = 0;

        for (Caja<T> caja : cajas) {
            if (caja.getContenido().equals(elemento)) {
                contador++;
            }
        }

        return contador;
    }

    @Override
    public String toString() {
        String resultado = "";

        resultado += "Posición\tColor Caja\tObjeto\n";

        for (int i = 0; i < cajas.size(); i++) {
            resultado += (i + 1) + "\t\t" +
                    cajas.get(i).getColor() + "\t\t" +
                    cajas.get(i).getContenido() + "\n";
        }

        return resultado;
    }
}