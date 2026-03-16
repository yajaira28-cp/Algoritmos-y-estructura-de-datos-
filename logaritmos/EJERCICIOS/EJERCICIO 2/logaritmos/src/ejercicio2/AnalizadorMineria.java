package ejercicio2;
import java.util.HashMap;
import java.util.Map;

public class AnalizadorMineria {
    private Terreno terreno;

    public AnalizadorMineria(Terreno terreno) {
        this.terreno = terreno;
    }

    public void encontrarRegionMasValiosa() {
        double maxValor = -1;
        int mejorFila = 0;
        int mejorCol = 0;
        for (int i = 0; i < terreno.filas - 1; i++) {
            for (int j = 0; j < terreno.columnas - 1; j++) {
                double valorActual = calcularValorRegion(i, j);
                if (valorActual > maxValor) {
                    maxValor = valorActual;
                    mejorFila = i;
                    mejorCol = j;
                }
            }
        }
        imprimirResultado(mejorFila, mejorCol, maxValor);
    }

    private double calcularValorRegion(int f, int c) {
        double suma = 0;
        for (int i = f; i < f + 2; i++) {
            for (int j = c; j < c + 2; j++) {
                Zona z = terreno.obtenerZona(i, j);
                suma += z.getCantidad() * z.getPureza();
            }
        }
        return suma;
    }

    private String obtenerMineralPredominante(int f, int c) {
        Map<String, Integer> conteo = new HashMap<>();
        for (int i = f; i < f + 2; i++) {
            for (int j = c; j < c + 2; j++) {
                String m = terreno.obtenerZona(i, j).getMineral();
                conteo.put(m, conteo.getOrDefault(m, 0) + 1);
            }
        }
        
        String frecuente = "";
        int max = -1;
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                frecuente = entry.getKey();
            }
        }
        return frecuente;
    }

    private void imprimirResultado(int f, int c, double valorTotal) {
        System.out.println("Región más valiosa encontrada:");
        System.out.println("\nPosición inicial: (" + f + "," + c + ")");
        System.out.println("Tamaño de la región: 2 x 2");
        System.out.println("\nZonas analizadas:");

        for (int i = f; i < f + 2; i++) {
            for (int j = c; j < c + 2; j++) {
                Zona z = terreno.obtenerZona(i, j);
                System.out.println("[ " + z.getMineral() + ", cantidad: " + z.getCantidad() + 
                                   ", pureza: " + z.getPureza() + " ]");
            }
        }

        System.out.println("\nValor total estimado: " + valorTotal);
        System.out.println("\nMineral predominante en la región: " + obtenerMineralPredominante(f, c));
    }
}