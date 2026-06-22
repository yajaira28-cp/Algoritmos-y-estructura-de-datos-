package Ejercicio6;

public class TestSessionCache {
    public static void main(String[] args) {
        SessionCache cache = new SessionCache();

        System.out.println(" LOGIN DE USUARIOS ");
        // Juan inicia sesión con una vida útil de 1.5 segundos
        cache.login("abc123", "Juan", "ADMIN", 1500);
        // Ana inicia sesión con una vida útil de 5 segundos
        cache.login("xyz789", "Ana", "USER", 5000);
        // Luis inicia sesión con una vida útil de 6 segundos
        cache.login("bcp456", "Luis", "MANAGER", 6000);

        cache.imprimirCache();

        System.out.println(" SIMULANDO TIEMPO Y VALIDANDO TOKENS ");
        System.out.println("... Esperando 2 segundos (Simulación de navegación del usuario) ...");
        try {
            Thread.sleep(2000); // Pausar el programa 2 segundos para forzar la expiración de Juan
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Validar Token de Juan (Debe fallar por tiempo)
        System.out.print("Validando Juan (abc123): ");
        Session s1 = cache.validate("abc123");
        if (s1 != null)
            System.out.println("VÁLIDA. Bienvenido " + s1.username);

        // Validar Token de Ana (Debe ser exitoso)
        System.out.print("Validando Ana (xyz789): ");
        Session s2 = cache.validate("xyz789");
        if (s2 != null)
            System.out.println("VÁLIDA. Bienvenido " + s2.username);
        System.out.println();

        System.out.println(" CIERRE DE SESIÓN EXPLÍCITO ");
        // Ana decide cerrar sesión de forma manual en la app
        cache.logout("xyz789");
        cache.imprimirCache();

        System.out.println(" RECOLECCIÓN Y LIMPIEZA DE EXPIRADOS ");
        // El servidor corre su rutina automática para liberar la sesión muerta de Juan
        cache.cleanExpired();

        // Mostrar el estado definitivo
        cache.imprimirCache();
    }
}