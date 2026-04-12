package LABORATORIO4;
public class CosteEmbarcaderos {

    public static void main(String[] args) {
        double[][] T = {
            {0, 2, 5, 10},
            {999, 0, 2, 7},
            {999, 999, 0, 1},
            {999, 999, 999, 0}
        };

        int n = T.length;
        double[][] C = calcularCostesMinimos(T, n);

        // Mostrar la matriz de costes mínimos C
        System.out.println("Matriz de Costes Mínimos:");
        imprimirMatriz(C);
    }
    public static double[][] calcularCostesMinimos(double[][] T, int n) {
        // Inicializamos la matriz C con los mismos valores de la tabla de tarifas T
        double[][] C = new double[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = T[i][j];
            }
        }
        for (int distancia = 2; distancia < n; distancia++) {
            for (int i = 0; i < n - distancia; i++) {
                int j = i + distancia;

                for (int k = i + 1; k < j; k++) {
                    double costeConEscala = T[i][k] + C[k][j];
                    if (costeConEscala < C[i][j]) {
                        C[i][j] = costeConEscala;
                    }
                }
            }
        }
        return C;
    }
    public static void imprimirMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 999) System.out.print("INF\t");
                else System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}