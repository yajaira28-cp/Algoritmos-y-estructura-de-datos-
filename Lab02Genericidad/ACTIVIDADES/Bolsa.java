package JAVA;

import java.util.ArrayList;
import java.util.Iterator;

public class Bolsa <T> implements Iterable <T> {
    private ArrayList <T> lista = new ArrayList<T>();
    private int tope;

    public Bolsa (int tope){
        super();
        this.tope = tope;
    }

    public void add(T objeto){
        if (lista.size() >= tope){
            lista.add(objeto);
        }else{
            throw new RuntimeException(" No caben más");

        }
    }

    public Iterator <T> iterato() {
        return lista.iterator();
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
}