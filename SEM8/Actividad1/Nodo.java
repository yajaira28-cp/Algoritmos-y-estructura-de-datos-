package Actividad1;

class Nodo {
    int clave;
    int altura;
    Nodo izquierdo, derecho;

    public Nodo(int dato) {
        this.clave = dato;
        this.altura = 1; // Todo nodo nuevo empieza con altura 1
    }
}

