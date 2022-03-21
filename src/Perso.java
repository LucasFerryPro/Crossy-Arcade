// import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Gui.StdDraw;

public class Perso {
    private int x;
    private int y;
    private int rotation;
    private double size;
    private int score;
    private boolean canMove;
    private int coinCount;
    private String name;
    private Field f;
    public List<Integer> alphabet = new ArrayList<>();

    private static final Scanner input = new Scanner(System.in);

    public Perso(int x, int y, double size, int rotation, Field f) {
        this .x = x;
        this.y = y;
        this.size = size;
        this.rotation = rotation;
        this.canMove = false;
        this.score = 0;
        this.coinCount = 0;
        this.name = "";
        for (int i = 65; i<=90; i++)
            this.alphabet.add(i);
        this.alphabet.add(32);
        this.f = f;
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

    public String getName() {
        // System.out.println("Quel est le joueur? (pas de ' ', de ':' ni de '/')");
        // this.name = input.next();
        StdDraw.clear();
        StdDraw.filledSquare((f.getSize()/2)-0.5, (f.getSize()/2)-0.5,  f.getSize()/2);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text((f.getSize()/2)-0.5, f.getSize()/2, "VEUILLEZ ENTRER VOTRE PSEUDO");
        StdDraw.text(f.getSize()/2-0.5, f.getSize()/2-1.5, name);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.show();
        StdDraw.pause(300);

        boolean isTyping = true;

        while(isTyping){
            boolean clickbtn = true;
            //verif appuie
            for(int lettre : alphabet){
                if (StdDraw.isKeyPressed(lettre) && clickbtn){
                    this.name += (char)lettre;
                    do {
                        clickbtn = false;
                    }while (StdDraw.isKeyPressed(lettre));
                    clickbtn = true;
                }
            }
            if (StdDraw.isKeyPressed(8) && clickbtn && this.name.length()>0){
                this.name = this.name.substring(0, this.name.length()-1);
                do {
                    clickbtn = false;
                }while (StdDraw.isKeyPressed(8));
                clickbtn = true;
            }

            StdDraw.clear();
            StdDraw.filledSquare((f.getSize()/2)-0.5, (f.getSize()/2)-0.5,  f.getSize()/2);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text((f.getSize()/2)-0.5, f.getSize()/2, "VEUILLEZ ENTRER VOTRE PSEUDO");

            StdDraw.text(f.getSize()/2-0.5, f.getSize()/2-1.5, name);
            StdDraw.setPenColor(StdDraw.BLACK);

            StdDraw.show();

            if(StdDraw.isKeyPressed(10))
                isTyping = false;
        }

        return this.name;
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

    public void respawn(int x, int y){
        setX(x);
        setY(y);
    }
}
