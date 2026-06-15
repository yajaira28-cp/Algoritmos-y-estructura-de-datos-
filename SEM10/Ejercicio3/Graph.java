package Ejercicio3;

import java.util.List;

public interface Graph<E> {
    void insertVertex(E vertex);

    void insertEdge(E v1, E v2, double weight);

    void removeVertex(E vertex);

    void removeEdge(E v1, E v2);

    boolean searchVertex(E vertex);

    boolean searchEdge(E v1, E v2);

    List<E> adjacentVertices(E vertex);
}