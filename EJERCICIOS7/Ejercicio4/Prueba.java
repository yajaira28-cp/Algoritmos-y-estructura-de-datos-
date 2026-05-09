package EJERCICIOS7.Ejercicio4;

public class Prueba {
    public static void main(String[] args) {

        LinkedBST arbol = new LinkedBST();

        arbol.insertar(15);
        arbol.insertar(8);
        arbol.insertar(22);
        arbol.insertar(5);
        arbol.insertar(12);
        arbol.insertar(18);
        arbol.insertar(30);

        System.out.println("Árbol en formato parenthesize:");
        arbol.parenthesize();
        System.out.println("\n¿Es un BST válido?: " + arbol.isValidBST());
    }
}