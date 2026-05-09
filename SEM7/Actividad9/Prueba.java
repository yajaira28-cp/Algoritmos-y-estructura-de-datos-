package Actividad9;

public class Prueba {
    public static void main(String[] args) {

        LinkedBST arbol = new LinkedBST();

        arbol.insertar(400);
        arbol.insertar(100);
        arbol.insertar(700);
        arbol.insertar(50);
        arbol.insertar(200);
        arbol.insertar(75);

        try {
            System.out.println("Mínimo: " + arbol.findMinNode());
            System.out.println("Máximo: " + arbol.findMaxNode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}