package ejercicio3;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;

    public void remove(E cl) {
        if (root == null) {
            System.out.println("El arbol esta vacio");
            return;
        }
        removeHelper(root, cl);
        if (root.getKeysCount() == 0) {
            if (root.isLeaf()) {
                root = null;
            } else {
                root = root.getChild(0);
            }
        }
    }

    private void removeHelper(BNode<E> current, E cl) {
        int idx = 0;
        while (idx < current.getKeysCount() && cl.compareTo(current.getKey(idx)) > 0) {
            idx++;
        }
        if (idx < current.getKeysCount() && cl.compareTo(current.getKey(idx)) == 0) {
            if (current.isLeaf()) {
                removeFromLeaf(current, idx);
            } else {
                removeFromNonLeaf(current, idx);
            }
        } else {
            if (current.isLeaf()) {
                System.out.println("La clave no esta en el arbol");
                return;
            }
            boolean flag = (idx == current.getKeysCount());
            if (current.getChild(idx).getKeysCount() < 1) {
                fill(current, idx);
            }
            if (flag && idx > current.getKeysCount()) {
                removeHelper(current.getChild(idx - 1), cl);
            } else {
                removeHelper(current.getChild(idx), cl);
            }
        }
    }

    private void removeFromLeaf(BNode<E> current, int idx) {
        for (int i = idx + 1; i < current.getKeysCount(); i++) {
            current.setKey(i - 1, current.getKey(i));
        }
        current.setKeysCount(current.getKeysCount() - 1);
    }

    private void removeFromNonLeaf(BNode<E> current, int idx) {
        E cl = current.getKey(idx);
        if (current.getChild(idx).getKeysCount() >= 1) {
            E pred = getPredecessor(current, idx);
            current.setKey(idx, pred);
            removeHelper(current.getChild(idx), pred);
        } else if (current.getChild(idx + 1).getKeysCount() >= 1) {
            E succ = getSuccessor(current, idx);
            current.setKey(idx, succ);
            removeHelper(current.getChild(idx + 1), succ);
        } else {
            merge(current, idx);
            removeHelper(current.getChild(idx), cl);
        }
    }

    private E getPredecessor(BNode<E> current, int idx) {
        BNode<E> curr = current.getChild(idx);
        while (!curr.isLeaf()) {
            curr = curr.getChild(curr.getKeysCount());
        }
        return curr.getKey(curr.getKeysCount() - 1);
    }

    private E getSuccessor(BNode<E> current, int idx) {
        BNode<E> curr = current.getChild(idx + 1);
        while (!curr.isLeaf()) {
            curr = curr.getChild(0);
        }
        return curr.getKey(0);
    }

    private void fill(BNode<E> current, int idx) {
        if (idx != 0 && current.getChild(idx - 1).getKeysCount() >= 1) {
            borrowFromPrev(current, idx);
        } else if (idx != current.getKeysCount() && current.getChild(idx + 1).getKeysCount() >= 1) {
            borrowFromNext(current, idx);
        } else {
            if (idx != current.getKeysCount()) {
                merge(current, idx);
            } else {
                merge(current, idx - 1);
            }
        }
    }

    private void borrowFromPrev(BNode<E> current, int idx) {
        BNode<E> child = current.getChild(idx);
        BNode<E> sibling = current.getChild(idx - 1);
        for (int i = child.getKeysCount() - 1; i >= 0; i--) {
            child.setKey(i + 1, child.getKey(i));
        }
        if (!child.isLeaf()) {
            for (int i = child.getKeysCount(); i >= 0; i--) {
                child.setChild(i + 1, child.getChild(i));
            }
        }
        child.setKey(0, current.getKey(idx - 1));
        if (!child.isLeaf()) {
            child.setChild(0, sibling.getChild(sibling.getKeysCount()));
        }
        current.setKey(idx - 1, sibling.getKey(sibling.getKeysCount() - 1));
        child.setKeysCount(child.getKeysCount() + 1);
        sibling.setKeysCount(sibling.getKeysCount() - 1);
    }

    private void borrowFromNext(BNode<E> current, int idx) {
        BNode<E> child = current.getChild(idx);
        BNode<E> sibling = current.getChild(idx + 1);
        child.setKey(child.getKeysCount(), current.getKey(idx));
        if (!child.isLeaf()) {
            child.setChild(child.getKeysCount() + 1, sibling.getChild(0));
        }
        current.setKey(idx, sibling.getKey(0));
        for (int i = 1; i < sibling.getKeysCount(); i++) {
            sibling.setKey(i - 1, sibling.getKey(i));
        }
        if (!sibling.isLeaf()) {
            for (int i = 1; i <= sibling.getKeysCount(); i++) {
                sibling.setChild(i - 1, sibling.getChild(i));
            }
        }
        child.setKeysCount(child.getKeysCount() + 1);
        sibling.setKeysCount(sibling.getKeysCount() - 1);
    }

    private void merge(BNode<E> current, int idx) {
        BNode<E> child = current.getChild(idx);
        BNode<E> sibling = current.getChild(idx + 1);
        child.setKey(1, current.getKey(idx));
        for (int i = 0; i < sibling.getKeysCount(); i++) {
            child.setKey(i + 2, sibling.getKey(i));
        }
        if (!child.isLeaf()) {
            for (int i = 0; i <= sibling.getKeysCount(); i++) {
                child.setChild(i + 2, sibling.getChild(i));
            }
        }
        for (int i = idx + 1; i < current.getKeysCount(); i++) {
            current.setKey(i - 1, current.getKey(i));
        }
        for (int i = idx + 2; i <= current.getKeysCount(); i++) {
            current.setChild(i - 1, current.getChild(i));
        }
        child.setKeysCount(child.getKeysCount() + sibling.getKeysCount() + 1);
        current.setKeysCount(current.getKeysCount() - 1);
    }
}