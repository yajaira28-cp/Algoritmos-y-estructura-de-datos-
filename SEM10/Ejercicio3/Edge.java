package Ejercicio3;

public class Edge<E> {
    public Vertex<E> target; // Ciudad destino
    public double weight; // Distancia en kilómetros

    public Edge(Vertex<E> target, double weight) {
        this.target = target;
        this.weight = weight;
    }
}