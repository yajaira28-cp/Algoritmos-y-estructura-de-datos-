package EJERCICIOS7.Ejercicio3;

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
    private int contarHojas() {
        if (raiz == null) return 0;

        java.util.Queue<Nodo> cola = new java.util.LinkedList<>();
        cola.add(raiz);
        int hojas = 0;

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            if (actual.izquierda == null && actual.derecha == null)
                hojas++;

            if (actual.izquierda != null) cola.add(actual.izquierda);
            if (actual.derecha != null) cola.add(actual.derecha);
        }
        return hojas;
    }
    private int altura() {
        if (raiz == null) return 0;

        java.util.Queue<Nodo> cola = new java.util.LinkedList<>();
        cola.add(raiz);
        int altura = 0;

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
    public int areaBST() {
        int hojas = contarHojas();
        int altura = altura();
        return hojas * altura;
    }

    // ===== b) DIBUJAR ÁRBOL
    public String toString() {
        return dibujar(raiz, 0);
    }

    private String dibujar(Nodo nodo, int nivel) {
        if (nodo == null) return "";

        String resultado = "";
        resultado += dibujar(nodo.derecha, nivel + 1);

        for (int i = 0; i < nivel; i++) {
            resultado += "   ";
        }
        resultado += nodo.dato + "\n";

        resultado += dibujar(nodo.izquierda, nivel + 1);

        return resultado;
    }
}