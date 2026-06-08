package ejercicio2;

public class Main {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>();

        System.out.println("--- CASO 1: Prueba con rango invalido ---");
        arbol.searchRange(40, 20);

        System.out.println("\n--- CASO 2: Prueba con rango inexistente (Arbol vacio) ---");
        arbol.searchRange(20, 40);
    }
}