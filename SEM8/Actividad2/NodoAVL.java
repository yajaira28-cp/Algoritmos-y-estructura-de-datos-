package Actividad2;

class NodoAVL {
    int clave;
    int altura;
    NodoAVL izquierdo, derecho;

    public NodoAVL(int dato) {
        this.clave = dato;
        this.altura = 1;
    }
}