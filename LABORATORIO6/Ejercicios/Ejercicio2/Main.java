package Ejercicios.Ejercicio2;

public class Main {
    public static void main(String[] args) {

        ColaArreglo cola = new ColaArreglo(5);

        // Encolar clientes
        cola.enqueue(101);
        cola.enqueue(102);
        cola.enqueue(103);
        cola.enqueue(104);
        cola.enqueue(105);

        // Intentar encolar uno más
        cola.enqueue(106); // Cola llena

        // Atender 2 clientes
        System.out.println("Atendiendo cliente: " + cola.dequeue());
        System.out.println("Atendiendo cliente: " + cola.dequeue());

        // Mostrar frente
        System.out.println("Cliente en frente: " + cola.front());

        // Encolar más (circular)
        cola.enqueue(106);
        cola.enqueue(107);

        // Vaciar cola
        while (!cola.isEmpty()) {
            System.out.println("Atendiendo cliente: " + cola.dequeue());
        }

        // Intentar desencolar otra vez
        cola.dequeue(); // Cola vacía
    }
}