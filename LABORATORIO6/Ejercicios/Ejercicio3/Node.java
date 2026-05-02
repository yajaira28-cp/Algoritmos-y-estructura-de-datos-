package Ejercicios.Ejercicio3;

class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

// Cola simple (FIFO)
class Cola {
    private Node front;
    private Node rear;

    public Cola() {
        front = rear = null;
    }

    public void enqueue(String x) {
        Node nuevo = new Node(x);
        if (isEmpty()) {
            front = rear = nuevo;
        } else {
            rear.next = nuevo;
            rear = nuevo;
        }
    }

    public String dequeue() {
        if (isEmpty()) return null;

        String dato = front.data;
        front = front.next;

        if (front == null) rear = null;

        return dato;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

// Cola de prioridad usando varias colas
class ColaPrioridad {
    private Cola[] queues;
    private int levels;

    public ColaPrioridad(int levels) {
        this.levels = levels;
        queues = new Cola[levels];

        for (int i = 0; i < levels; i++) {
            queues[i] = new Cola();
        }
    }

    // insertar
    public void enqueue(String x, int priority) {
        queues[priority].enqueue(x);
    }

    // eliminar (mayor prioridad primero)
    public String dequeue() {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        System.out.println("Cola vacía");
        return null;
    }

    public boolean isEmpty() {
        for (int i = 0; i < levels; i++) {
            if (!queues[i].isEmpty()) return false;
        }
        return true;
    }
}