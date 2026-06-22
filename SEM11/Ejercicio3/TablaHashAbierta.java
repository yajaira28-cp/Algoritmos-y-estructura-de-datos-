package Ejercicio3;

public class TablaHashAbierta {
    // Clase interna para representar cada nodo de la lista enlazada
    private static class Nodo {
        int clave;
        String nombre;
        Nodo siguiente;

        public Nodo(int clave, String nombre) {
            this.clave = clave;
            this.nombre = nombre;
            this.siguiente = null;
        }
    }

    private static final int TAMANO = 7;
    private Nodo[] tabla;

    public TablaHashAbierta() {
        tabla = new Nodo[TAMANO]; // En Java un arreglo de objetos se inicializa automáticamente con null
    }

    // Función hash h(k) = k % 7
    private int funcionHash(int k) {
        return k % TAMANO;
    }

    // Método para insertar (añade al final de la lista enlazada para mantener el
    // orden del ejercicio)
    public void insertar(int clave, String nombre) {
        int indice = funcionHash(clave);
        Nodo nuevoNodo = new Nodo(clave, nombre);

        if (tabla[indice] == null) {
            tabla[indice] = nuevoNodo;
        } else {
            Nodo actual = tabla[indice];
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    // Método para buscar una clave
    public void buscar(int clave) {
        int indice = funcionHash(clave);
        Nodo actual = tabla[indice];
        int posicionNodo = 1;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.clave == clave) {
                System.out.println("-> Clave " + clave + " ENCONTRADA. Nombre: \"" + actual.nombre + "\"");
                System.out.println("   Ubicación: Posición de tabla [" + indice + "], Nodo número " + posicionNodo
                        + " de la lista.");
                encontrado = true;
                break;
            }
            actual = actual.siguiente;
            posicionNodo++;
        }
        if (!encontrado)
            System.out.println("Clave " + clave + " no existe.");
    }

    // Método para eliminar una clave
    public void eliminar(int clave) {
        int indice = funcionHash(clave);
        Nodo actual = tabla[indice];
        Nodo anterior = null;

        while (actual != null) {
            if (actual.clave == clave) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente; // Era el primer elemento
                } else {
                    anterior.siguiente = actual.siguiente; // Se salta el elemento actual
                }
                System.out.println(" Clave " + clave + " eliminada con éxito.");
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        System.out.println("Clave " + clave + " no encontrada para eliminar.");
    }

    // Método para mostrar el estado actual de la tabla
    public void mostrarTabla() {
        System.out.println("Estado de la Tabla Hash:");
        for (int i = 0; i < TAMANO; i++) {
            System.out.print("[" + i + "] -> ");
            Nodo actual = tabla[i];
            if (actual == null) {
                System.out.print("null");
            } else {
                while (actual != null) {
                    System.out.print("[" + actual.clave + ": \"" + actual.nombre + "\"] -> ");
                    actual = actual.siguiente;
                }
                System.out.print("null");
            }
            System.out.println();
        }
    }

    // Método principal de prueba
    public static void main(String[] args) {
        TablaHashAbierta hash = new TablaHashAbierta();

        // 1. Insertar elementos
        hash.insertar(10, "Juan");
        hash.insertar(17, "Ana");
        hash.insertar(24, "Luis");
        hash.insertar(31, "Rosa");
        hash.insertar(5, "Pedro");
        hash.insertar(12, "Carla");

        System.out.println(" 1. ESTADO INICIAL DE LA TABLA ");
        hash.mostrarTabla();
        System.out.println();

        // 2. Buscar clave 24
        System.out.println(" 2. OPERACIÓN DE BÚSQUEDA ");
        hash.buscar(24);
        System.out.println();

        // 3. Eliminar clave 17 y ver estado final
        System.out.println(" 3. OPERACIÓN DE ELIMINACIÓN (Clave 17) ");
        hash.eliminar(17);
        hash.mostrarTabla();
    }
}