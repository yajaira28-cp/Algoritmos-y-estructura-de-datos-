package Actividad3;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    // Verifica si el árbol está vacío
    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;

        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        E mediana;

        // Si llegamos al fondo del árbol, es momento de insertar
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            // Usamos el método searchNode que implementamos en BNode
            ResultadoBusqueda res = current.searchNode(cl);
            fl = res.encontrado;
            int pos = res.posicion;
            // No se permiten elementos duplicados
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }

            // Bajamos recursivamente por el hijo correspondiente
            mediana = push(current.childs.get(pos), cl);

            // Al regresar de la recursividad, verificamos si el hijo de abajo se dividió
            if (up) {
                // Modificado para usar la función nativa nodeFull pasándole el orden del árbol
                if (current.nodeFull(this.orden)) {
                    mediana = dividedNode(current, mediana, pos);
                } else {
                    putNode(current, mediana, nDes, pos);
                    up = false;
                }
            }
            return mediana;
        }
    }

    // Coloca una clave y su hijo derecho dentro de un nodo desplazando el resto
    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        // Desplazamos las claves e hijos hacia la derecha para hacer espacio
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        // Insertamos los nuevos valores en la posición correcta
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    // Divide un nodo lleno en dos partes
    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        // Determinamos dónde se cortará el nodo
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;

        // Creamos el nuevo nodo hermano derecho
        nDes = new BNode<E>(this.orden);

        // Trasladamos la mitad de las claves e hijos al nuevo nodo
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        // Actualizamos los contadores de claves de ambos bloques
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;

        // Insertamos la clave causante del exceso en el lado que le corresponda
        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }
        // Extraemos la clave mediana que va a ser promovida al padre superior
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        return median;
    }

    // =================================================================
    // METODO TOSTRING PRINCIPAL (Proporcionado en tu guia)
    // =================================================================
    @Override
    public String toString() {
        String s = "";
        if (isEmpty()) {
            s += "BTree is empty...";
        } else {
            // Llamamos al metodo de soporte inicializandolo con el nodo raiz
            s = writeTree(this.root);
        }
        return s;
    }

    private String writeTree(BNode<E> current) {
        return obtenerTextoNodo(current, "");
    }

    private String obtenerTextoNodo(BNode<E> current, String sangria) {
        String acumulador = "";

        if (current != null) {
            acumulador += sangria + " |-- " + current.toString() + "\n";
            for (int i = 0; i <= current.count; i++) {
                if (current.childs.get(i) != null) {
                    acumulador += obtenerTextoNodo(current.childs.get(i), sangria + "     |");
                }
            }
        }
        return acumulador;
    }

    public void printTree() {
        printNode(this.root, "");
    }

    private void printNode(BNode<E> current, String sangria) {
        if (current != null) {
            System.out.print(sangria + " |-- " + current.toString() + "\n");
            for (int i = 0; i <= current.count; i++) {
                printNode(current.childs.get(i), sangria + "     |");
            }
        }
    }
}