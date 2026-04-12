package LABORATORIO4;

import java.util.Arrays;

public class CambioMoneda {
    public static void main(String[] args) {
        int[] monedas = {1, 5, 6, 9};
        int objetivo = 11;

        int resultado = calcularMinimoMonedas(monedas, objetivo);

        if (resultado != -1) {
            System.out.println("El número mínimo de monedas para " + objetivo + " es: " + resultado);
        } else {
            System.out.println("No es posible formar el valor con las monedas dadas.");
        }
    }
    public static int calcularMinimoMonedas(int[] monedas, int M) {
        int[] dp = new int[M + 1];
        Arrays.fill(dp, M + 1);

        dp[0] = 0;

        for (int i = 1; i <= M; i++) {
            for (int moneda : monedas) {
                if (i >= moneda) {
                    dp[i] = Math.min(dp[i], dp[i - moneda] + 1);
                }
            }
        }
        return dp[M] > M ? -1 : dp[M];
    }
}