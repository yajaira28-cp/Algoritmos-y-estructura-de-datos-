package Actividad3;

public class BSTree<E extends Comparable<E>> {
    protected Node<E> raiz;

    public BSTree() {
        this.raiz = null;
    }

    public void insert(E data) throws ItemDuplicated {
        this.raiz = insertRec(this.raiz, data);
    }

    private Node<E> insertRec(Node<E> actual, E data) throws ItemDuplicated {
        if (actual == null) {
            return new Node<>(data);
        }

        int comparacion = data.compareTo(actual.data);

        if (comparacion < 0) {
            actual.left = insertRec(actual.left, data);
        } else if (comparacion > 0) {
            actual.right = insertRec(actual.right, data);
        } else {
            throw new ItemDuplicated("El elemento ya existe.");
        }
        
        return actual;
    }
}