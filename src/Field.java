/**
 * Field
 */
import Gui.StdDraw;

import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// import java.awt.Color;
import java.util.Arrays;

import static java.awt.event.KeyEvent.*;

public class Field{

    
    public static int compteurVroum = 50;

    private boolean canKey;
    private int size;
    private Perso p;
    private List<Arbre> arbres;
    private List<Road> roads;
    private List<Vroum> vroums;
    private List<Coin> coins;
    private List<Score> scores;
    private double difficulte; 
    private int totalCoin;
    private int veryTotalCoin;

    public Field(int size){
        // Gui.StdDraw.setCanvasSize(700, 700);
        StdDraw.setScale(-0.5, size-0.5);
        
        StdDraw.enableDoubleBuffering();
        this.size = size;
        this.arbres = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.vroums = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.scores = new ArrayList<>();
        this.canKey = true;

        this.difficulte = 4;

        p = new Perso(size/2, 0, 0.5, 90,this);
    }

    public void addArbre(int x, int y){
        arbres.add(new Arbre(x,y));
    }

    public Road addRoad(int y,char cote){
        double max = difficulte/10/3;
        double min = (difficulte/10/3)-0.1;
        double speed = min + (Math.random()*(max-min));
        Road r = new Road(y,size,cote,speed);
        roads.add(r);
        return r;
    }

    // public River addRiver(int y,char cote){
    //     River r = new River(y,cote);
    // }

    public Vroum addVroum(Road road, char cote){
        Vroum v = new Vroum(road,cote);
        vroums.add(v);
        return v;
    }

    public void movePerso(Perso p, int d){

        int x;
        int y;
        if(p.getCanMove()){

            //up
            if((StdDraw.isKeyPressed(90)||StdDraw.isKeyPressed(VK_UP))&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(180);
                if(p.getCoinCount()<totalCoin){
                    if(y<=size-2 && !isCollision(x, y+1, arbres))
                        p.setY(y+d);
                    drawAll();
                }else{
                    if(y<=size-1 && !isCollision(x, y+1, arbres))
                        p.setY(y+d);
                    drawAll();
                }
            }else check();

            //left
            if((StdDraw.isKeyPressed(81)||StdDraw.isKeyPressed(VK_LEFT))&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(270);
                if(x>0 && !isCollision(x-1, y, arbres))
                    p.setX(x-d);
                drawAll();
            }else check();

            //down
            if((StdDraw.isKeyPressed(83)||StdDraw.isKeyPressed(VK_DOWN))&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(0);
                if(y>0 && !isCollision(x, y-1, arbres))
                    p.setY(y-d);
                drawAll();
            }else check();

            //right
            if((StdDraw.isKeyPressed(68)||StdDraw.isKeyPressed(VK_RIGHT))&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(90);
                if(x<=size-2 && !isCollision(x+1, y, arbres))
                    p.setX(x+d);
                drawAll();
            }else check();
        }

        pickCoin();

        if (p.getY()>=size) {
            newLevel();
        }
    }

    private void check() {
        if(!StdDraw.isKeyPressed(VK_UP)&&!StdDraw.isKeyPressed(VK_DOWN)&&!StdDraw.isKeyPressed(VK_RIGHT)&&!StdDraw.isKeyPressed(VK_LEFT)&&!StdDraw.isKeyPressed(90)&&!StdDraw.isKeyPressed(81)&&!StdDraw.isKeyPressed(83)&&!StdDraw.isKeyPressed(68)&&!canKey){
            canKey = true;
        }
    }

    public boolean isCollision(int x, int y, List<Arbre> arbres){

        for (Arbre arbre : arbres)
            if (arbre.getX() == x && arbre.getY() == y)
                return true;

        return false;
    }

    public void moveVroums(){
        List<Vroum> supprVroums = new ArrayList<Vroum>();
        for (Vroum vroum : vroums){
            vroum.move();
            if(vroum.getX()>size+5 || vroum.getX()<-5)
                supprVroums.add(vroum);
        }
        for (Vroum supprVroum :supprVroums) {
            vroums.remove(supprVroum);
        }

    }

    public void pickCoin(){
        Coin supprCoins = null;

        for (Coin coin : this.coins){
            if (coin.getX()==p.getX() && coin.getY()==p.getY()){
                p.coinUp(1);
                if(p.getCoinCount()>totalCoin)
                    p.scored(2);
                supprCoins = coin;
            }
        }
        if(supprCoins != null)
            coins.remove(supprCoins);       
    }

    public boolean verifMort(){
        for (Vroum vroum : vroums)
            if(p.getY()==vroum.getY())
                if(p.getX()<vroum.getX()+1.3 && p.getX()>vroum.getX()-1.3)
                    return true;
        return false;
    }

    public void videRoad(){
        roads.clear();
    }

    public void videArbre(){
        arbres.clear();
    }

    public void videVroum(){
        vroums.clear();
    }

    public void videCoins(){
        coins.clear();
    }

    public void algoVroum(){
        compteurVroum++;
        if(compteurVroum >= 150-difficulte*10){
            for (Road road : roads) {
                if(Math.random()<difficulte/10)
                    addVroum(road, road.getCote());

            }
            compteurVroum = 0;
        }
    }

    static public boolean contains(int[] T, String val) {
        return Arrays.toString(T).contains(val);
    }

    static public boolean contains(int[][] T, int[] xy) {
        for (int[] loc : T) {
            if (loc[0] == xy[0] && loc[1] == xy[1]){
                return true;
            }
        }
        return false;
    }

    public void algoArbre(){

        int[] listRoadsY = new int[roads.size()];
        
        for (int i = 0; i < listRoadsY.length; i++) {
            listRoadsY[i] = roads.get(i).getY();
        }

        for (int i = 0; i<size-1; i++){
            if(!contains(listRoadsY, String.valueOf(i))){
                for (int j = 0; j<size; j++){
                    if(Math.random() < 0.20 && i!=0 && j != (size/2-1)){
                        arbres.add(new Arbre(j, i));
                    }
                }
            }
        }
    }

    public void algoCoin(){
        int [][] arbresXY = new int[arbres.size()][2];
        for (int i = 0; i < arbres.size(); i++) {
            arbresXY[i][0] = arbres.get(i).getX();
            arbresXY[i][1] = arbres.get(i).getY();
        }
        
        for (int y = 1; y<size-3; y++){
            for (int x = 1; x<size-1; x++){
                if(!contains(arbresXY, new int[] {x,y} )){
                    if (Math.random()<0.15) {
                        coins.add(new Coin(x,y));
                    }
                }
            }
        }
        veryTotalCoin = coins.size();
        totalCoin = coins.size()/2;
    }

    public void algoLignes(){
        for (int i = 2; i < size-2; i++) {
            if (Math.random()<difficulte/10){
                if (Math.random()<0.5)
                    addRoad(i,'d');
                else
                    addRoad(i, 'g');
            }//else{
            //     if (Math.random()<0.5)
            //         addRiver(i,'d');
            //     else
            //         addRiver(i, 'g');
            // }
        }

        if(roads.size()==0){
            int val;
            int val2;
            do{
                val = (int)(2 + (Math.random() * ((size-2) - 2)));
                val2 = (int)(2 + (Math.random() * ((size-2) - 2)));
            }while(val2 == val);
            if (Math.random()<0.5)
                addRoad(val,'d');
            else
                addRoad(val, 'g');
            if (Math.random()<0.5)
                addRoad(val2,'d');
            else
                addRoad(val2, 'g');
        }

    }

    public void initBoard(){
        p.respawn(size/2-1,0);
        p.setRotation(180);

        algoLignes();
        algoArbre();
        algoCoin();
    }

    public void game(){
        videRoad();
        videArbre();
        videVroum();
        videCoins();
        initBoard();
        p.setCanMove(false);
    }

    public void newLevel(){
        if(p.getCoinCount()>=totalCoin){
            if(p.getCoinCount() == veryTotalCoin)
                p.scored(5);
            game();
            p.scored(10);
            p.setCoinCount(0);
        
            if(difficulte <= 8){
                difficulte+=0.1;
            }
        }
    }

    public void restart(){
        game();
        p.setScore(0);
        p.setCoinCount(0);
        difficulte = 4;
    }

    public void drawBackground(){
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledSquare(size/2-0.5, size/2-0.5, size/2);

        if(p.getCoinCount()<totalCoin){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(size/2-0.5, size-1, size/2, 0.5);
        }

        // Gui.StdDraw.setPenColor(Gui.StdDraw.WHITE);
        // for (double i = 0.5; i < size; i++) 
        //     Gui.StdDraw.line(i, -0.5, i, size-0.5);
        // for (double i = 0.5; i < size; i++) 
        //     Gui.StdDraw.line(-0.5, i, size-0.5, i);
    }

    public void affiche(){
        for (Road road : roads) {
            road.afficheRoad();
        }
        
        for (Arbre arbre : arbres) {
            arbre.afficheArbre();
        }

        for (Coin coin : coins){
            coin.afficheCoin();
        }

        for (Vroum vroum : vroums){
            vroum.afficheVroum();
        }
    }

    public void gameOverScreen() throws IOException{
        
        int res = -1;

        double high = size/2+size/6-0.5;
        double low = size/2-size/6-0.5;

        double LocY = size/2-0.5;

        double restartLocX = LocY;
        double highScoreLocX = size/4-0.5;
        double quitLocX = highScoreLocX + size/2;

        while (res == -1){
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(LocY, LocY+size/4, "CROSSY ARCADE");
            StdDraw.filledRectangle(restartLocX, LocY, size/8.5, size/16);
            StdDraw.filledRectangle(highScoreLocX, LocY,size/8.5,size/16 );
            StdDraw.filledRectangle(quitLocX, LocY,size/8.5,size/16 );
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(restartLocX, LocY, "RESTART");
            StdDraw.text(highScoreLocX, LocY, "HIGHSCORE");
            StdDraw.text(quitLocX, LocY, "QUIT");
            StdDraw.show();

            boolean clickbtn = true;
            //verif appuie
            if (StdDraw.isMousePressed() && clickbtn){
                //to do
                
                //Coordonné//
                double xm = StdDraw.mouseX();
                double ym = StdDraw.mouseY();
                //
    
                //Formule//
                if (ym < high && ym > low && xm < restartLocX+1.7 && xm > restartLocX-1.7)
                    res = 0;
                else if (ym < high && ym > low && xm < highScoreLocX+1.7 && xm > highScoreLocX-1.7){
                    res = 1;
                }
                else if(ym < high && ym > low && xm < quitLocX+1.7 && xm > quitLocX-1.7)
                    res = 2;
                //
    
              //
                  
                do {
                    clickbtn = false;
                }while (StdDraw.isMousePressed());
                clickbtn = true;
            }
        }
        switch (res) {
            case 0:
                restart();
                break;
            case 1:
                showHighscore();
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public List<Score> sort(String[] list){
        for(int j = 0; j<list.length; j++){
            scores.add(new Score(list[j].split(":")[0],Integer.parseInt(list[j].split(":")[1])));
        }

        scores.sort(Score.scoreComparator);
        return scores;
    }

    public void showHighscore() throws IOException{
        int res = -1;
        String scores = "";
        String[] listScore;

        File file = new File("src\\score");
 
        FileReader fis = new FileReader(file);
        
        int charCode;
        while((charCode = fis.read()) != -1) {
            scores+=(char)charCode;
        }
        fis.close();

        listScore = scores.split("/");

        List <Score> listScoreObject = sort(listScore);

        double titleY = size/2.5 + size/2;
        double high = titleY + 1;
        double low = titleY -1;
        double middleX = size/2-0.5;
        double restartLocX = size/4-0.5;
        double quitLocX = restartLocX + size/2;

        while(res == -1){
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(middleX, titleY, "HIGHSCORE");
            StdDraw.filledRectangle(restartLocX, titleY,size/8.5,size/16);
            StdDraw.filledRectangle(quitLocX, titleY, size/8.5,size/16);

            int sizeH = listScoreObject.size();
            
            if(sizeH>12)
                sizeH = 12;

            for (int i = 0; i < sizeH; i++){
                StdDraw.text(middleX, titleY-(i*size/16)-2*size/16, listScoreObject.get(i).toString());
                if(i!=size-1){
                    StdDraw.line(middleX-this.size/3, titleY-(i*size/16)-2.5*size/16, middleX+this.size/3, titleY-(i*size/16)-2.5*size/16);
                }
            }

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(restartLocX, titleY, "RESTART");
            StdDraw.text(quitLocX, titleY, "QUIT");
            StdDraw.show();

            boolean clickbtn = true;
            //verif appuie
            if (StdDraw.isMousePressed() && clickbtn){
                //to do
                
                //Coordonné//
                double xm = StdDraw.mouseX();
                double ym = StdDraw.mouseY();
                //
    
                //Formule//
                if (ym < high && ym > low && xm < restartLocX+1.7 && xm > restartLocX-1.7)
                    res = 0;
                else if (ym < high && ym > low && xm < quitLocX+1.7 && xm > quitLocX-1.7){
                    res = 1;
                }
                //
    
              //
                  
                do {
                    clickbtn = false;
                }while (StdDraw.isMousePressed());
                clickbtn = true;
            }
        }

        switch (res) {
            case 0:
                restart();
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public void drawAll(){
        if(p.getCanMove()){
            drawBackground();
            affiche();
            p.affichePerso();
        }
    }

    public int getSize(){
        return this.size;
    }

    public Perso getPerso(){
        return p;
    }

    public int getTotalCoin() {
        return totalCoin;
    }
}