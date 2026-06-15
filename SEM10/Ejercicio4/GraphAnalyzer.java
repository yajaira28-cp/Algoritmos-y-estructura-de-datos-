package Ejercicio4;

import java.util.*;

public class GraphAnalyzer {

    public static <E> boolean esConexo(Graph<E> graph) {
        if (graph.getVertexCount() == 0)
            return true;

        List<E> vertices = graph.getVertices();
        E inicio = vertices.get(0);

        Set<E> visitados = new HashSet<>();
        Queue<E> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            E actual = cola.poll();

            // Buscar vecinos salientes Y entrantes (tratamiento no dirigido)
            for (E v : vertices) {
                if (!visitados.contains(v)) {
                    if (graph.searchEdge(actual, v) || graph.searchEdge(v, actual)) {
                        visitados.add(v);
                        cola.add(v);
                    }
                }
            }
        }
        return visitados.size() == graph.getVertexCount();
    }

    public static <E> boolean esPlano(Graph<E> graph) {
        int v = graph.getVertexCount();
        int a = graph.getEdgeCount();

        if (v < 3)
            return true;
        return a <= (3 * v - 6);
    }

    public static <E> boolean sonIsomorfos(Graph<E> g1, Graph<E> g2) {
        if (g1.getVertexCount() != g2.getVertexCount() || g1.getEdgeCount() != g2.getEdgeCount()) {
            return false;
        }

        List<E> v1 = g1.getVertices();
        List<E> v2 = g2.getVertices();

        return evaluarPermutaciones(v1, v2, 0, g1, g2);
    }

    private static <E> boolean evaluarPermutaciones(List<E> v1, List<E> v2, int index, Graph<E> g1, Graph<E> g2) {
        if (index == v2.size()) {
            return verificarMapeoEstructural(v1, v2, g1, g2);
        }

        for (int i = index; i < v2.size(); i++) {
            Collections.swap(v2, index, i);
            if (evaluarPermutaciones(v1, v2, index + 1, g1, g2))
                return true;
            Collections.swap(v2, index, i); // Backtracking
        }
        return false;
    }

    private static <E> boolean verificarMapeoEstructural(List<E> v1, List<E> v2, Graph<E> g1, Graph<E> g2) {
        int n = v1.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean tieneAristaG1 = g1.searchEdge(v1.get(i), v1.get(j));
                boolean tieneAristaG2 = g2.searchEdge(v2.get(i), v2.get(j));
                if (tieneAristaG1 != tieneAristaG2)
                    return false;
            }
        }
        return true;
    }

    public static <E> boolean esAutoComplementario(Graph<E> graph) {
        GraphLink<E> complemento = new GraphLink<>();
        List<E> vertices = graph.getVertices();

        // Inicializar vértices
        for (E v : vertices) {
            complemento.insertVertex(v);
        }
        for (E u : vertices) {
            for (E v : vertices) {
                if (!u.equals(v) && !graph.searchEdge(u, v)) {
                    complemento.insertEdge(u, v);
                }
            }
        }
        return sonIsomorfos(graph, complemento);
    }
}