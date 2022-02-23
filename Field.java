/**
 * Field
 */
import GUILibrary.StdDraw;
import java.util.List;
import java.util.ArrayList;
// import java.awt.Color;

public class Field{

    
    public static int compteurVroum = 50;

    private boolean canKey;
    private int size;
    private Perso p;
    private List<Arbre> arbres;
    private List<Road> roads;
    private List<Vroum> vroums;
    private double difficulte; 

    public Field(int size){
        // StdDraw.setCanvasSize(700, 700);
        StdDraw.setScale(-0.5, size-0.5);
        
        StdDraw.enableDoubleBuffering();
        this.size = size;
        this.arbres = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.vroums = new ArrayList<>();
        this.canKey = true;

        this.difficulte = 8;

        p = new Perso(0,0,0.4,180);
    }

    public void addArbre(int x, int y){
        arbres.add(new Arbre(x,y));
    }

    public Road addRoad(int y,char cote){
        double max = difficulte/10/3;
        double min = (difficulte/10/3)-0.1;
        double speed = min + (Math.random()*(max-min));
        System.out.println(y+"  "+speed);
        System.out.println(min+"  "+max);
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


    //CHANGER SYSTEME DE TOUCHE
    public void movePerso(Perso p, int d){

        int x;
        int y;
        if(p.getCanMove()){

            //up
            if(StdDraw.isKeyPressed(90)&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(180);
                if(y<=size-1 && !isCollision(x, y+1, arbres))
                    p.setY(y+d);
                drawAll();
            }else if(!StdDraw.isKeyPressed(90)&&!StdDraw.isKeyPressed(81)&&!StdDraw.isKeyPressed(83)&&!StdDraw.isKeyPressed(68)&&!canKey){
                canKey = true;
            }

            //left
            if(StdDraw.isKeyPressed(81)&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(270);
                if(x>0 && !isCollision(x-1, y, arbres))
                    p.setX(x-d);
                drawAll();
            }else if(!StdDraw.isKeyPressed(90)&&!StdDraw.isKeyPressed(81)&&!StdDraw.isKeyPressed(83)&&!StdDraw.isKeyPressed(68)&&!canKey){
                canKey = true;
            }

            //down
            if(StdDraw.isKeyPressed(83)&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(0);
                if(y>0 && !isCollision(x, y-1, arbres))
                    p.setY(y-d);
                drawAll();
            }else if(!StdDraw.isKeyPressed(90)&&!StdDraw.isKeyPressed(81)&&!StdDraw.isKeyPressed(83)&&!StdDraw.isKeyPressed(68)&&!canKey){
                canKey = true;
            }

            //right
            if(StdDraw.isKeyPressed(68)&&canKey){
                canKey = false;
                x = p.getX();
                y = p.getY();
                p.setRotation(90);
                if(x<=size && !isCollision(x+1, y, arbres))
                    p.setX(x+d);
                drawAll();
            }else if(!StdDraw.isKeyPressed(90)&&!StdDraw.isKeyPressed(81)&&!StdDraw.isKeyPressed(83)&&!StdDraw.isKeyPressed(68)&&!canKey){
                canKey = true;
            }
        }

        if (p.getY()>=size) {
            newLevel();
        }
    }

    public boolean isCollision(int x, int y, List<Arbre> arbres){

        for (Arbre arbre : arbres)
            if (arbre.getX() == x && arbre.getY() == y)
                return true;

        return false;
    }

    public void drawBackground(){
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledSquare(size/2-0.5, size/2-0.5, size/2);

        StdDraw.setPenColor(StdDraw.WHITE);
        for (double i = 0.5; i < size; i++) 
            StdDraw.line(i, -0.5, i, size-0.5);
        for (double i = 0.5; i < size; i++) 
            StdDraw.line(-0.5, i, size-0.5, i);
    }

    public void affiche(){
        for (Road road : roads) {
            road.afficheRoad();
        }
        for (Arbre arbre : arbres) {
            arbre.afficheArbre();
        }
        for (Vroum vroum : vroums){
            vroum.afficheVroum();
        }
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

    public void algoVroum(){
        compteurVroum++;
        if(compteurVroum >= 50){
            for (Road road : roads) {
                if(Math.random()<difficulte/10)
                    addVroum(road, road.getCote()); 
                
            }
            compteurVroum = 0;
        }
    }

    public void algoArbre(){
        //faire algo
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
        p.setY(0);
        p.setX(size/2-1);
        p.setRotation(180);

        algoLignes();
        
    }

    public void game(){
        videRoad();
        videArbre();
        videVroum();
        initBoard();
        p.setCanMove(false);
    }

    public void newLevel(){
        game();
        p.setScore(p.getScore()+10);
        
        if(difficulte <= 8){
            difficulte+=0.1;
            System.out.println(difficulte);
        }
    }

    public void restart(){
        game();
        p.setScore(0);
        difficulte = 5;
    }

    public void drawAll(){
        drawBackground();
        affiche();
        p.affichePerso();
        p.afficheScore(size-2, size-1);
        StdDraw.show();
    }

    public int getSize(){
        return this.size;
    }

    public Perso getPerso(){
        return p;
    }
}