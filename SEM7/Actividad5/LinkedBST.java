package Actividad5;

import Actividad3.ExceptionIsEmpty;
import Actividad3.ItemDuplicated;
import Actividad3.ItemNoFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTreeInterface<E> {
    
    class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }
    public boolean isEmpty() {
        return this.root == null;
    }

//Implementar insert con validación de duplicados
    public void insert(E data) throws ItemDuplicated {
        this.root = insertRecursive(this.root, data);
    }

    private Node insertRecursive(Node current, E data) throws ItemDuplicated {
        if (current == null) return new Node(data);
        
        int cmp = data.compareTo(current.data);
        if (cmp < 0) {
            current.left = insertRecursive(current.left, data);
        } else if (cmp > 0) {
            current.right = insertRecursive(current.right, data);
        } else {
            throw new ItemDuplicated("El dato " + data + " ya existe en el árbol.");
        }
        return current;
    }

    //  Implementar delete validando que no esté vacío
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("No se puede eliminar: el árbol está vacío.");
        }
        System.out.println("Eliminando dato: " + data);
    }

    // Implementar search validando existencia
    public E search(E data) throws ItemNoFound {
        if (isEmpty() || !searchRecursive(this.root, data)) {
            throw new ItemNoFound("Dato " + data + " no encontrado.");
        }
        return data;
    }
    private boolean searchRecursive(Node current, E data) {
        if (current == null) return false;
        if (data.equals(current.data)) return true;
        return data.compareTo(current.data) < 0 
            ? searchRecursive(current.left, data) 
            : searchRecursive(current.right, data);
    }

    // Implementar toString con formato
    @Override
    public String toString() {
        if (isEmpty()) return "Arbol vacío";
        StringBuilder sb = new StringBuilder();
        sb.append("Estructura BST: [ ");
        buildString(this.root, sb);
        sb.append("]");
        return sb.toString();
    }
    private void buildString(Node current, StringBuilder sb) {
        if (current != null) {
            buildString(current.left, sb);
            sb.append(current.data).append(" ");
            buildString(current.right, sb);
        }
    }
}