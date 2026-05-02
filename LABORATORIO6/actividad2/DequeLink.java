package actividad2;

import actividad1.ExceptionIsEmpty;

class DequeLink<E> implements Deque<E> {
    private Node<E> first;
    private Node<E> last;

    public DequeLink() {
        first = null;
        last = null;
    }
     public void addFirst(E x) {
        Node<E> nuevo = new Node<>(x);
        if (isEmpty()) {
            first = last = nuevo;
        } else {
            nuevo.next = first;
            first = nuevo;
        }
    }

    public void addLast(E x) {
        Node<E> nuevo = new Node<>(x);
        if (isEmpty()) {
            first = last = nuevo;
        } else {
            last.next = nuevo;
            last = nuevo;
        }
    }

    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }
        E dato = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return dato;
    }

    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }

        E dato = last.data;

        if (first == last) { // solo un elemento
            first = last = null;
        } else {
            Node<E> aux = first;
            while (aux.next != last) {
                aux = aux.next;
            }
            aux.next = null;
            last = aux;
        }

        return dato;
    }

    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }
        return first.data;
    }

    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }
        return last.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        String resultado = "";
        Node<E> aux = first;
        while (aux != null) {
            resultado += aux.data + " ";
            aux = aux.next;
        }
        return resultado;
    }
}