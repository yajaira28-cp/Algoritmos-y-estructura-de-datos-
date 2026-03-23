package Lab02Genericidad.src;

public class TestGen {
    public static void main(String[] args) {

        Cajoneria<Golosina> cajoneria = new Cajoneria<>();
        cajoneria.addCaja(new Caja<>("Rojo", new Golosina("Chicle", 10)));
        cajoneria.addCaja(new Caja<>("Azul", new Golosina("Caramelo", 5)));
        cajoneria.addCaja(new Caja<>("Verde", new Golosina("Chocolate", 20)));
        cajoneria.addCaja(new Caja<>("Amarillo", new Golosina("Galleta", 15)));
        cajoneria.addCaja(new Caja<>("Negro", new Golosina("Dulce", 8)));

        System.out.println("CONTENIDO INICIAL ");
        System.out.println(cajoneria);

        Golosina buscada = new Golosina("Chocolate", 20);
        System.out.println("Buscar: " + cajoneria.search(buscada));

        Golosina eliminada = cajoneria.delete(buscada);

        if (eliminada != null) {
            System.out.println("Elemento eliminado: " + eliminada);
        } else {
            System.out.println("No se encontró el elemento a eliminar");
        }
        System.out.println(" DESPUÉS DE ELIMINAR ");
        System.out.println(cajoneria);
    }
}
