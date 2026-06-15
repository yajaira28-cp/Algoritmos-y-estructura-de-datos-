import java.util.*;

public class GraphLink<E> {

    private List<Vertex<E>> vertices;

    public GraphLink() {
        this.vertices = new ArrayList<>();
    }

    public void insertVertex(E value) {
        if (findVertex(value) == null) {
            vertices.add(new Vertex<>(value));
        }
    }

    public void insertEdgeWeight(E v, E z, int w) {
        Vertex<E> vertexV = findVertex(v);
        Vertex<E> vertexZ = findVertex(z);

        if (vertexV != null && vertexZ != null) {
            vertexV.addEdge(new Edge<>(vertexZ, w));
            vertexZ.addEdge(new Edge<>(vertexV, w));
        } else {
            System.out.println("Uno o ambos vertices no existen en el grafo.");
        }
    }

    public Stack<Vertex<E>> Dijkstra(E v, E w) {
        Vertex<E> start = findVertex(v);
        Vertex<E> target = findVertex(w);

        if (start == null || target == null) return null;

        Map<Vertex<E>, Integer> distances = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        
        PriorityQueue<VertexDistancePair<E>> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));

        for (Vertex<E> vertex : getAllVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        
        distances.put(start, 0);
        pq.add(new VertexDistancePair<>(start, 0));

        while (!pq.isEmpty()) {
            VertexDistancePair<E> currentPair = pq.poll();
            Vertex<E> currentVertex = currentPair.vertex;

            if (currentVertex.equals(target)) break;

            if (currentPair.distance > distances.get(currentVertex)) continue;

            for (Edge<E> edge : currentVertex.getEdges()) {
                Vertex<E> neighbor = edge.getDestination();
                int newDist = distances.get(currentVertex) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, currentVertex);
                    pq.add(new VertexDistancePair<>(neighbor, newDist));
                }
            }
        }

        Stack<Vertex<E>> pathStack = new Stack<>();
        Vertex<E> step = target;

        if (predecessors.get(step) == null && !step.equals(start)) {
            return null;
        }

        while (step != null) {
            pathStack.push(step);
            step = predecessors.get(step);
        }

        return pathStack;
    }

    public ArrayList<Vertex<E>> shortPath(E v, E z) {
        Stack<Vertex<E>> pathStack = Dijkstra(v, z);
        if (pathStack == null) return new ArrayList<>();

        ArrayList<Vertex<E>> pathList = new ArrayList<>();
        while (!pathStack.isEmpty()) {
            pathList.add(pathStack.pop());
        }

        return pathList;
    }

    public boolean isConexo() {
        List<Vertex<E>> allVertices = getAllVertices();
        if (allVertices.isEmpty()) return true;

        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        Vertex<E> start = allVertices.get(0);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            for (Edge<E> edge : current.getEdges()) {
                Vertex<E> neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == allVertices.size();
    }

    private Vertex<E> findVertex(E value) {
        for (Vertex<E> v : vertices) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null; 
    }

    private List<Vertex<E>> getAllVertices() {
        return this.vertices;
    }

    private static class VertexDistancePair<T> {
        Vertex<T> vertex;
        int distance;

        VertexDistancePair(Vertex<T> vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static class Vertex<T> {
        private T value;
        private List<Edge<T>> edges;

        public Vertex(T value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }

        public T getValue() {
            return value;
        }

        public List<Edge<T>> getEdges() {
            return edges;
        }

        public void addEdge(Edge<T> edge) {
            this.edges.add(edge);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public static class Edge<T> {
        private Vertex<T> destination;
        private int weight;

        public Edge(Vertex<T> destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public Vertex<T> getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>();
        
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        graph.insertEdgeWeight("A", "B", 3);
        graph.insertEdgeWeight("B", "C", 1);
        graph.insertEdgeWeight("A", "C", 7);
        graph.insertEdgeWeight("C", "D", 2);

        System.out.println("Es conexo: " + graph.isConexo());

        System.out.println("Ruta corta (shortPath A -> D): " + graph.shortPath("A", "D"));

        System.out.println("Ruta Dijkstra (Stack de D -> A de abajo hacia arriba): " + graph.Dijkstra("A", "D"));
    }
}