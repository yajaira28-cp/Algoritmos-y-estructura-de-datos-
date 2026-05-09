package Actividad9;

class LinkedBST {
    private Nodo raiz;

    public LinkedBST() {
        raiz = null;
    }

    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo insertarRec(Nodo nodo, int dato) {
        if (nodo == null) return new Nodo(dato);

        if (dato < nodo.dato) {
            nodo.izquierda = insertarRec(nodo.izquierda, dato);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, dato);
        }
        return nodo;
    }

    public boolean search(int valor) {
        return searchRec(raiz, valor);
    }

    private boolean searchRec(Nodo nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.dato) return true;

        if (valor < nodo.dato)
            return searchRec(nodo.izquierda, valor);
        else
            return searchRec(nodo.derecha, valor);
    }
    public int findMinNode() throws Exception {
        if (raiz == null) throw new Exception("El árbol está vacío");

        Nodo actual = raiz;
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual.dato;
    }
    public int findMaxNode() throws Exception {
        if (raiz == null) throw new Exception("El árbol está vacío");

        Nodo actual = raiz;
        while (actual.derecha != null) {
            actual = actual.derecha;
        }
        return actual.dato;
    }
}