package Ejercicio2;

import java.util.Arrays;

public class ComparacionSondeoHash {
    private static final int TAMANO = 7;

    public static void main(String[] args) {
        int[] valores = { 10, 17, 24, 31, 4 };

        ejecutarSondeoLineal(valores);
        System.out.println("\n" + "=".repeat(50) + "\n");
        ejecutarSondeoCuadratico(valores);
    }

    public static void ejecutarSondeoLineal(int[] valores) {
        int[] tabla = new int[TAMANO];
        Arrays.fill(tabla, -1);
        int totalSaltos = 0;

        System.out.println(" SONDEO LINEAL ");

        for (int x : valores) {
            int hashBase = x % TAMANO;
            int i = 0;
            int posicion = hashBase;

            while (tabla[posicion] != -1) {
                i++;
                posicion = (hashBase + i) % TAMANO;
            }

            tabla[posicion] = x;
            totalSaltos += i;
            System.out.printf("Insertado %2d en índice %d (Saltos ante colisión: %d)%n", x, posicion, i);
        }
        imprimirTabla(tabla, totalSaltos);
    }

    public static void ejecutarSondeoCuadratico(int[] valores) {
        int[] tabla = new int[TAMANO];
        Arrays.fill(tabla, -1);
        int totalSaltos = 0;

        System.out.println(" SONDEO CUADRÁTICO ");

        for (int x : valores) {
            int hashBase = x % TAMANO;
            int i = 0;
            int posicion = hashBase;

            while (tabla[posicion] != -1) {
                i++;
                posicion = (hashBase + (i * i)) % TAMANO;
            }

            tabla[posicion] = x;
            totalSaltos += i;
            System.out.printf("Insertado %2d en índice %d (Saltos ante colisión: %d)%n", x, posicion, i);
        }
        imprimirTabla(tabla, totalSaltos);
    }

    private static void imprimirTabla(int[] tabla, int totalSaltos) {
        System.out.println("\nEstado Final de la Tabla:");
        System.out.println("Índice\tValor");
        for (int i = 0; i < TAMANO; i++) {
            System.out.println(i + "\t" + (tabla[i] == -1 ? "[Vacío]" : tabla[i]));
        }
        System.out.println("Total de saltos acumulados: " + totalSaltos);
    }
}