package Actividad3;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String mensaje) {
        super(mensaje); // Pasa el mensaje de error al padre
    }
}