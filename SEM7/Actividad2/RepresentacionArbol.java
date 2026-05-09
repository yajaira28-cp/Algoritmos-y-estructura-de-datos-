package Actividad2;
public class RepresentacionArbol {
    public static void main(String[] args) {
        // Representación secuencial del primer árbol (Letras)
        // Usamos String para poder poner "null" en espacios vacíos
        String[] arbolLetras = {"F", "B", "G", "A", "D", null, "I", null, null, "C", "E", null, null, "H", null};

        System.out.println("Arreglo de Letras:");
        imprimirArreglo(arbolLetras);

        // Representación secuencial del segundo árbol (Números)
        // Usamos Integer para manejar nulos
        Integer[] arbolNumeros = {8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13, null};

        System.out.println("\nArreglo de Números:");
        imprimirArreglo(arbolNumeros);
    }

    // Método simple para mostrar el arreglo
    public static void imprimirArreglo(Object[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print("[" + i + "]:" + (arreglo[i] == null ? "-" : arreglo[i]) + "  ");
        }
        System.out.println();
    }
}