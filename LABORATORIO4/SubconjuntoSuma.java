package LABORATORIO4;

import java.util.Scanner;

public class SubconjuntoSuma {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la entrada (N, elementos y objetivo):");
        int n = sc.nextInt();
        int[] arreglo = new int[n];
        for (int i = 0; i < n; i++) {
            arreglo[i] = sc.nextInt();
        }
        int objetivo = sc.nextInt();

        // Llamada al método recursivo
        boolean resultado = puedeFormarSuma(arreglo, 0, 0, objetivo);
        
        System.out.println("Salida: " + resultado);
    }
    public static boolean puedeFormarSuma(int[] arr, int indice, int sumaActual, int objetivo) {
        if (indice == arr.length) {
            return sumaActual == objetivo;
        }

        int valorActual = arr[indice];

        if (valorActual % 3 == 0) {
            return puedeFormarSuma(arr, indice + 1, sumaActual + valorActual, objetivo);
        }

        boolean esParActual = (valorActual % 2 == 0);
        boolean siguienteEsPar = (indice + 1 < arr.length && arr[indice + 1] % 2 == 0);

        boolean incluir = false;
        if (!(esParActual && siguienteEsPar)) {
            incluir = puedeFormarSuma(arr, indice + 1, sumaActual + valorActual, objetivo);
        }

        if (incluir) return true;

        boolean excluir = puedeFormarSuma(arr, indice + 1, sumaActual, objetivo);
        return excluir;
    }
}