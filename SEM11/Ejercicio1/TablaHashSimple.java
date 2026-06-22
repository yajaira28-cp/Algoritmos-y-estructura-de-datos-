package Ejercicio1;

import java.util.Arrays;

public class TablaHashSimple {

    public static void main(String[] args) {
        int tamanoTabla = 11;
        int[] tablaHash = new int[tamanoTabla];

        // Inicializar la tabla en -1 (posiciones vacías)
        Arrays.fill(tablaHash, -1);

        // Valores a insertar
        int[] valores = { 3, 14, 25, 36, 47, 58 };

        System.out.println(" 1. PROCESO DE INSERCIÓN Y CÁLCULO MANUAL ");
        for (int valor : valores) {
            // Función Hash: h(x) = x % 11
            int indice = valor % tamanoTabla;

            System.out.printf("Valor: %2d -> Hash: %d %% 11 = Índice %d", valor, valor, indice);

            if (tablaHash[indice] != -1) {
                System.out.printf(" (¡Colisión! Se sobrescribe el %d con el %d)%n", tablaHash[indice], valor);
            } else {
                System.out.println(" (Asignado exitosamente)");
            }

            // Insertar el valor (sobrescribe si hay colisión)
            tablaHash[indice] = valor;
        }

        System.out.println("\n 2. TABLA HASH FINAL Y POSICIONES VACÍAS ");
        System.out.println("Índice\tValor\tEstado");
        System.out.println("-------------------------");
        for (int i = 0; i < tablaHash.length; i++) {
            String estado = (tablaHash[i] == -1) ? "Vacía" : "Ocupada";
            String valorStr = (tablaHash[i] == -1) ? "-1" : String.valueOf(tablaHash[i]);
            System.out.println(i + "\t" + valorStr + "\t" + estado);
        }
    }
}