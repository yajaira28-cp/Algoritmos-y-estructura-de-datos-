package Actividad3;

// Clase Nodo
class Node<T extends Comparable<T>> {
    T data;
    Node<T> left, right;

    public Node(T data) {
        this.data = data;
        this.left = this.right = null;
    }
}