package LABORATORIO4;

import java.util.ArrayList;
import java.util.List;

public class Subconjuntos {
     public static void generarSubconjuntos(int[] arr, List<Integer> actual, int i) { 

        // Caso base: si ya recorrimos todo el arreglo 
        if (i == arr.length) { 
            System.out.println(actual);
            return; 

        } 
        // Opción 1: Incluir el elemento actual en el subconjunto 
        actual.add(arr[i]); 

        generarSubconjuntos(arr, actual, i + 1); 

        // Backtracking: quitar el elemento para explorar la siguiente opción 
        actual.remove(actual.size() - 1); 

        // Opción 2: No incluir el elemento actual 
        generarSubconjuntos(arr, actual, i + 1); 

    } 
    public static void main(String[] args) { 

        int[] arr = { 1, 2, 3 };
        generarSubconjuntos(arr, new ArrayList<>(), 0); 

    } 

} 

