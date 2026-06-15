package Ejercicio3;

public class Main {
    public static void main(String[] args) {
        // Instanciamos el TAD implementado con enlaces de listas
        GraphLink<String> mapaCiudades = new GraphLink<>();

        mapaCiudades.insertVertex("Arequipa");
        mapaCiudades.insertVertex("Cusco");
        mapaCiudades.insertVertex("Puno");
        mapaCiudades.insertVertex("Tacna");
        mapaCiudades.insertVertex("Moquegua");

        mapaCiudades.insertEdge("Arequipa", "Cusco", 510);
        mapaCiudades.insertEdge("Arequipa", "Moquegua", 230);
        mapaCiudades.insertEdge("Moquegua", "Tacna", 160);
        mapaCiudades.insertEdge("Cusco", "Puno", 390);
        mapaCiudades.insertEdge("Puno", "Tacna", 420);

        mapaCiudades.mostrarGrafo();

        mapaCiudades.dijkstra("Tacna", "Cusco");
        mapaCiudades.dijkstra("Arequipa", "Puno");
    }
}