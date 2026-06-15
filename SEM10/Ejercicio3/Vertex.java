package Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Vertex<E> {
    public E data;
    public List<Edge<E>> adjEdges; // Lista de adyacencia interna

    public Vertex(E data) {
        this.data = data;
        this.adjEdges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vertex<?> vertex = (Vertex<?>) obj;
        return data.equals(vertex.data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}