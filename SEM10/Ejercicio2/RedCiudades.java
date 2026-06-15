
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.List;

public class RedCiudades {
    // Definimos un grafo no dirigido y ponderado
    private Graph<String, DefaultWeightedEdge> grafo;

    public RedCiudades() {
        // Inicializamos el grafo usando la implementación de JGraphT
        this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    // Funcionalidad: Agregar ciudades (Vértices)
    public void agregarCiudad(String ciudad) {
        if (!grafo.containsVertex(ciudad)) {
            grafo.addVertex(ciudad);
        }
    }

    // Funcionalidad: Agregar carreteras con distancia (Aristas ponderadas)
    public void agregarCarretera(String origen, String destino, double distancia) {
        // Nos aseguramos de que ambos vértices existan antes de crear la arista
        agregarCiudad(origen);
        agregarCiudad(destino);

        // Añadimos la arista y le asignamos el peso (distancia)
        DefaultWeightedEdge arista = grafo.addEdge(origen, destino);
        if (arista != null) {
            grafo.setEdgeWeight(arista, distancia);
        }
    }

    // Mostrar todas las ciudades registradas
    public void mostrarCiudades() {
        System.out.println("--- LISTA DE CIUDADES ---");
        for (String ciudad : grafo.vertexSet()) {
            System.out.println("- " + ciudad);
        }
        System.out.println();
    }

    // Funcionalidad: Mostrar todas las conexiones del grafo
    public void mostrarConexiones() {
        System.out.println("--- CARRETERAS REGISTRADAS ---");
        for (DefaultWeightedEdge arista : grafo.edgeSet()) {
            String origen = grafo.getEdgeSource(arista);
            String destino = grafo.getEdgeTarget(arista);
            double distancia = grafo.getEdgeWeight(arista);
            System.out.println(origen + " <---> " + destino + " (" + distancia + " km)");
        }
        System.out.println();
    }

    // Funcionalidad: Calcular el camino más corto usando Dijkstra
    public void calcularRutaOptima(String origen, String destino) {
        System.out.println("--- BUSCANDO RUTA ÓPTIMA: " + origen + " a " + destino + " ---");

        // Verificamos que las ciudades existan en el grafo
        if (!grafo.containsVertex(origen) || !grafo.containsVertex(destino)) {
            System.out.println("Error: Una o ambas ciudades no existen en la red.");
            return;
        }

        // Instanciamos el algoritmo de Dijkstra provisto por JGraphT
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);

        // Obtenemos la ruta (path)
        var ruta = dijkstra.getPath(origen, destino);

        if (ruta != null) {
            // Obtener la lista de vértices (el camino secuencial)
            List<String> listaCiudades = ruta.getVertexList();
            double costoTotal = ruta.getWeight();

            System.out.println("Camino más corto: " + String.join(" -> ", listaCiudades));
            System.out.println("Costo total (Distancia): " + costoTotal + " km");
        } else {
            System.out.println("No existe una ruta de carreteras que conecte " + origen + " con " + destino + ".");
        }
        System.out.println();
    }
}