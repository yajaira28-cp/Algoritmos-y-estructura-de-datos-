package Actividad3;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public boolean isEmpty() { return root == null; }

    // Insertar con validación de duplicados
    public void insert(T data) throws ItemDuplicated {
        root = insertRecursive(root, data);
    }

    private Node<T> insertRecursive(Node<T> current, T data) throws ItemDuplicated {
        if (current == null) return new Node<>(data);
        
        int cmp = data.compareTo(current.data);
        if (cmp < 0) {
            current.left = insertRecursive(current.left, data);
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, data);
        } else {
            throw new ItemDuplicated("El dato " + data + " ya existe.");
        }
        return current;
    }

    // Buscar con validación de no encontrado
    public void search(T data) throws ItemNoFound, ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Árbol vacío");
        if (!searchRecursive(root, data)) throw new ItemNoFound("Dato no encontrado");
    }

    private boolean searchRecursive(Node<T> current, T data) {
        if (current == null) return false;
        if (data.equals(current.data)) return true;
        return data.compareTo(current.data) < 0 
            ? searchRecursive(current.left, data) 
            : searchRecursive(current.right, data);
    }
}