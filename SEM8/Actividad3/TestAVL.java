package Actividad3;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> arbol = new AVLTree<>();

        System.out.println(" INICIO DE CASOS DE PRUEBA EN SECUENCIA");

            System.out.println("\n Prueba 1 y 2: Inserción sin rotación:");
            arbol.insert(50);
            arbol.insert(30);
            System.out.println(" Claves 50 y 30 añadidas con éxito.");

            System.out.println("\n Prueba 3: Inserción con Rotación Simple Derecha (RSR):");
            arbol.insert(20); // Rompe balance en 50. Hijo 30(BF:1) -> RSR

            System.out.println("\n Prueba 4: Inserción con Rotación Simple Izquierda (RSL):");
            arbol.insert(70);
            arbol.insert(80); // Rompe balance en 50. Hijo 70(BF:-1) -> RSL

            System.out.println("\n Prueba 5: Inserción con Rotación Doble Derecha (RDR):");
            arbol.insert(10);
            arbol.insert(15); // Rompe balance en 20. Hijo 10(BF:-1). Nieto 15(BF:0) -> RDR

            System.out.println("\n Prueba 6: Inserción con Rotación Doble Izquierda (RDL):");
            arbol.insert(60); // Rompe balance en 70. Hijo 80. Nieto 60 -> RDL

            System.out.println("\n Prueba 7: Segunda RSR (Caso Izquierda-Izquierda masivo):");
            arbol.insert(5);
            arbol.insert(2); // Provoca otra rotación simple a la derecha en la zona inferior

            System.out.println("\n Prueba 8: Segunda RSL (Caso Derecha-Derecha masivo):");
            arbol.insert(90);
            arbol.insert(100); // Provoca otra rotación simple a la izquierda en el extremo derecho

    }
}
