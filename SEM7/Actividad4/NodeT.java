package Actividad4;

public class NodeT<T> {
    T data;
    NodeT<T> left;
    NodeT<T> right;

    public NodeT(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}