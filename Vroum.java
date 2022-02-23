import GUILibrary.StdDraw;

public class Vroum {
    private Road assignedRoad;
    private char cote;
    private double x;
    private double y;
    private double speed;

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
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(x, y, 1, 0.4);
        }
    }
}