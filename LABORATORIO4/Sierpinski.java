package LABORATORIO4;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sierpinski extends JPanel {
    private int nivelDeseado;

    // Constructor para definir el nivel de recursión
    public Sierpinski(int nivel) {
        this.nivelDeseado = nivel;
    }

    // Método recursivo para dibujar los triángulos 
    public void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int nivel) {
        if (nivel == 0) {
            // Caso base: Dibujar el triángulo relleno 
            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            // Avance recursivo: calcular puntos medios 
            int mx12 = (x1 + x2) / 2;
            int my12 = (y1 + y2) / 2;
            int mx23 = (x2 + x3) / 2;
            int my23 = (y2 + y3) / 2;
            int mx31 = (x3 + x1) / 2;
            int my31 = (y3 + y1) / 2;

            // Tres llamadas recursivas para las subdivisiones 
            drawTriangle(g, x1, y1, mx12, my12, mx31, my31, nivel - 1);
            drawTriangle(g, mx12, my12, x2, y2, mx23, my23, nivel - 1);
            drawTriangle(g, mx31, my31, mx23, my23, x3, y3, nivel - 1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Llamada inicial con las coordenadas de la imagen y el nivel elegido 
        drawTriangle(g, 100, 500, 500, 500, 300, 100, nivelDeseado);
    }

    public static void main(String[] args) {
        // Para cumplir la actividad, generamos las ventanas para los niveles 4, 6 y 8 
        int[] nivelesActividad = {4, 6, 8};

        for (int nivel : nivelesActividad) {
            JFrame frame = new JFrame("Triángulo de Sierpinski - Nivel " + nivel);
            Sierpinski panel = new Sierpinski(nivel);
            
            frame.add(panel);
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Centra la ventana
            frame.setVisible(true);
        }
    }
}
