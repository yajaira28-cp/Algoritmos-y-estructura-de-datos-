package Actividad7;

class LinkedBST {
    private Nodo raiz;

    public LinkedBST() {
        raiz = null;
    }

    public void preOrder() {
        preOrder(raiz);
    }

    private void preOrder(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " "); // raíz
            preOrder(nodo.izquierda);          // izquierda
            preOrder(nodo.derecha);            // derecha
        }
    }

    // Inserción simple
    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo insertarRec(Nodo nodo, int dato) {
        if (nodo == null) {
            return new Nodo(dato);
        }

        if (dato < nodo.dato) {
            nodo.izquierda = insertarRec(nodo.izquierda, dato);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, dato);
        }

        return nodo;
    }
}