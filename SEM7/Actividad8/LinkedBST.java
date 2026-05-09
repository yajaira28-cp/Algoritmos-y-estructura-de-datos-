package Actividad8;

class LinkedBST {
    private Nodo raiz;

    public LinkedBST() {
        raiz = null;
    }

 public void postOrder() {
        postOrder(raiz);
    }

    private void postOrder(Nodo nodo) {
        if (nodo != null) {
            postOrder(nodo.izquierda);
            postOrder(nodo.derecha);
            System.out.print(nodo.dato + " ");
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