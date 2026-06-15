package Ejercicio4;

import java.util.*;

public class GraphLink<E> implements Graph<E> {
    // Usamos un Map para asociar cada vértice con su lista de aristas
    // (GraphListEdge)
    private Map<E, List<GraphListEdge<E>>> adjacencyList;

    public GraphLink() {
        this.adjacencyList = new LinkedHashMap<>();
    }

    @Override
    public void insertVertex(E vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    @Override
    public void insertEdge(E v1, E v2) {
        insertVertex(v1);
        insertVertex(v2);
        if (!searchEdge(v1, v2)) {
            adjacencyList.get(v1).add(new GraphListEdge<>(v2));
        }
    }

    @Override
    public boolean searchVertex(E vertex) {
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public boolean searchEdge(E v1, E v2) {
        if (!adjacencyList.containsKey(v1))
            return false;
        for (GraphListEdge<E> edge : adjacencyList.get(v1)) {
            if (edge.target.equals(v2))
                return true;
        }
        return false;
    }

    @Override
    public List<E> adjacentVertices(E vertex) {
        List<E> adjs = new ArrayList<>();
        if (adjacencyList.containsKey(vertex)) {
            for (GraphListEdge<E> edge : adjacencyList.get(vertex)) {
                adjs.add(edge.target);
            }
        }
        return adjs;
    }

    @Override
    public List<E> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    @Override
    public int getVertexCount() {
        return adjacencyList.size();
    }

    @Override
    public int getEdgeCount() {
        int count = 0;
        for (List<GraphListEdge<E>> edges : adjacencyList.values()) {
            count += edges.size();
        }
        return count;
    }
}