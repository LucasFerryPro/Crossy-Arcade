import Gui.StdDraw;
import java.awt.Color;

public class Vroum {
    private final Color c;
    private Road assignedRoad;
    private char cote;
    private double x;
    private double y;
    private double speed;

    public static Color[] colors = new Color[]{
            Color.decode("#CD6155"),
            Color.decode("#922B21"),
            Color.decode("#7D3C98"),
            Color.decode("#9B59B6"),
            Color.decode("#873600"),
            Color.decode("#212F3C"),
            Color.decode("#B7950B"),
            Color.decode("#16A085"),
            Color.decode("#154360"),
            Color.decode("#D2B4DE"),
            Color.decode("#D4EFDF"),
            Color.decode("#D4EFDF"),
            Color.decode("#E6B0AA"),
    };
    public static Color vitre = Color.decode("#D4EFDF");

    public Vroum(Road assignedRoad, char cote){
        this.assignedRoad = assignedRoad;
        this.cote = cote;
        this.y = assignedRoad.getY();
        this.speed = assignedRoad.getSpeed();
        if (cote == 'g'){
            this.x = -2.5 - Math.random()*2;
        }else{
            this.x = assignedRoad.getLongueur()+1.5 + Math.random()*2;
        }

        this.c = colors[(int)(Math.random()*(colors.length-1))];
    }

    public Road getAssignedRoad() {
        return assignedRoad;
    }
    public char getCote() {
        return cote;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getSpeed() {
        return speed;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void move(){
        if (cote == 'g')
            this.x += this.speed;
        else 
            this.x -= this.speed;
    }

    public void afficheVroum(){
        if (x < assignedRoad.getLongueur()+2 || x > -2){
            StdDraw.setPenColor(c);
            StdDraw.filledRectangle(x, y, 1, 0.4);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.rectangle(x, y, 1, 0.4);
        }
    }
}