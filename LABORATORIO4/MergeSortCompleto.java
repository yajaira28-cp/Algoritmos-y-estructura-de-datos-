package LABORATORIO4;

import java.util.Arrays;

public class MergeSortCompleto {
    public static void mergeSort(int[] arreglo, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;

            mergeSort(arreglo, inicio, medio);
            mergeSort(arreglo, medio + 1, fin);

            merge(arreglo, inicio, medio, fin);
        }
    }

    public static void merge(int[] arreglo, int inicio, int medio, int fin) {

        int tamaño1 = medio - inicio + 1;
        int tamaño2 = fin - medio;

        int[] izquierda = new int[tamaño1];
        int[] derecha = new int[tamaño2];

        // Copiar datos al arreglo izquierdo
        for (int i = 0; i < tamaño1; i++) {
            izquierda[i] = arreglo[inicio + i];
        }

        // Copiar datos al arreglo derecho
        for (int j = 0; j < tamaño2; j++) {
            derecha[j] = arreglo[medio + 1 + j];
        }

        int i = 0, j = 0;
        int k = inicio;

        while (i < tamaño1 && j < tamaño2) {
            if (izquierda[i] <= derecha[j]) {
                arreglo[k] = izquierda[i];
                i++;
            } else {
                arreglo[k] = derecha[j];
                j++;
            }
            k++;
        }

        while (i < tamaño1) {
            arreglo[k] = izquierda[i];
            i++;
            k++;
        }

        // Copiar elementos restantes de derecha
        while (j < tamaño2) {
            arreglo[k] = derecha[j];
            j++;
            k++;
        }
    }

    // Método para mostrar el arreglo
    public static void mostrarArreglo(String mensaje, int[] arreglo) {
        System.out.println(mensaje + Arrays.toString(arreglo));
    }

    // Método principal
    public static void main(String[] args) {

        int[] arreglo5 = {9, 4, 7, 1, 3};

        int[] arreglo8 = {8, 3, 6, 2, 7, 5, 1, 4};

        int[] arreglo10 = {10, 2, 8, 6, 7, 3, 1, 9, 4, 5};

        mostrarArreglo("Antes (5 elementos): ", arreglo5);
        mostrarArreglo("Antes (8 elementos): ", arreglo8);
        mostrarArreglo("Antes (10 elementos): ", arreglo10);

        // Ordenar
        mergeSort(arreglo5, 0, arreglo5.length - 1);
        mergeSort(arreglo8, 0, arreglo8.length - 1);
        mergeSort(arreglo10, 0, arreglo10.length - 1);

        mostrarArreglo("Después (5 elementos): ", arreglo5);
        mostrarArreglo("Después (8 elementos): ", arreglo8);
        mostrarArreglo("Después (10 elementos): ", arreglo10);
    }
}
    
