package PyPooAct1;

public class Coordenada {
    private double x;
    private double y;

    public Coordenada( )
    {
        this.x = 0;
        this.y = 0;
    }
    
    public Coordenada(double x, double y )
    {
        this.x = x;
        this.y = y;
        
    }

    public Coordenada(Coordenada c )
    {
        this.x = c.x;
        this.y = c.y;
    }

    void setX(double x) { 
        this.x = x;
    }
    void setY(double y){
        this.y = y;
    }
    
    //métodos getter
    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    private double raiz(double num) {
        if (num < 0) return -1; 
        if (num == 0) return 0;

        double estimacion = num / 2;
        double anterior;
        
        do {
            anterior = estimacion;
            estimacion = (anterior + (num / anterior)) / 2;
        } while ((anterior - estimacion) > 0.000001 || (estimacion - anterior) > 0.000001);

        return estimacion;
    }

    public double distancia(Coordenada c) 
    {
        double dx = this.x - c.x;
        double dy = this.y - c.y;

        return raiz((dx * dx) + (dy * dy));
    }

    public static double distancia(Coordenada c1, Coordenada c2)
    {
        double dx = c1.x - c2.x;
        double dy = c1.y - c2.y;
        
        // Creamos una instancia temporal para usar el método de raíz
        // o llamamos a una lógica similar
        double sumaCuadrados = (dx * dx) + (dy * dy);
        
        double n = sumaCuadrados / 2;
        if (sumaCuadrados == 0) return 0;
        for (int i = 0; i < 20; i++) 
        { 
            n = (n + (sumaCuadrados / n)) / 2;
        }
        return n;
    }
    //método que devuelve los valores de una coordenada 
    public String toString()
    {
        return "[" + x + "." + y + "]";
        
    }
}
