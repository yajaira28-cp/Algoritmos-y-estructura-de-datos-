package PyPooAct1;

public class Verificador {

    // se sobreponen
    public static boolean sobreponen(Rectangulo A, Rectangulo B) {

        if (A.getEsquina2().getX() > B.getEsquina1().getX() &&
            A.getEsquina1().getX() < B.getEsquina2().getX() &&
            A.getEsquina2().getY() > B.getEsquina1().getY() &&
            A.getEsquina1().getY() < B.getEsquina2().getY()) {

            return true;
        }

        return false;
    }

    // se tocan
    public static boolean seTocan(Rectangulo A, Rectangulo B) {

        if (A.getEsquina2().getX() == B.getEsquina1().getX() ||
            A.getEsquina1().getX() == B.getEsquina2().getX() ||
            A.getEsquina2().getY() == B.getEsquina1().getY() ||
            A.getEsquina1().getY() == B.getEsquina2().getY()) {

            return true;
        }

        return false;
    }

}
