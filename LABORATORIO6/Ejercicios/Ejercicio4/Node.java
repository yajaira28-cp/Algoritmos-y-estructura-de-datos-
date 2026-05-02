package Ejercicios.Ejercicio4;

class Node {
    String data;
    int value; // valor secundario (orden interno)
    Node next;

    public Node(String data, int value) {
        this.data = data;
        this.value = value;
        this.next = null;
    }
}

// Cola enlazada ordenada por valor secundario
class ColaOrdenada {
    private Node first;

    public ColaOrdenada() {
        first = null;
    }

    // insertar ordenado (menor valor primero)
    public void enqueue(String x, int value) {
        Node nuevo = new Node(x, value);

        if (first == null || value < first.value) {
            nuevo.next = first;
            first = nuevo;
            return;
        }

        Node actual = first;
        while (actual.next != null && actual.next.value <= value) {
            actual = actual.next;
        }

        nuevo.next = actual.next;
        actual.next = nuevo;
    }

    public String dequeue() {
        if (isEmpty()) return null;

        String dato = first.data;
        first = first.next;
        return dato;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        String res = "";
        Node aux = first;
        while (aux != null) {
            res += "(" + aux.data + "," + aux.value + ") -> ";
            aux = aux.next;
        }
        return res;
    }
}

// Cola de prioridad híbrida
class PriorityQueueHybrid {
    private ColaOrdenada[] queues;
    private int levels;

    public PriorityQueueHybrid(int levels) {
        this.levels = levels;
        queues = new ColaOrdenada[levels];

        for (int i = 0; i < levels; i++) {
            queues[i] = new ColaOrdenada();
        }
    }

    // insertar
    public void enqueue(String x, int priority, int value) {
        queues[priority].enqueue(x, value);
    }

    // eliminar
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

    public void mostrar() {
        for (int i = levels - 1; i >= 0; i--) {
            System.out.println("Nivel " + i + ": " + queues[i]);
        }
    }
}
