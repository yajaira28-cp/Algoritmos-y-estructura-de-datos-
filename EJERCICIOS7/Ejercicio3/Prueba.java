package EJERCICIOS7.Ejercicio3;

public class Prueba {

    public static boolean sameArea(LinkedBST a1, LinkedBST a2) {
        return a1.areaBST() == a2.areaBST();
    }
    public static void main(String[] args) {

        LinkedBST arbol1 = new LinkedBST();
        LinkedBST arbol2 = new LinkedBST();

        // Árbol 1
        arbol1.insertar(15);
        arbol1.insertar(8);
        arbol1.insertar(22);
        arbol1.insertar(5);
        arbol1.insertar(12);

        // Árbol 2
        arbol2.insertar(10);
        arbol2.insertar(5);
        arbol2.insertar(20);
        arbol2.insertar(3);
        arbol2.insertar(7);

        System.out.println("Árbol 1:");
        System.out.println(arbol1);

        System.out.println("Árbol 2:");
        System.out.println(arbol2);

        System.out.println("Área árbol 1: " + arbol1.areaBST());
        System.out.println("Área árbol 2: " + arbol2.areaBST());

        System.out.println("¿Tienen la misma área?: " + sameArea(arbol1, arbol2));
    }
}