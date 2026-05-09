package Actividad3;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        try {
            System.out.println("Insertando nodos");
            bst.insert(10);
            bst.insert(5);
            bst.insert(15);
            
            // Intento de duplicado para probar la excepción
            bst.insert(10); 

        } catch (ItemDuplicated e) {
            System.out.println("ALERTA: " + e.getMessage());
        }
        try {
            System.out.println("Buscando el número 100");
            bst.search(100);
        } catch (ItemNoFound | ExceptionIsEmpty e) {
            System.out.println("ALERTA: " + e.getMessage());
        }
    }
}