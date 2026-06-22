package Ejercicio6;

import java.util.Objects;

//Representa la sesión del usuario
class Session {
    String token;
    String username;
    String role;
    long expiresAt;
    Session siguiente; // Puntero para el encadenamiento (Lista enlazada)

    public Session(String token, String username, String role, long ttlMs) {
        this.token = token;
        this.username = username;
        this.role = role;
        // Tiempo actual del sistema
        this.expiresAt = System.currentTimeMillis() + ttlMs;
        this.siguiente = null;
    }

    public boolean estaExpirada() {
        return System.currentTimeMillis() > this.expiresAt;
    }

    @Override
    public String toString() {
        return String.format("[%s] Usuario: %s (%s) - Expira en: %d ms",
                token, username, role, (expiresAt - System.currentTimeMillis()));
    }
}

// Caché de sesiones con Tabla Hash Abierta
class SessionCache {
    private static final int CAPACIDAD = 11; // Tamaño fijo de la tabla
    private Session[] tabla;

    public SessionCache() {
        this.tabla = new Session[CAPACIDAD]; // Inicializa los baldes en null por defecto
    }

    // Función Hash basada en el hashCode de Java adaptada al tamaño de nuestra
    // tabla
    private int funcionHash(String token) {
        return Math.abs(token.hashCode()) % CAPACIDAD;
    }

    // Iniciar sesión
    public void login(String token, String username, String role, long ttlMs) {
        int indice = funcionHash(token);
        Session nuevaSesion = new Session(token, username, role, ttlMs);

        // Si el balde está vacío, se asigna como cabeza de lista
        if (tabla[indice] == null) {
            tabla[indice] = nuevaSesion;
        } else {
            // Si hay colisión, recorremos hasta el final e insertamos
            Session actual = tabla[indice];
            while (actual.siguiente != null) {
                // Si el token ya existía de un login previo, actualizamos sus datos
                if (actual.token.equals(token)) {
                    actual.username = username;
                    actual.role = role;
                    actual.expiresAt = nuevaSesion.expiresAt;
                    return;
                }
                actual = actual.siguiente;
            }
            // Validación para el último nodo de la lista enlazada
            if (actual.token.equals(token)) {
                actual.username = username;
                actual.role = role;
                actual.expiresAt = nuevaSesion.expiresAt;
            } else {
                actual.siguiente = nuevaSesion; // Encadenamiento
            }
        }
        System.out.println(" Sesión creada exitosamente para: " + username + " (Balde " + indice + ")");
    }

    // Validar sesión (Buscar y comprobar expiración)
    public Session validate(String token) {
        int indice = funcionHash(token);
        Session actual = tabla[indice];

        while (actual != null) {
            if (actual.token.equals(token)) {
                if (actual.estaExpirada()) {
                    System.out.println(" [ALERTA] El token " + token + " ha expirado.");
                    return null;
                }
                return actual; // Token válido y activo
            }
            actual = actual.siguiente;
        }
        return null; // Token no encontrado
    }

    // Cierre de sesión explícito (Eliminar un nodo específico)
    public void logout(String token) {
        int indice = funcionHash(token);
        Session actual = tabla[indice];
        Session anterior = null;

        while (actual != null) {
            if (actual.token.equals(token)) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente; // Era el primer elemento de la lista
                } else {
                    anterior.siguiente = actual.siguiente; // Desvincular el nodo intermedio/final
                }
                System.out.println(" [LOGOUT] Sesión del token " + token + " destruida explícitamente.");
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    // Limpieza pasiva de memoria
    public void cleanExpired() {
        int eliminados = 0;

        for (int i = 0; i < CAPACIDAD; i++) {
            Session actual = tabla[i];
            Session anterior = null;

            while (actual != null) {
                if (actual.estaExpirada()) {
                    eliminados++;
                    if (anterior == null) {
                        tabla[i] = actual.siguiente; // Eliminar cabeza
                        actual = tabla[i]; // Avanzar usando la nueva cabeza
                    } else {
                        anterior.siguiente = actual.siguiente; // Eliminar nodo interno
                        actual = actual.siguiente; // Avanzar al siguiente
                    }
                } else {
                    anterior = actual;
                    actual = actual.siguiente;
                }
            }
        }
        System.out.println(" [CLEANUP] Limpieza completada. Se eliminaron " + eliminados + " sesiones expiradas.");
    }

    // Imprime el estado visual del caché
    public void imprimirCache() {
        System.out.println("\n ESTADO ACTUAL DEL CACHÉ DE SESIONES ");
        int activas = 0;
        for (int i = 0; i < CAPACIDAD; i++) {
            Session actual = tabla[i];
            if (actual != null) {
                System.out.print("Balde [" + i + "]: ");
                while (actual != null) {
                    System.out.print(actual + " -> ");
                    activas++;
                    actual = actual.siguiente;
                }
                System.out.println("null");
            }
        }
        System.out.println("Total de sesiones en memoria: " + activas + "\n");
    }
}
