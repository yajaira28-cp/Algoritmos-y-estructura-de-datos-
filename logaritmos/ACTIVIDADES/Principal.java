package PyPooAct1;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Rectangulo A
        System.out.println("Rectangulo A");

        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();

        Rectangulo A = new Rectangulo(new Coordenada(x1,y1), new Coordenada(x2,y2));


        // Rectangulo B
        System.out.println("Rectangulo B");

        double x3 = sc.nextDouble();
        double y3 = sc.nextDouble();
        double x4 = sc.nextDouble();
        double y4 = sc.nextDouble();

        Rectangulo B = new Rectangulo(new Coordenada(x3,y3), new Coordenada(x4,y4));


        // Mostrar rectangulos
        System.out.println("Rectangulo A: " + A);
        System.out.println("Rectangulo B: " + B);


        // Verificar casos
        if(Verificador.sobreponen(A,B)){

            System.out.println("Caso 1: Se sobreponen");

            double base = A.getEsquina2().getX() - B.getEsquina1().getX();
            double altura = A.getEsquina2().getY() - B.getEsquina1().getY();

            double area = base * altura;

            System.out.println("Area de sobreposicion: " + area);

        }
        else if(Verificador.seTocan(A,B)){

            System.out.println("Caso 2: Se tocan");

        }
        else{

            System.out.println("Caso 3: Son disjuntos");

        }
    }
}