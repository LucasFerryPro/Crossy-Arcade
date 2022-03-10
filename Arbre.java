import GUILibrary.StdDraw;
import java.awt.Color;

public class Arbre {
    private int x;
    private int y;
    private Color c;

    public Arbre(int x, int y){
        this.x = x;
        this.y = y;
        this.c = new Color(1, 50, 32);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return c;
    }

    public void afficheArbre(){
        
        StdDraw.setPenColor(c);
        StdDraw.filledCircle(x, y, 0.45);
        StdDraw.setPenColor(StdDraw.BLACK);
    }
}
