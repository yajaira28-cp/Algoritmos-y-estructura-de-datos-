package LABORATORIO4;

import java.util.ArrayList;
import java.util.List;

public class Permutacuones {
    public static void permutar(int[] arr, List<Integer> actual, boolean[] usado) {
        // Caso base: si el tamaño de la lista es igual al del arreglo, se encontró una permutación
        if (actual.size() == arr.length) {
            System.out.println(actual);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            // Si el elemento no ha sido usado en la permutación actual
            if (!usado[i]) {
                usado[i] = true;          // Marcar como usado
                actual.add(arr[i]);       // Agregar el elemento a la lista actual

                permutar(arr, actual, usado); // Llamada recursiva

                // Backtracking
                actual.remove(actual.size() - 1); // Quitar el último elemento
                usado[i] = false;                 // Marcar como no usado para la siguiente iteración
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        boolean[] usado = new boolean[arr.length];
        // Iniciar la generación de permutaciones
        permutar(arr, new ArrayList<>(), usado);
    }
}

