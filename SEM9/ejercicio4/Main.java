package ejercicio4;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        
        System.out.println("--- 1. Cargando Datos desde biblioteca.txt ---");
        biblioteca.cargarDesdeArchivo("biblioteca.txt");

        System.out.println("\n--- 2. Mostrar Libros Ordenados por ISBN ---");
        biblioteca.mostrarLibrosOrdenados();

        System.out.println("\n--- 3. Buscar un libro especifico ---");
        biblioteca.buscarPorIsbn("9780134494166");

        System.out.println("\n--- 4. Mostrar Metricas del Sistema ---");
        biblioteca.mostrarMetricas();
    }
}