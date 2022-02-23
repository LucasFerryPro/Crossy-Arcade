import GUILibrary.StdDraw;

public class Road {
    private double y;
    private int longueur;
    private char cote;
    private double speed;
    public Road(double y, int longueur, char cote, double speed){
        this.y=y;
        this.longueur=longueur;
        this.cote = cote;
        this.speed = speed;
    }

    public Road(double y, int longueur, char cote){
        this(y,longueur,cote,0.8);
    }

    public double getY() {
        return y;
    }

    public int getLongueur() {
        return longueur;
    }

    public char getCote() {
        return cote;
    }

    public double getSpeed() {
        return speed;
    }

    public void afficheRoad(){
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(longueur/2-0.5, y, longueur/2, 0.5);
        StdDraw.setPenColor(StdDraw.YELLOW);
        //faire les lignes
        StdDraw.setPenColor(StdDraw.BLACK);

    }

}
