package Actividad1;

public class ArbolAVL {

    private Nodo raiz;

    // Obtener la altura de un nodo de forma segura (evita errores con nodos nulos)
    private int obtenerAltura(Nodo nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    // Calcular el Factor de Equilibrio (FE = altura_izquierda - altura_derecha)
    private int obtenerFactorEquilibrio(Nodo nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    // Actualiza la altura de un nodo basándose en la altura máxima de sus hijos
    private void actualizarAltura(Nodo nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }
    // Rotación Simple a la Derecha (Caso Izquierda - Izquierda)
    private Nodo rotarDerecha(Nodo x) {
        System.out.println(" Aplicando (Rotación Simple Derecha) en nodo desbalanceado: " + x.clave);
        Nodo y = x.izquierdo;
        Nodo T2 = y.derecho;

        // Reestructuración de punteros
        y.derecho = x;
        x.izquierdo = T2;

        // Recalcular alturas afectadas
        actualizarAltura(x);
        actualizarAltura(y);

        return y; // Nueva raíz del subárbol
    }

    // Rotación Simple a la Izquierda (Caso Derecha - Derecha)
    private Nodo rotarIzquierda(Nodo x) {
        System.out.println(" Aplicando (Rotación Simple Izquierda) en nodo desbalanceado: " + x.clave);
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;

        // Reestructuración de punteros
        y.izquierdo = x;
        x.derecho = T2;

        // Recalcular alturas afectadas
        actualizarAltura(x);
        actualizarAltura(y);

        return y; // Nueva raíz del subárbol
    }

    public void insertar(int clave) {
        System.out.println("\nInsertando clave: " + clave);
        raiz = insertarRecursivo(raiz, clave);
    }

    private Nodo insertarRecursivo(Nodo actual, int clave) {
        // 1. Inserción estándar de un Árbol Binario de Búsqueda
        if (actual == null) {
            return new Nodo(clave);
        }

        if (clave < actual.clave) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, clave);
        } else if (clave > actual.clave) {
            actual.derecho = insertarRecursivo(actual.derecho, clave);
        } else {
            return actual; // No se permiten claves duplicadas en este diseño
        }

        // 2. Actualizar la altura del nodo padre actual
        actualizarAltura(actual);

        // 3. Evaluar el Factor de Equilibrio para detectar desbalances
        int fe = obtenerFactorEquilibrio(actual);

        // --- CASO 1: Izquierda - Izquierda (FE > 1 y clave menor al hijo izquierdo)
        if (fe > 1 && clave < actual.izquierdo.clave) {
            System.out.println("Desbalance detectado en nodo " + actual.clave + " (Tipo: Izquierda-Izquierda)");
            return rotarDerecha(actual);
        }

        // --- CASO 2: Derecha - Derecha (FE < -1 y clave mayor al hijo derecho)
        if (fe < -1 && clave > actual.derecho.clave) {
            System.out.println("Desbalance detectado en nodo " + actual.clave + " (Tipo: Derecha-Derecha)");
            return rotarIzquierda(actual);
        }

        // --- CASO 3: Izquierda - Derecha (Rotación Doble Izquierda-Derecha)
        if (fe > 1 && clave > actual.izquierdo.clave) {
            System.out.println("Desbalance detectado en nodo " + actual.clave + " (Tipo: Izquierda-Derecha)");
            System.out.println(" Requiere Rotación Doble. Paso 1: Rotar hijo a la izquierda.");
            actual.izquierdo = rotarIzquierda(actual.izquierdo);
            return rotarDerecha(actual);
        }

        // --- CASO 4: Derecha - Izquierda (Rotación Doble Derecha-Izquierda)
        if (fe < -1 && clave < actual.derecho.clave) {
            System.out.println(" Desbalance detectado en nodo " + actual.clave + " (Tipo: Derecha-Izquierda)");
            System.out.println(" Requiere Rotación Doble. Paso 1: Rotar hijo a la derecha.");
            actual.derecho = rotarDerecha(actual.derecho);
            return rotarIzquierda(actual);
        }

        return actual; // El nodo sigue en equilibrio equilibrado
    }
    
    // Muestra el árbol de forma gráfica en la consola (girado a 90 grados)
    public void mostrarArbol() {
        System.out.println("\nEstructura actual del Árbol AVL:");
        imprimirArbolEstructura(raiz, 0);
    }

    private void imprimirArbolEstructura(Nodo nodo, int nivel) {
        if (nodo == null) return;

        // Primero procesar el lado derecho (quedará arriba en la consola)
        imprimirArbolEstructura(nodo.derecho, nivel + 1);

        // Imprimir el nodo actual con tabulaciones proporcionales a su nivel
        if (nivel != 0) {
            for (int i = 0; i < nivel - 1; i++) System.out.print("    ");
            System.out.println("├─── " + nodo.clave);
        } else {
            System.out.println("RAÍZ: " + nodo.clave);
        }

        // Procesar el lado izquierdo (quedará abajo en la consola)
        imprimirArbolEstructura(nodo.izquierdo, nivel + 1);
    }
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        
        // Claves del ejercicio en el orden exacto solicitado
        int[] claves = {30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75};

        for (int clave : claves) {
            arbol.insertar(clave);
            arbol.mostrarArbol(); // Dibuja el árbol tras cada paso para el seguimiento académico
        }
    }
}