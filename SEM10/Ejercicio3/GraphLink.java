package Ejercicio3;

import java.util.*;

public class GraphLink<E> implements Graph<E> {
    private List<Vertex<E>> vertices;

    public GraphLink() {
        this.vertices = new ArrayList<>();
    }

    // Método auxiliar para buscar el objeto Vertex según el dato genérico
    private Vertex<E> findVertex(E data) {
        for (Vertex<E> v : vertices) {
            if (v.data.equals(data))
                return v;
        }
        return null;
    }

    @Override
    public void insertVertex(E vertex) {
        if (!searchVertex(vertex)) {
            vertices.add(new Vertex<>(vertex));
        }
    }

    @Override
    public void insertEdge(E v1, E v2, double weight) {
        // Asegurar que existan ambos nodos antes de conectarlos
        insertVertex(v1);
        insertVertex(v2);

        Vertex<E> u = findVertex(v1);
        Vertex<E> v = findVertex(v2);

        // Al ser no dirigido, se agrega la arista en ambos sentidos
        if (!searchEdge(v1, v2)) {
            u.adjEdges.add(new Edge<>(v, weight));
            v.adjEdges.add(new Edge<>(u, weight));
        }
    }

    @Override
    public void removeVertex(E vertex) {
        Vertex<E> vToRemove = findVertex(vertex);
        if (vToRemove == null)
            return;

        for (Vertex<E> v : vertices) {
            v.adjEdges.removeIf(edge -> edge.target.equals(vToRemove));
        }
        vertices.remove(vToRemove);
    }

    @Override
    public void removeEdge(E v1, E v2) {
        Vertex<E> u = findVertex(v1);
        Vertex<E> v = findVertex(v2);
        if (u != null && v != null) {
            u.adjEdges.removeIf(edge -> edge.target.equals(v));
            v.adjEdges.removeIf(edge -> edge.target.equals(u));
        }
    }

    @Override
    public boolean searchVertex(E vertex) {
        return findVertex(vertex) != null;
    }

    @Override
    public boolean searchEdge(E v1, E v2) {
        Vertex<E> u = findVertex(v1);
        if (u == null)
            return false;
        for (Edge<E> edge : u.adjEdges) {
            if (edge.target.data.equals(v2))
                return true;
        }
        return false;
    }

    @Override
    public List<E> adjacentVertices(E vertex) {
        List<E> adjs = new ArrayList<>();
        Vertex<E> v = findVertex(vertex);
        if (v != null) {
            for (Edge<E> edge : v.adjEdges) {
                adjs.add(edge.target.data);
            }
        }
        return adjs;
    }

    // Funcionalidad para mostrar el grafo completo en consola
    public void mostrarGrafo() {
        System.out.println("--- RED DE CIUDADES (LISTAS DE ADYACENCIA) ---");
        for (Vertex<E> v : vertices) {
            System.out.print("Ciudad [" + v.data + "] conectada con: ");
            if (v.adjEdges.isEmpty()) {
                System.out.print("Ninguna");
            } else {
                for (Edge<E> edge : v.adjEdges) {
                    System.out.print(edge.target.data + " (" + edge.weight + " km) | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Algoritmo de Dijkstra integrado directamente sobre el TAD nativo
    public void dijkstra(E origen, E destino) {
        System.out.println("--- DIJKSTRA: CAMINO MÁS CORTO DESDE [" + origen + "] HASTA [" + destino + "] ---");

        if (!searchVertex(origen) || !searchVertex(destino)) {
            System.out.println("Error: Alguna de las ciudades no está registrada.");
            return;
        }

        Map<E, Double> distancias = new HashMap<>();
        Map<E, E> predecesores = new HashMap<>();
        PriorityQueue<NodeDistance<E>> colaPrioridad = new PriorityQueue<>(
                Comparator.comparingDouble(nd -> nd.distance));

        // Inicializar distancias iniciales en infinito
        for (Vertex<E> v : vertices) {
            distancias.put(v.data, Double.MAX_VALUE);
        }

        distancias.put(origen, 0.0);
        colaPrioridad.add(new NodeDistance<>(origen, 0.0));

        while (!colaPrioridad.isEmpty()) {
            NodeDistance<E> actual = colaPrioridad.poll();
            E uData = actual.vertexData;

            if (uData.equals(destino))
                break; // Llegamos al objetivo óptimo

            Vertex<E> u = findVertex(uData);
            for (Edge<E> edge : u.adjEdges) {
                E vData = edge.target.data;
                double nuevaDistancia = distancias.get(uData) + edge.weight;

                if (nuevaDistancia < distancias.get(vData)) {
                    distancias.put(vData, nuevaDistancia);
                    predecesores.put(vData, uData);
                    colaPrioridad.add(new NodeDistance<>(vData, nuevaDistancia));
                }
            }
        }

        // Evaluar e imprimir resultados
        if (distancias.get(destino) == Double.MAX_VALUE) {
            System.out.println("No se encontró ninguna ruta disponible.");
        } else {
            List<E> caminoCompleto = new LinkedList<>();
            E paso = destino;
            while (paso != null) {
                caminoCompleto.add(0, paso);
                paso = predecesores.get(paso);
            }
            System.out.println("Ruta Óptima: " + caminoCompleto);
            System.out.println("Distancia Total Mínima: " + distancias.get(destino) + " km\n");
        }
    }

    // Clase interna para gestionar la cola de prioridad de Dijkstra
    private static class NodeDistance<E> {
        E vertexData;
        double distance;

        NodeDistance(E vertexData, double distance) {
            this.vertexData = vertexData;
            this.distance = distance;
        }
    }
}