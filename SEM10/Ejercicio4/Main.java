package Ejercicio4;

public class Main {
    public static void main(String[] args) {
        System.out.println(" ANALIZADOR DE PROPIEDADES DE GRAFOS \n");

        GraphLink<String> g1 = new GraphLink<>();
        g1.insertEdge("A", "B");
        g1.insertEdge("B", "C");
        g1.insertEdge("C", "A");

        System.out.println(" Análisis del Grafo 1 (Triángulo Dirigido) ");
        System.out.println("¿Es Conexo?: " + GraphAnalyzer.esConexo(g1));
        System.out.println("¿Es Plano?: " + GraphAnalyzer.esPlano(g1));

        // --- PRUEBA 2: Isomorfismo ---
        GraphLink<String> g2 = new GraphLink<>();
        g2.insertEdge("X", "Y");
        g2.insertEdge("Y", "Z");
        g2.insertEdge("Z", "X");

        System.out.println("\n Análisis de Isomorfismo entre Grafo 1 y Grafo 2 ");
        System.out.println("¿Grafo 1 es Isomorfo con Grafo 2?: " + GraphAnalyzer.sonIsomorfos(g1, g2));

        GraphLink<Integer> k5 = new GraphLink<>();
        int n = 5;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j)
                    k5.insertEdge(i, j); // Conectar todos con todos
            }
        }
        System.out.println("\n Análisis del Grafo K5 (Altamente conectado) ");
        System.out.println("¿Es Plano?: " + GraphAnalyzer.esPlano(k5) + " (Esperado: false por exceso de aristas)");

        GraphLink<Integer> p4 = new GraphLink<>();
        p4.insertEdge(1, 2);
        p4.insertEdge(2, 3);
        p4.insertEdge(3, 4);

        System.out.println("\n Análisis de Grafo Auto-complementario ");
        System.out.println("¿El camino P4 es Auto-complementario?: " + GraphAnalyzer.esAutoComplementario(p4));
    }
}