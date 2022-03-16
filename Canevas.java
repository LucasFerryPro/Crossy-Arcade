import GUILibrary.StdDraw;

public class Canevas {

    private int size;
    private Field terrain;

    public Canevas(int size, Field terrain){
        this.size = size;
        this.terrain = terrain;
    }

    public void affichescore(double x, double y,int score){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x,y , ("Score: "+score)); 
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public void affichecoincount(double x, double y,int coinCount, int totalCoins){
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x,y , ("Coins: "+coinCount+"/"+totalCoins));
        StdDraw.setPenColor(StdDraw.BLACK);
    }
}
