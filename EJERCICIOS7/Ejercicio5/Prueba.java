package EJERCICIOS7.Ejercicio5;

public class Prueba {
    public static void main(String[] args) {

        LinkedBST inventario = new LinkedBST();
        inventario.insertar(50);
        inventario.insertar(30);
        inventario.insertar(70);
        inventario.insertar(20);
        inventario.insertar(40);
        inventario.insertar(60);
        inventario.insertar(80);

        // Buscar rango
        inventario.searchRange(30, 70);

        // Contar hojas
        System.out.println("Productos en nodos hoja: " + inventario.countLeaves());

        // Orden descendente
        inventario.printDescending();
    }
}