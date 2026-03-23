package Lab02Genericidad.src;

public class Caja<T> {
    private String color;
    private T contenido;

    public Caja(String color, T contenido) {
        this.color = color;
        this.contenido = contenido;
    }

    public String getColor() {
        return color;
    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return contenido.toString();
    }
}
