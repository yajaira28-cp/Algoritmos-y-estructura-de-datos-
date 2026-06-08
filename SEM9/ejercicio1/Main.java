package ejercicio1;

public class Main {
    public static void main(String[] args) {
        // Creamos el árbol B
        BTree<Integer> arbol = new BTree<>();

        System.out.println("--- Probando el método de búsqueda ---");
        boolean encontrado = arbol.search(52);
        System.out.println("Resultado de la búsqueda: " + encontrado);
    }
}