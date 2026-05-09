package EJERCICIOS7.Ejercicio2;

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

    public void destroyNodes() throws Exception {
        if (raiz == null) throw new Exception("Árbol vacío");
        raiz = null;
    }

    public int countAllNodes() {
        return countNoHoja(raiz);
    }

    private int countNoHoja(Nodo nodo) {
        if (nodo == null || (nodo.izquierda == null && nodo.derecha == null))
            return 0;

        return 1 + countNoHoja(nodo.izquierda) + countNoHoja(nodo.derecha);
    }

    public int countNodes() {
        return countHoja(raiz);
    }

    private int countHoja(Nodo nodo) {
        if (nodo == null) return 0;

        if (nodo.izquierda == null && nodo.derecha == null)
            return 1;

        return countHoja(nodo.izquierda) + countHoja(nodo.derecha);
    }

    public int height(int valor) {
        Nodo nodo = buscarNodo(raiz, valor);
        if (nodo == null) return -1;

        return alturaIterativa(nodo);
    }

    private Nodo buscarNodo(Nodo nodo, int valor) {
        while (nodo != null) {
            if (valor == nodo.dato) return nodo;
            if (valor < nodo.dato) nodo = nodo.izquierda;
            else nodo = nodo.derecha;
        }
        return null;
    }

    private int alturaIterativa(Nodo nodo) {
        java.util.Queue<Nodo> cola = new java.util.LinkedList<>();
        cola.add(nodo);
        int altura = -1;

        while (!cola.isEmpty()) {
            int tamaño = cola.size();
            altura++;

            for (int i = 0; i < tamaño; i++) {
                Nodo actual = cola.poll();

                if (actual.izquierda != null) cola.add(actual.izquierda);
                if (actual.derecha != null) cola.add(actual.derecha);
            }
        }
        return altura;
    }
    public int amplitud() {
        if (raiz == null) return 0;

        java.util.Queue<Nodo> cola = new java.util.LinkedList<>();
        cola.add(raiz);
        int max = 0;

        while (!cola.isEmpty()) {
            int tamaño = cola.size();
            max = Math.max(max, tamaño);

            for (int i = 0; i < tamaño; i++) {
                Nodo actual = cola.poll();

                if (actual.izquierda != null) cola.add(actual.izquierda);
                if (actual.derecha != null) cola.add(actual.derecha);
            }
        }
        return max;
    }
}