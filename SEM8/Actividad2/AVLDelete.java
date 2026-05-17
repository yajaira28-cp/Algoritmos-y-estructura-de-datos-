package Actividad2;

public class AVLDelete {

    private NodoAVL raiz;

    private int obtenerAltura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int obtenerFactorEquilibrio(NodoAVL nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    private void actualizarAltura(NodoAVL nodo) {
        if (nodo != null) {
            nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));
        }
    }

    // ROTACIONES DEL ÁRBOL AVL
    private NodoAVL rotarDerecha(NodoAVL x) {
        System.out.println(" Rotación Simple Derecha en nodo: " + x.clave);
        NodoAVL y = x.izquierdo;
        NodoAVL T2 = y.derecho;

        y.derecho = x;
        x.izquierdo = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }
    private NodoAVL rotarIzquierda(NodoAVL x) {
        System.out.println(" Rotación Simple Izquierda en nodo: " + x.clave);
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    //MÉTODO DE INSERCIÓN
    public void insertar(int clave) {
        raiz = insertarRecursivo(raiz, clave);
    }
    private NodoAVL insertarRecursivo(NodoAVL actual, int clave) {
        if (actual == null) return new NodoAVL(clave);

        if (clave < actual.clave) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, clave);
        } else if (clave > actual.clave) {
            actual.derecho = insertarRecursivo(actual.derecho, clave);
        } else {
            return actual;
        }

        actualizarAltura(actual);
        int fe = obtenerFactorEquilibrio(actual);

        if (fe > 1 && clave < actual.izquierdo.clave) return rotarDerecha(actual);
        if (fe < -1 && clave > actual.derecho.clave) return rotarIzquierda(actual);
        
        if (fe > 1 && clave > actual.izquierdo.clave) {
            actual.izquierdo = rotarIzquierda(actual.izquierdo);
            return rotarDerecha(actual);
        }
        if (fe < -1 && clave < actual.derecho.clave) {
            actual.derecho = rotarDerecha(actual.derecho);
            return rotarIzquierda(actual);
        }

        return actual;
    }

    //MÉTODOS DE ELIMINACIÓN
    public void eliminar(int clave)
    {
        System.out.println(" ELIMINANDO CLAVE K = " + clave);
        raiz = eliminarRecursivo(raiz, clave);
    }
    private NodoAVL eliminarRecursivo(NodoAVL actual, int clave) {
        if (actual == null) return null;

        // Búsqueda del nodo a eliminar
        if (clave < actual.clave) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, clave);
        } else if (clave > actual.clave) {
            actual.derecho = eliminarRecursivo(actual.derecho, clave);
        } else {
    
            // Caso 1 u 2: Un solo hijo o ninguno
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }

            // Caso 3: Tiene dos hijos -> Buscar sucesor en inorden
            NodoAVL sucesor = obtenerMinimo(actual.derecho);
            System.out.println("   [Caso 3] Nodo con 2 hijos. Sucesor Inorden encontrado: " + sucesor.clave);
            
            // Copiar el valor del sucesor al nodo actual
            actual.clave = sucesor.clave;
            
            // Eliminar el duplicado del sucesor en el subárbol derecho
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor.clave);
        }

        // Actualizar alturas post-eliminación
        actualizarAltura(actual);

        // Control y Rebalanceo AVL
        int fe = obtenerFactorEquilibrio(actual);

        // Balanceo Caso Izquierda-Izquierda / Izquierda-Derecha
        if (fe > 1) {
            System.out.println(" Desbalance detectado en nodo X = " + actual.clave + " (FE = " + fe + ")");
            if (obtenerFactorEquilibrio(actual.izquierdo) >= 0) {
                return rotarDerecha(actual);
            } else {
                actual.izquierdo = rotarIzquierda(actual.izquierdo);
                return rotarDerecha(actual);
            }
        }
        // Balanceo Caso Derecha-Derecha / Derecha-Izquierda
        if (fe < -1) {
            System.out.println(" Desbalance detectado en nodo X = " + actual.clave + " (FE = " + fe + ")");
            if (obtenerFactorEquilibrio(actual.derecho) <= 0) {
                return rotarIzquierda(actual);
            } else {
                actual.derecho = rotarDerecha(actual.derecho);
                return rotarIzquierda(actual);
            }
        }
        return actual;
    }
    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        NodoAVL aux = nodo;
        while (aux.izquierdo != null) {
            aux = aux.izquierdo;
        }
        return aux;
    }
//Mostrar Árbol
    public void mostrarArbol() {
        System.out.println("\nEstructura actual del Árbol:");
        imprimirEstructura(raiz, 0);
    }

    private void imprimirEstructura(NodoAVL nodo, int nivel) {
        if (nodo == null) return;

        imprimirEstructura(nodo.derecho, nivel + 1);

        if (nivel != 0) {
            for (int i = 0; i < nivel - 1; i++) System.out.print("    ");
            System.out.println("├─── " + nodo.clave + " (h:" + nodo.altura + ")");
        } else {
            System.out.println("RAÍZ: " + nodo.clave + " (h:" + nodo.altura + ")");
        }
        imprimirEstructura(nodo.izquierdo, nivel + 1);
    }
//Método principal
    public static void main(String[] args) {
        AVLDelete arbol = new AVLDelete();

        // 1. Reconstruimos el árbol inicial de la Figura 8.10
        int[] datosIniciales = {33, 20, 45, 12, 26, 41, 56, 6, 15, 24, 35, 44, 48, 59, 17, 38, 46, 53, 65, 50};
        for (int x : datosIniciales) {
            arbol.insertar(x);
        }

        System.out.println(" ÁRBOL INICIAL CONSTRUIDO ");
        arbol.mostrarArbol();

        // 2. Ejecución secuencial de eliminaciones pedidas
        int[] eliminaciones = {12, 33, 46, 59, 45, 56};
        for (int k : eliminaciones) {
            arbol.eliminar(k);
            arbol.mostrarArbol();
        }
    }
}