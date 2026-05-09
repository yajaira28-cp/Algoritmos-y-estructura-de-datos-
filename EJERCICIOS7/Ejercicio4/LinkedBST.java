package EJERCICIOS7.Ejercicio4;

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

        if (dato < nodo.dato)
            nodo.izquierda = insertarRec(nodo.izquierda, dato);
        else
            nodo.derecha = insertarRec(nodo.derecha, dato);

        return nodo;
    }
    public void parenthesize() {
        parenthesize(raiz, 0);
    }

    private void parenthesize(Nodo nodo, int nivel) {
        if (nodo == null) return;

        for (int i = 0; i < nivel; i++) {
            System.out.print("   ");
        }

        System.out.println(nodo.dato);

        if (nodo.izquierda != null || nodo.derecha != null) {
            parenthesize(nodo.izquierda, nivel + 1);
            parenthesize(nodo.derecha, nivel + 1);
        }
    }

    public boolean isValidBST() {
        return validar(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validar(Nodo nodo, int min, int max) {
        if (nodo == null) return true;

        if (nodo.dato <= min || nodo.dato >= max)
            return false;

        return validar(nodo.izquierda, min, nodo.dato) &&
               validar(nodo.derecha, nodo.dato, max);
    }
}