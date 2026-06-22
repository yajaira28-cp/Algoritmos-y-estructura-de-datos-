package Ejercicio4;

public class TablaHashEstados {

    // Definición de los tres estados posibles
    enum Estado {
        EMPTY, OCCUPIED, DELETED
    }

    // Clase para representar cada entrada de la tabla
    private static class Entry {
        int clave;
        Estado estado;

        public Entry() {
            this.clave = -1;
            this.estado = Estado.EMPTY;
        }
    }

    private static final int TAMANO = 7;
    private Entry[] tabla;

    public TablaHashEstados() {
        tabla = new Entry[TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            tabla[i] = new Entry();
        }
    }

    private int funcionHash(int clave) {
        return clave % TAMANO;
    }

    // Insertar clave (reutiliza celdas DELETED)
    public void insertar(int clave) {
        int hashBase = funcionHash(clave);
        int i = 0;
        int posicion = hashBase;
        int posicionDeleted = -1;

        while (tabla[posicion].estado != Estado.EMPTY) {
            // Si encontramos una celda DELETED, recordamos su posición para reutilizarla
            if (tabla[posicion].estado == Estado.DELETED && posicionDeleted == -1) {
                posicionDeleted = posicion;
            }
            // Si la clave ya existe y está ocupada, no la duplicamos
            if (tabla[posicion].estado == Estado.OCCUPIED && tabla[posicion].clave == clave) {
                return;
            }
            i++;
            posicion = (hashBase + i) % TAMANO;
            if (i == TAMANO)
                break; // Tabla llena
        }
        // Si encontramos un hueco DELETED en el camino, lo priorizamos
        int indiceDestino = (posicionDeleted != -1) ? posicionDeleted : posicion;

        tabla[indiceDestino].clave = clave;
        tabla[indiceDestino].estado = Estado.OCCUPIED;
        System.out.println("Insertado " + clave + " en índice " + indiceDestino);
    }

    // Eliminación lógica
    public void eliminar(int clave) {
        int hashBase = funcionHash(clave);
        int i = 0;
        int posicion = hashBase;

        while (tabla[posicion].estado != Estado.EMPTY) {
            if (tabla[posicion].estado == Estado.OCCUPIED && tabla[posicion].clave == clave) {
                tabla[posicion].estado = Estado.DELETED; // Eliminación lógica
                System.out.println("-> Clave " + clave + " eliminada lógicamente en índice " + posicion);
                return;
            }
            i++;
            posicion = (hashBase + i) % TAMANO;
            if (i == TAMANO)
                break;
        }
        System.out.println("Clave " + clave + " no encontrada para eliminar.");
    }

    // Buscar clave (salta celdas DELETED)
    public void buscar(int clave) {
        int hashBase = funcionHash(clave);
        int i = 0;
        int posicion = hashBase;

        while (tabla[posicion].estado != Estado.EMPTY) {
            // Saltamos los DELETED pero seguimos buscando
            if (tabla[posicion].estado == Estado.OCCUPIED && tabla[posicion].clave == clave) {
                System.out.println("-> Clave " + clave + " ENCONTRADA en índice " + posicion);
                return;
            }
            i++;
            posicion = (hashBase + i) % TAMANO;
            if (i == TAMANO)
                break;
        }
        System.out.println(" Clave " + clave + " NO encontrada.");
    }

    public void mostrarTabla() {
        System.out.println("\nEstado actual de la tabla:");
        System.out.println("Índice\tClave\tEstado");
        System.out.println("-------------------------");
        for (int i = 0; i < TAMANO; i++) {
            String claveStr = (tabla[i].estado == Estado.EMPTY) ? "-" : String.valueOf(tabla[i].clave);
            System.out.println(i + "\t" + claveStr + "\t" + tabla[i].estado);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TablaHashEstados hash = new TablaHashEstados();

        System.out.println(" 1. INSERCIÓN INICIAL ");
        hash.insertar(5);
        hash.insertar(12);
        hash.insertar(19);
        hash.insertar(26);
        hash.mostrarTabla();

        System.out.println(" 2. ELIMINACIÓN LÓGICA DE 12 ");
        hash.eliminar(12);
        hash.mostrarTabla();

        System.out.println(" 3. BÚSQUEDA DE 19 POST-ELIMINACIÓN ");
        hash.buscar(19);
        System.out.println();

        System.out.println(" 4. REINSERCIÓN DE 33 ");
        hash.insertar(33);
        hash.mostrarTabla();
    }
}