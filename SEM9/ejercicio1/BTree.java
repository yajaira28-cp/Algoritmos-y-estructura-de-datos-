package ejercicio1;
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
}