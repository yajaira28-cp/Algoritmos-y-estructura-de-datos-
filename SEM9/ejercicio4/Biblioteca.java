package ejercicio4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Biblioteca {
    private BTree<Libro> arbolLibros;

    public void cargarDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = br.readLine();
            if (linea == null) {
                System.out.println("El archivo esta vacio");
                return;
            }
            int orden = Integer.parseInt(linea.trim());
            int t = orden / 2;
            if (t < 2) t = 2;
            arbolLibros = new BTree<>(t);

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    String isbn = datos[0].trim();
                    String titulo = datos[1].trim();
                    String autor = datos[2].trim();
                    int anio = Integer.parseInt(datos[3].trim());

                    Libro nuevoLibro = new Libro(isbn, titulo, autor, anio);
                    if (!arbolLibros.searchWithRoute(nuevoLibro)) {
                        arbolLibros.insert(nuevoLibro);
                    } else {
                        System.out.println("Ignorado ISBN duplicado: " + isbn);
                    }
                }
            }
            System.out.println("Carga masiva completada exitosamente");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error procesando el archivo: " + e.getMessage());
        }
    }

    public void buscarPorIsbn(String isbn) {
        if (arbolLibros == null) {
            System.out.println("La biblioteca no ha sido inicializada");
            return;
        }
        Libro dummy = new Libro(isbn, "", "", 0);
        boolean encontrado = arbolLibros.searchWithRoute(dummy);
        System.out.println("¿Libro encontrado?: " + encontrado);
    }

    public void mostrarLibrosOrdenados() {
        if (arbolLibros != null) {
            arbolLibros.showOrdered();
        }
    }

    public void mostrarMetricas() {
        if (arbolLibros != null) {
            System.out.println("Altura del Arbol B: " + arbolLibros.getHeight());
            System.out.println("Cantidad total de libros: " + arbolLibros.size());
        }
    }
}