import Gui.StdDraw;

public class Coin {


    private double x;
    private double y;

    public Coin(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void afficheCoin(){
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(x, y, 0.2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x, y, 0.2);
        StdDraw.setPenColor(StdDraw.BLACK);
    }
    
}
