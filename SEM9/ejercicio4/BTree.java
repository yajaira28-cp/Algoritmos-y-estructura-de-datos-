package ejercicio4;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int t;
    private int nodeCounter;

    public BTree(int t) {
        this.t = t;
        this.root = new BNode<>(1, t, true);
        this.nodeCounter = 1;
    }

    public boolean searchWithRoute(E cl) {
        System.out.print("Camino recorrido (idNodes): ");
        return searchWithRouteHelper(root, cl);
    }

    private boolean searchWithRouteHelper(BNode<E> current, E cl) {
        System.out.print(current.getIdNode() + " ");
        int i = 0;
        while (i < current.getKeysCount() && cl.compareTo(current.getKey(i)) > 0) {
            i++;
        }
        if (i < current.getKeysCount() && cl.compareTo(current.getKey(i)) == 0) {
            System.out.println();
            return true;
        }
        if (current.isLeaf()) {
            System.out.println();
            return false;
        }
        return searchWithRouteHelper(current.getChild(i), cl);
    }

    public void insert(E key) {
        BNode<E> r = root;
        if (r.getKeysCount() == (2 * t - 1)) {
            nodeCounter++;
            BNode<E> s = new BNode<>(nodeCounter, t, false);
            root = s;
            s.setChild(0, r);
            splitChild(s, 0, r);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    private void insertNonFull(BNode<E> current, E key) {
        int i = current.getKeysCount() - 1;
        if (current.isLeaf()) {
            while (i >= 0 && key.compareTo(current.getKey(i)) < 0) {
                current.setKey(i + 1, current.getKey(i));
                i--;
            }
            current.setKey(i + 1, key);
            current.setKeysCount(current.getKeysCount() + 1);
        } else {
            while (i >= 0 && key.compareTo(current.getKey(i)) < 0) {
                i--;
            }
            i++;
            if (current.getChild(i).getKeysCount() == (2 * t - 1)) {
                splitChild(current, i, current.getChild(i));
                if (key.compareTo(current.getKey(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(current.getChild(i), key);
        }
    }

    private void splitChild(BNode<E> parent, int i, BNode<E> child) {
        nodeCounter++;
        BNode<E> z = new BNode<>(nodeCounter, t, child.isLeaf());
        z.setKeysCount(t - 1);
        for (int j = 0; j < t - 1; j++) {
            z.setKey(j, child.getKey(j + t));
        }
        if (!child.isLeaf()) {
            for (int j = 0; j < t; j++) {
                z.setChild(j, child.getChild(j + t));
            }
        }
        child.setKeysCount(t - 1);
        for (int j = parent.getKeysCount(); j >= i + 1; j--) {
            parent.setChild(j + 1, parent.getChild(j));
        }
        parent.setChild(i + 1, z);
        for (int j = parent.getKeysCount() - 1; j >= i; j--) {
            parent.setKey(j + 1, parent.getKey(j));
        }
        parent.setKey(i, child.getKey(t - 1));
        parent.setKeysCount(parent.getKeysCount() + 1);
    }

    public void showOrdered() {
        showOrderedHelper(root);
    }

    private void showOrderedHelper(BNode<E> current) {
        int i;
        for (i = 0; i < current.getKeysCount(); i++) {
            if (!current.isLeaf()) {
                showOrderedHelper(current.getChild(i));
            }
            System.out.println(current.getKey(i));
        }
        if (!current.isLeaf()) {
            showOrderedHelper(current.getChild(i));
        }
    }

    public int getHeight() {
        if (root == null) return 0;
        int height = 0;
        BNode<E> curr = root;
        while (curr != null) {
            height++;
            if (curr.isLeaf()) break;
            curr = curr.getChild(0);
        }
        return height;
    }

    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(BNode<E> current) {
        if (current == null) return 0;
        int count = current.getKeysCount();
        if (!current.isLeaf()) {
            for (int i = 0; i <= current.getKeysCount(); i++) {
                count += sizeHelper(current.getChild(i));
            }
        }
        return count;
    }
}