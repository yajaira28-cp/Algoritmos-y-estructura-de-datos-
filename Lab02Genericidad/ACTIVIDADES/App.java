package JAVA;

public class App {
    public static void main (String[] args) throws Exception{
        //Caja que guarda enteros
        Caja<Integer> cajaNumero = new Caja<Integer>();
        cajaNumero.guardar(10);


        //Caja que guarda texto
        Caja<String> cajaTexto = new Caja<String>();
        cajaTexto.guardar("Hola");

        System.out.println("Numero: " + cajaNumero.obtener());
        System.out.println("Texto: " + cajaTexto.obtener());
    }
    
}
