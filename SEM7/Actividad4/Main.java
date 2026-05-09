package Actividad4;

import Actividad3.ItemDuplicated;
import Actividad3.ItemNoFound;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        try {
            System.out.println("--- Pruebas de Inserción ---");
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            System.out.println("Nodos insertados con éxito.");

            bst.insert(30); 
        } catch (ItemDuplicated e) {
            System.out.println("Captura: " + e.getMessage());
        }

        try {
            System.out.println("\n--- Pruebas de Búsqueda ---");
            Integer resultado = bst.search(70);
            System.out.println("Se encontró el valor: " + resultado);
            
            bst.search(100); // Esto lanzará ItemNoFound
        } catch (ItemNoFound e) {
            System.out.println("Captura: " + e.getMessage());
        }
    }
}