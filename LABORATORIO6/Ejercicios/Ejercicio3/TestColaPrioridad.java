package Ejercicios.Ejercicio3;

public class TestColaPrioridad {
    public static void main(String[] args) {

        ColaPrioridad cp = new ColaPrioridad(3);

        cp.enqueue("A", 0);
        cp.enqueue("B", 2);
        cp.enqueue("C", 1);
        cp.enqueue("D", 2);

        // Mostrar orden de salida
        while (!cp.isEmpty()) {
            System.out.println(cp.dequeue());
        }
    }
}