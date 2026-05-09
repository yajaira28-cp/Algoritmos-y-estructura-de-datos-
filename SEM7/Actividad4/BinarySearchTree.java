package Actividad4;

import Actividad3.ExceptionIsEmpty;
import Actividad3.ItemDuplicated;
import Actividad3.ItemNoFound;

public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeInterface<T> {
    private NodeT<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void insert(T data) throws ItemDuplicated {
        this.root = insertRecursive(this.root, data);
    }

    private NodeT<T> insertRecursive(NodeT<T> current, T data) throws ItemDuplicated {
        if (current == null) return new NodeT<>(data);
        int cmp = data.compareTo(current.data);
        if (cmp < 0) current.left = insertRecursive(current.left, data);
        else if (cmp > 0) current.right = insertRecursive(current.right, data);
        else throw new ItemDuplicated("El dato " + data + " ya existe.");
        return current;
    }

    @Override
    public T search(T data) throws ItemNoFound {
        if (isEmpty() || !searchRecursive(this.root, data)) {
            throw new ItemNoFound("Dato no encontrado: " + data);
        }
        return data;
    }

    private boolean searchRecursive(NodeT<T> current, T data) {
        if (current == null) return false;
        if (data.equals(current.data)) return true;
        return data.compareTo(current.data) < 0 
            ? searchRecursive(current.left, data) 
            : searchRecursive(current.right, data);
    }

    @Override
    public void delete(T data) throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Árbol vacío.");
        // Aquí se implementaría la lógica de eliminación lógica o física
        System.out.println("Eliminación solicitada para: " + data);
    }
}