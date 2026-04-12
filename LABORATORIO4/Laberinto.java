package LABORATORIO4;

public class Laberinto {
    public static void main(String[] args) {
        int[][] laberinto = {
            {0, 1, 1},
            {1, 0, 1},
            {1, 0, 0}
        };

        int n = laberinto.length;
        int[][] solucion = new int[n][n];

        if (resolverLaberinto(laberinto, 0, 0, solucion)) {
            System.out.println("Salida: true");
            System.out.println("Camino encontrado (marcado con 1):");
            imprimirMatriz(solucion);
        } else {
            System.out.println("Salida: false");
        }
    }
    public static boolean resolverLaberinto(int[][] lab, int x, int y, int[][] sol) {
        int n = lab.length;

        // Caso base: Si llegamos a la esquina inferior derecha
        if (x == n - 1 && y == n - 1 && lab[x][y] == 0) {
            sol[x][y] = 1; // Marcar el paso final
            return true;
        }

        // Verificar si la celda (x, y) es valida para moverse
        if (esSeguro(lab, x, y, sol)) {
            sol[x][y] = 1;

            if (resolverLaberinto(lab, x + 1, y, sol)) return true;

            if (resolverLaberinto(lab, x, y + 1, sol)) return true;

            if (resolverLaberinto(lab, x - 1, y, sol)) return true;

            if (resolverLaberinto(lab, x, y - 1, sol)) return true;

            sol[x][y] = 0;
            return false;
        }
        return false;
    }
    private static boolean esSeguro(int[][] lab, int x, int y, int[][] sol) {
        int n = lab.length;
        return (x >= 0 && x < n && y >= 0 && y < n && lab[x][y] == 0 && sol[x][y] == 0);
    }

    private static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
