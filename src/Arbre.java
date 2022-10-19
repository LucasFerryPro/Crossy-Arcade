import Gui.StdDraw;

import java.awt.Color;

public class Arbre {
    private int x;
    private int y;
    private Color c;
    private double size;

    public static Color[] colors = {
        Color.decode("#96CC39"),
        Color.decode("#6BA32D"),
        Color.decode("#547A1D"),
        Color.decode("#3B5C0A")

    };

    public Arbre(int x, int y){
        this.x = x;
        this.y = y;
        this.c = colors[(int)(Math.random()*(colors.length-1))];
        this.size = 0.35 + (Math.random() * (0.50 - 0.35));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void afficheArbre(){
        
        StdDraw.setPenColor(c);
        StdDraw.filledCircle(x, y, size);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x, y, size);
        StdDraw.setPenColor(StdDraw.BLACK);
    }
}
