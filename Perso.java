// import java.awt.Color;

import org.w3c.dom.Text;

import GUILibrary.StdDraw;

public class Perso {
    private int x;
    private int y;
    private int rotation;
    private double size;
    private int score;
    private boolean canMove;
    private int coinCount;

    public Perso(int x, int y, double size, int rotation) {
        this .x = x;
        this.y = y;
        this.size = size;
        this.rotation = rotation;
        this.canMove = false;
        this.score = 0;
        this.coinCount = 0;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public double getSize() {
        return size;
    }
    public int getRotation() {
        return rotation;
    }
    public int getScore() {
        return score;
    }
    public int getCoinCount() {
        return coinCount;
    }
    public boolean getCanMove(){
        return this.canMove;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setSize(double size) {
        this.size = size;
    }
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void affichePerso(){
        StdDraw.picture(x, y, "images/perso.png", 1, 1, rotation);
    }

    public void coinUp(int coins){
        this.coinCount+=coins;
    }

    public void coinDown(int coins){
        this.coinCount-=coins;
    }

    public void affichescore(double x, double y){
        StdDraw.text(x,y , ("Score: "+this.score));
    }

    public void affichecoincount(double x, double y){
        StdDraw.text(x,y , ("Coins: "+this.coinCount));
    }

    public void respawn(int x, int y){
        setX(x);
        setY(y);
    }
}
