package Ejercicios.Ejercicio2;

class ColaArreglo {
    private int[] cola;
    private int front;
    private int rear;
    private int size;

    public ColaArreglo(int capacidad) {
        cola = new int[capacidad];
        front = 0;
        rear = -1;
        size = 0;
    }

    // insertar
    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        rear = (rear + 1) % cola.length;
        cola[rear] = x;
        size++;
    }

    //eliminar
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        int dato = cola[front];
        front = (front + 1) % cola.length;
        size--;
        return dato;
    }

    // ver frente
    public int front() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        return cola[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == cola.length;
    }
}
