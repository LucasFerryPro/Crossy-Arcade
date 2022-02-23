import GUILibrary.StdDraw;

public class Arbre {
    private int x;
    private int y;

    public Arbre(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void afficheArbre(){
        StdDraw.picture(x, y, "images/arbre.png", 4, 4);
    }
}
