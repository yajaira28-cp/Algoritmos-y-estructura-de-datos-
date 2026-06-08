package Actividad3;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    private static int contadorGlobalIds = 1; // Variable global estatica para auto-incrementar
    protected int idNode; // Identificador unico de este nodo especifico

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;

        this.idNode = contadorGlobalIds;
        contadorGlobalIds++;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    public boolean nodeFull(int n) {
        return this.count == (n - 1);
    }

    public boolean nodeEmpty() {
        return this.count == 0;
    }

    // METODO: searchNode, Busca una clave en el nodo actual.
    // Retorna un objeto personalizado "ResultadoBusqueda" que contiene Un boolean y
    // Un entero
    public ResultadoBusqueda searchNode(E key) {
        int i = 0;

        // Avanzamos en el ArrayList mientras no nos pasemos del conteo real y la clave
        // del nodo sea menor que la clave que buscamos
        while (i < this.count && this.keys.get(i).compareTo(key) < 0) {
            i++;
        }

        // Si salimos del bucle y encontramos la clave exacta
        if (i < this.count && this.keys.get(i).compareTo(key) == 0) {
            return new ResultadoBusqueda(true, i); // True: Encontrado en la posicion i
        } else {
            return new ResultadoBusqueda(false, i); // False: No esta, debes bajar por el hijo i
        }
    }

    // Devuelve el idNode junto con sus claves vigentes
    @Override
    public String toString() {
        String resultado = "Nodo ID [" + this.idNode + "] -> Claves: [";
        for (int i = 0; i < this.count; i++) {
            resultado += this.keys.get(i);
            if (i < this.count - 1) {
                resultado += " | ";
            }
        }
        resultado += "]";
        return resultado;
    }
}

class ResultadoBusqueda {
    public boolean encontrado;
    public int posicion;

    public ResultadoBusqueda(boolean encontrado, int posicion) {
        this.encontrado = encontrado;
        this.posicion = posicion;
    }
}