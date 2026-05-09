package EJERCICIOS7.Ejercicio5;

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
    public void searchRange(int min, int max) {
        System.out.print("Productos en rango: ");
        searchRange(raiz, min, max);
        System.out.println();
    }

    private void searchRange(Nodo nodo, int min, int max) {
        if (nodo == null) return;

        if (nodo.dato > min)
            searchRange(nodo.izquierda, min, max);

        if (nodo.dato >= min && nodo.dato <= max)
            System.out.print(nodo.dato + " ");

        if (nodo.dato < max)
            searchRange(nodo.derecha, min, max);
    }
    public int countLeaves() {
        return countLeaves(raiz);
    }

    private int countLeaves(Nodo nodo) {
        if (nodo == null) return 0;

        if (nodo.izquierda == null && nodo.derecha == null)
            return 1;

        return countLeaves(nodo.izquierda) + countLeaves(nodo.derecha);
    }
    public void printDescending() {
        System.out.print("Productos descendente: ");
        printDescending(raiz);
        System.out.println();
    }

    private void printDescending(Nodo nodo) {
        if (nodo == null) return;

        printDescending(nodo.derecha);
        System.out.print(nodo.dato + " ");
        printDescending(nodo.izquierda);
    }
}