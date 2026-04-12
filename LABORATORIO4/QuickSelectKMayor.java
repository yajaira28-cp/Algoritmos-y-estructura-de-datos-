package LABORATORIO4;
import java.util.Scanner;
public class QuickSelectKMayor {

    public static void main(String[] args) {
        int[] arreglo = {4, 2, 7, 1, 4, 6};
        int k = 1;

        // El algoritmo busca el k-ésimo mayor
        int resultado = encontrarKMayor(arreglo, 0, arreglo.length - 1, k);

        System.out.println("Salida: " + resultado);s
    }
    public static int encontrarKMayor(int[] arr, int izquierda, int derecha, int k) {
        // Si k es válido
        if (k > 0 && k <= derecha - izquierda + 1) {
            
            int pos = particion(arr, izquierda, derecha);
            int nElementos = pos - izquierda + 1;

            // Caso 1: Si la posición del pivote es el k-ésimo elemento
            if (nElementos == k) {
                return arr[pos];
            }

            // Caso 2: Si el k-ésimo está a la izquierda (el pivote es más pequeño)
            if (nElementos > k) {
                return encontrarKMayor(arr, izquierda, pos - 1, k);
            }

            // Caso 3: Si el k-ésimo está a la derecha
            return encontrarKMayor(arr, pos + 1, derecha, k - nElementos);
        }
        return Integer.MAX_VALUE;
    }
    private static int particion(int[] arr, int izquierda, int derecha) {
        int pivote = arr[derecha]; 
        int i = izquierda;

        for (int j = izquierda; j <= derecha - 1; j++) {
            if (arr[j] >= pivote) {
                intercambiar(arr, i, j);
                i++;
            }
        }
        intercambiar(arr, i, derecha);
        return i;
    }
    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}