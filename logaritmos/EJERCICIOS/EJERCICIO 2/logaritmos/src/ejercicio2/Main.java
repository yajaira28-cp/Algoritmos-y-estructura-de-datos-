package ejercicio2;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            
            String primeraLinea = br.readLine();
            if (primeraLinea == null) return;
            
            String[] dim = primeraLinea.split(" ");
            int filas = Integer.parseInt(dim[0]);
            int columnas = Integer.parseInt(dim[1]);

            Terreno terreno = new Terreno(filas, columnas);

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    String linea = br.readLine();
                    if (linea != null) {
                        String[] datos = linea.split(" ");
                        Zona z = new Zona(datos[0], Integer.parseInt(datos[1]), Double.parseDouble(datos[2]));
                        terreno.agregarZona(i, j, z);
                    }
                }
            }
            AnalizadorMineria analizador = new AnalizadorMineria(terreno);
            analizador.encontrarRegionMasValiosa();
        } 
        catch (IOException e) {
            System.out.println("Error al leer archivo");
        } 
        catch (Exception e) {
            System.out.println("Error en el formato de datos: " + e.getMessage());
        }
    }
}