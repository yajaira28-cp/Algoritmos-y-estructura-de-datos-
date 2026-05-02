package actividad3;

import actividad1.ExceptionIsEmpty;

interface PriorityQueue<E, N> {
    void enqueue(E x, N pr);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
}

// Nodo para la cola de prioridad
class Node<E> {
    private E data;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    public E getData() { return data; }
    public Node<E> getNext() { return next; }
    public void setNext(Node<E> next) { this.next = next; }
}

// Implementación
class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {

    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    // insertar ordenado por prioridad primero el mayor
    public void enqueue(E x, N pr) {
        EntryNode nuevoDato = new EntryNode(x, pr);
        Node<EntryNode> nuevo = new Node<>(nuevoDato);

        if (isEmpty()) {
            first = last = nuevo;
            return;
        }

        // si tiene mayor prioridad que el primero
        if (pr.compareTo(first.getData().priority) > 0) {
            nuevo.setNext(first);
            first = nuevo;
            return;
        }

        Node<EntryNode> actual = first;

        while (actual.getNext() != null &&
               pr.compareTo(actual.getNext().getData().priority) <= 0) {
            actual = actual.getNext();
        }

        nuevo.setNext(actual.getNext());
        actual.setNext(nuevo);

        if (nuevo.getNext() == null) {
            last = nuevo;
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty");

        E aux = this.first.getData().data;
        this.first = this.first.getNext();

        if (this.first == null)
            this.last = null;

        return aux;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty");
        return first.getData().data;
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty");
        return last.getData().data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        String res = "";
        Node<EntryNode> aux = first;
        while (aux != null) {
            res += "(" + aux.getData().data + "," + aux.getData().priority + ") ";
            aux = aux.getNext();
        }
        return res;
    }
}

public class TestPriorityQueue {
    public static void main(String[] args) {

        try {
            PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

            pq.enqueue("A", 8);
            pq.enqueue("B", 7);
            pq.enqueue("C", 3);
            pq.enqueue("D", 4);

            System.out.println("Cola: " + pq);
            System.out.println("Front: " + pq.front());
            System.out.println("Back: " + pq.back());

            pq.dequeue();
            System.out.println("Después de dequeue: " + pq);

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}