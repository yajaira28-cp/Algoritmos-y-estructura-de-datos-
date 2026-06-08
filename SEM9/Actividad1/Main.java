package Actividad1;

public class Main {
    public static void main(String[] args) {
        ArbolB arbol = new ArbolB();

        int[] clavesInsertar = { 50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5 };
        System.out.println(" INSERCION PASO A PASO EN EL ARBOL B\n");

        for (int i = 0; i < clavesInsertar.length; i++) {
            System.out.println("Insertando la clave: " + clavesInsertar[i]);
            arbol.insertar(clavesInsertar[i]);
            System.out.println("\nEstado del arbol:");
            arbol.imprimirArbol();
            System.out.println();
        }
        int[] clavesEliminar = { 25, 10, 50, 70, 27, 5, 75 };
        System.out.println(" ELIMINACION CLAVES DEL ARBOL B\n");

        for (int i = 0; i < clavesEliminar.length; i++) {
            System.out.println("Eliminando la clave: " + clavesEliminar[i]);
            arbol.eliminar(clavesEliminar[i]);
            System.out.println("\nEstado del arbol:");
            arbol.imprimirArbol();
            System.out.println();
        }
    }
}

class Nodo {
    int[] claves;
    Nodo[] hijos;
    int cantidad;
    boolean esHoja;

    public Nodo(boolean esHoja) {
        this.claves = new int[4];
        this.hijos = new Nodo[5];
        this.cantidad = 0;
        this.esHoja = esHoja;
    }
}

class ArbolB {
    private Nodo raiz;

    public ArbolB() {
        this.raiz = new Nodo(true);
    }

    public void insertar(int clave) {
        Nodo r = raiz;
        if (r.cantidad == 3) { // Si la raiz esta llena, se divide inmediatamente
            Nodo s = new Nodo(false);
            raiz = s;
            s.hijos[0] = r;

            System.out.println(" La RAIZ se ha llenado.");
            dividirHijo(s, 0, r);

            insertarEnNodoNoLleno(s, clave);
        } else {
            insertarEnNodoNoLleno(r, clave);
        }
    }

    private void insertarEnNodoNoLleno(Nodo x, int clave) {
        int i = x.cantidad - 1;

        if (x.esHoja) {
            while (i >= 0 && clave < x.claves[i]) {
                x.claves[i + 1] = x.claves[i];
                i--;
            }
            x.claves[i + 1] = clave;
            x.cantidad++;
        } else {
            while (i >= 0 && clave < x.claves[i]) {
                i--;
            }
            i++;
            if (x.hijos[i].cantidad == 3) { // Si el hijo destino esta lleno, se divide preventivamente
                System.out.println(" Nodo hijo en la posicion [" + i + "] esta lleno. Dividiendo");
                dividirHijo(x, i, x.hijos[i]);
                if (clave > x.claves[i]) {
                    i++;
                }
            }
            insertarEnNodoNoLleno(x.hijos[i], clave);
        }
    }

    private void dividirHijo(Nodo padre, int i, Nodo hijoLleno) {
        Nodo nuevoHermano = new Nodo(hijoLleno.esHoja);
        nuevoHermano.claves[0] = hijoLleno.claves[2];
        nuevoHermano.cantidad = 1;

        if (!hijoLleno.esHoja) {
            nuevoHermano.hijos[0] = hijoLleno.hijos[2];
            nuevoHermano.hijos[1] = hijoLleno.hijos[3];
        }

        hijoLleno.cantidad = 1; // El original se queda solo con la primera posicion

        for (int j = padre.cantidad; j >= i + 1; j--) {
            padre.hijos[j + 1] = padre.hijos[j];
        }
        padre.hijos[i + 1] = nuevoHermano;

        for (int j = padre.cantidad - 1; j >= i; j--) {
            padre.claves[j + 1] = padre.claves[j];
        }
        padre.claves[i] = hijoLleno.claves[1]; // Sube la mediana
        padre.cantidad++;
    }

    public void eliminar(int clave) {
        eliminarRegistro(raiz, clave);
        if (raiz.cantidad == 0 && !raiz.esHoja) {
            raiz = raiz.hijos[0]; // Si la raiz quedo vacia, el hijo unico sube a ser raiz
        }
    }

    private void eliminarRegistro(Nodo x, int clave) {
        int idx = 0;
        while (idx < x.cantidad && x.claves[idx] < clave) {
            idx++;
        }

        // La clave esta presente en este nodo
        if (idx < x.cantidad && x.claves[idx] == clave) {
            if (x.esHoja) {
                // Es una hoja, la removemos directamente corriendo los elementos
                for (int i = idx + 1; i < x.cantidad; i++) {
                    x.claves[i - 1] = x.claves[i];
                }
                x.cantidad--;
            } else {
                // Es un nodo interno, se reemplaza por el sucesor
                Nodo sucesor = x.hijos[idx + 1];
                while (!sucesor.esHoja) {
                    sucesor = sucesor.hijos[0];
                }
                int claveSucesor = sucesor.claves[0];
                x.claves[idx] = claveSucesor;
                eliminarRegistro(x.hijos[idx + 1], claveSucesor);
            }
        } else {
            // SI la clave no esta en este nodo, debemos descender
            if (x.esHoja) {
                System.out.println("La clave " + clave + " no existe en el arbol.");
                return;
            }

            boolean esUltimoHijo = (idx == x.cantidad);
            Nodo hijoObjetivo = x.hijos[idx];

            // Si el hijo objetivo tiene menos del minimo (menos de 1 clave en Orden 4)
            if (hijoObjetivo.cantidad < 1) {
                System.out.println(" Subdesbordamiento");
                reestructurar(x, idx);
                if (esUltimoHijo && idx > x.cantidad) {
                    hijoObjetivo = x.hijos[idx - 1];
                } else {
                    hijoObjetivo = x.hijos[idx];
                }
            }
            eliminarRegistro(hijoObjetivo, clave);
        }
    }

    // Controla la Redistribucion o la Fusion
    private void reestructurar(Nodo padre, int idx) {
        // Intentar prestar del hermano izquierdo
        if (idx > 0 && padre.hijos[idx - 1].cantidad > 1) {
            System.out.println(" Tomando prestada clave del hermano izquierdo.");
            Nodo hijo = padre.hijos[idx];
            Nodo hermanoIzq = padre.hijos[idx - 1];

            for (int i = hijo.cantidad - 1; i >= 0; i--) {
                hijo.claves[i + 1] = hijo.claves[i];
            }
            if (!hijo.esHoja) {
                for (int i = hijo.cantidad; i >= 0; i--) {
                    hijo.hijos[i + 1] = hijo.hijos[i];
                }
                hijo.hijos[0] = hermanoIzq.hijos[hermanoIzq.cantidad];
            }

            hijo.claves[0] = padre.claves[idx - 1];
            padre.claves[idx - 1] = hermanoIzq.claves[hermanoIzq.cantidad - 1];
            hijo.cantidad++;
            hermanoIzq.cantidad--;
        }
        // Intentar prestar del hermano derecho
        else if (idx < padre.cantidad && padre.hijos[idx + 1].cantidad > 1) {
            System.out.println(" Tomando prestada clave del hermano derecho.");
            Nodo hijo = padre.hijos[idx];
            Nodo hermanoDer = padre.hijos[idx + 1];

            hijo.claves[hijo.cantidad] = padre.claves[idx];
            padre.claves[idx] = hermanoDer.claves[0];

            if (!hijo.esHoja) {
                hijo.hijos[hijo.cantidad + 1] = hermanoDer.hijos[0];
            }

            for (int i = 1; i < hermanoDer.cantidad; i++) {
                hermanoDer.claves[i - 1] = hermanoDer.claves[i];
            }
            if (!hermanoDer.esHoja) {
                for (int i = 1; i <= hermanoDer.cantidad; i++) {
                    hermanoDer.hijos[i - 1] = hermanoDer.hijos[i];
                }
            }
            hijo.cantidad++;
            hermanoDer.cantidad--;
        }
        // Si ningun hermano puede prestar, se aplica FUSION
        else {
            System.out.println(" Uniendo nodos hermanos.");
            if (idx < padre.cantidad) {
                fusionarNodos(padre, idx);
            } else {
                fusionarNodos(padre, idx - 1);
            }
        }
    }

    private void fusionarNodos(Nodo padre, int idx) {
        Nodo hijoIzq = padre.hijos[idx];
        Nodo hermanoDer = padre.hijos[idx + 1];

        hijoIzq.claves[hijoIzq.cantidad] = padre.claves[idx];

        for (int i = 0; i < hermanoDer.cantidad; i++) {
            hijoIzq.claves[hijoIzq.cantidad + 1 + i] = hermanoDer.claves[i];
        }
        if (!hijoIzq.esHoja) {
            for (int i = 0; i <= hermanoDer.cantidad; i++) {
                hijoIzq.hijos[hijoIzq.cantidad + 1 + i] = hermanoDer.hijos[i];
            }
        }
        hijoIzq.cantidad += 1 + hermanoDer.cantidad;

        for (int i = idx + 1; i < padre.cantidad; i++) {
            padre.claves[i - 1] = padre.claves[i];
        }
        for (int i = idx + 2; i <= padre.cantidad; i++) {
            padre.hijos[i - 1] = padre.hijos[i];
        }
        padre.cantidad--;
    }

    public void imprimirArbol() {
        imprimirNodoTextoPlano(raiz, "");
    }

    private void imprimirNodoTextoPlano(Nodo nodo, String sangria) {
        if (nodo != null) {
            System.out.print(sangria + "   [");
            for (int i = 0; i < nodo.cantidad; i++) {
                System.out.print(nodo.claves[i]);
                if (i < nodo.cantidad - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println("]");

            if (!nodo.esHoja) {
                for (int i = 0; i <= nodo.cantidad; i++) {
                    imprimirNodoTextoPlano(nodo.hijos[i], sangria + "     ");
                }
            }
        }
    }
}