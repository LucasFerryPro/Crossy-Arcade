import Gui.StdDraw;

import java.awt.*;

public class Canevas {

    private int size;
    private Field terrain;

    public Canevas(int size, Field terrain){
        this.size = size;
        this.terrain = terrain;
    }

    public void affichescore(double x, double y,int score){
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(x,y,1.5,0.5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.rectangle(x,y,1.5,0.5);
        StdDraw.text(x,y , ("Score: "+score)); 
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public void affichecoincount(double x, double y,int coinCount, int totalCoins){
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(x,y,1.5,0.5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.rectangle(x,y,1.5,0.5);
        StdDraw.text(x,y , ("Coins: "+coinCount+"/"+totalCoins));
        StdDraw.setPenColor(StdDraw.BLACK);
    }
}
