package PyPooAct1;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ContainerRect contenedor = new ContainerRect(10);

        for (int i = 0; i < 2; i++) {

            System.out.println("Rectangulo " + (i + 1));

            System.out.print("x1: ");
            double x1 = sc.nextDouble();

            System.out.print("y1: ");
            double y1 = sc.nextDouble();

            System.out.print("x2: ");
            double x2 = sc.nextDouble();

            System.out.print("y2: ");
            double y2 = sc.nextDouble();

            Coordenada c1 = new Coordenada(x1, y1);
            Coordenada c2 = new Coordenada(x2, y2);

            Rectangulo r = new Rectangulo(c1, c2);

            contenedor.addRectangulo(r);
        }

        System.out.println("Rectangulo   Coordenadas   Distancia   Area");
        System.out.println(contenedor);

    }

}