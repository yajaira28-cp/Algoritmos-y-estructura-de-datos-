package Actividad6;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST arbol = new LinkedBST();

        arbol.insertar(400);
        arbol.insertar(100);
        arbol.insertar(700);
        arbol.insertar(50);
        arbol.insertar(200);
        arbol.insertar(75);

        System.out.println("Recorrido In-Order:");
        arbol.inOrder();
    }
}