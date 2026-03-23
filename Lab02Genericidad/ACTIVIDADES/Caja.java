package JAVA;

class Caja <T> {
    
    private T dato;

    public void guardar(T dato){
        this.dato = dato;
    }

    public T obtener(){
        return dato;
    }
}