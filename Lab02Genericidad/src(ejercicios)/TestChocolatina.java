package Lab02Genericidad.src;

public class TestChocolatina {
    public static void main(String[] args) {

        Cajoneria<Chocolatina> cajoneria = new Cajoneria<>();

        cajoneria.addCaja(new Caja<>("Rojo", new Chocolatina("Milka")));
        cajoneria.addCaja(new Caja<>("Azul", new Chocolatina("Ferrero")));
        cajoneria.addCaja(new Caja<>("Verde", new Chocolatina("Milka")));

        System.out.println("Contenido:");
        System.out.println(cajoneria);

        Chocolatina buscada = new Chocolatina("Milka");

        System.out.println("Buscar: " + cajoneria.search(buscada));
        System.out.println("Ocurrencias: " + cajoneria.countOccurrences(buscada));
    }
}