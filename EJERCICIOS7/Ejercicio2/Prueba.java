package EJERCICIOS7.Ejercicio2;
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

        try {
            System.out.println("Nodos no hoja: " + arbol.countAllNodes());
            System.out.println("Nodos hoja: " + arbol.countNodes());

            System.out.println("\nAltura desde 8: " + arbol.height(8));
            System.out.println("Altura desde 22: " + arbol.height(22));
            System.out.println("Altura desde 100: " + arbol.height(100));

            System.out.println("\nAmplitud del árbol: " + arbol.amplitud());

            System.out.println("\nEliminando árbol");
            arbol.destroyNodes();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}