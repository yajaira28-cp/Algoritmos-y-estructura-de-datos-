package ejercicio2;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;

    public boolean search(E cl) {
        if (root == null) {
            return false;
        }
        return searchHelper(root, cl);
    }

    private boolean searchHelper(BNode<E> current, E cl) {
        int i = 0;
        while (i < current.getKeysCount() && cl.compareTo(current.getKey(i)) > 0) {
            i++;
        }
        if (i < current.getKeysCount() && cl.compareTo(current.getKey(i)) == 0) {
            System.out.println(cl + " se encuentra en el nodo " + current.getIdNode() + " en la posición " + i);
            return true;
        }
        if (current.isLeaf()) {
            return false;
        }
        return searchHelper(current.getChild(i), cl);
    }

    public void searchRange(E min, E max) {
        if (min.compareTo(max) > 0) {
            System.out.println("Error: El rango proporcionado es invalido (min es mayor que max).");
            return;
        }
        if (root != null) {
            searchRangeHelper(root, min, max);
            System.out.println(); 
        } else {
            System.out.println("El arbol esta vacio.");
        }
    }

    private void searchRangeHelper(BNode<E> current, E min, E max) {
        int i;
        for (i = 0; i < current.getKeysCount(); i++) {
            E currentKey = current.getKey(i);

            if (!current.isLeaf() && currentKey.compareTo(min) >= 0) {
                searchRangeHelper(current.getChild(i), min, max);
            }

            if (currentKey.compareTo(min) >= 0 && currentKey.compareTo(max) <= 0) {
                System.out.print(currentKey + " ");
            }

            if (currentKey.compareTo(max) > 0) {
                break;
            }
        }

        if (!current.isLeaf() && (i == 0 || current.getKey(i - 1).compareTo(max) <= 0)) {
            searchRangeHelper(current.getChild(i), min, max);
        }
    }
}