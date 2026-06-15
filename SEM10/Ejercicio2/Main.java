package Ejercicio2;

public class Main {
        public static void main(String[] args) {
                // Crear la red de ciudades
                RedCiudades red = new RedCiudades();

                // 1. Agregar las ciudades del ejemplo
                red.agregarCiudad("Arequipa");
                red.agregarCiudad("Cusco");
                red.agregarCiudad("Puno");
                red.agregarCiudad("Tacna");
                red.agregarCiudad("Moquegua");

                // 2. Agregar las conexiones con sus respectivas distancias
                red.agregarCarretera("Arequipa", "Cusco", 510);
                red.agregarCarretera("Arequipa", "Moquegua", 230);
                red.agregarCarretera("Moquegua", "Tacna", 160);
                red.agregarCarretera("Cusco", "Puno", 390);
                red.agregarCarretera("Puno", "Tacna", 420);

                // 3. Mostrar la información en consola
                red.mostrarCiudades();
                red.mostrarConexiones();

                // 4. Calcular camino más corto (Ejemplo de Tacna a Cusco)
                // Tomará la ruta óptima: Tacna -> Moquegua -> Arequipa -> Cusco (Costo:
                // 160+230+510 = 900)
                // En lugar de Tacna -> Puno -> Cusco (Costo: 420+390 = 810) -> Espera,
                // ¡Dijkstra elegirá la de 810!
                red.calcularRutaOptima("Tacna", "Cusco");

                // Otro ejemplo: Arequipa a Puno
                red.calcularRutaOptima("Arequipa", "Puno");
        }
}