package Actividad6;

class LinkedBST {
    private Nodo raiz;

    public LinkedBST() {
        raiz = null;
    }

    // Método público para iniciar el recorrido
    public void inOrder() {
        inOrder(raiz);
    }

    // Método privado recursivo (izquierda, raíz, derecha)
    private void inOrder(Nodo nodo) {
        if (nodo != null) {
            inOrder(nodo.izquierda);   // izquierda
            System.out.print(nodo.dato + " "); // raíz
            inOrder(nodo.derecha);     // derecha
        }
    }

    // Método simple para insertar datos
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