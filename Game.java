import GUILibrary.StdDraw;

public class Game {

    public static Field terrain = new Field(16);
    public static int waitTime = 100;
    public static int compteurTime = waitTime;

    public static void main(String[] args) {
        Perso p = terrain.getPerso();

        terrain.initBoard();

        terrain.drawAll();
        while(true){

            if(!p.getCanMove()){
                StdDraw.filledSquare((terrain.getSize()/2)-0.5, (terrain.getSize()/2)-0.5, terrain.getSize()/2);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.text((terrain.getSize()/2)-0.5, (terrain.getSize()/2)-0.5, "LOADING");
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.show();
                compteurTime--;
            }
            if (compteurTime==0){
                compteurTime = waitTime;
                p.setCanMove(true);
            }

            terrain.algoVroum();
            terrain.moveVroums();

            terrain.movePerso(p,1);
            if(terrain.verifMort()){
                StdDraw.clear();
                terrain.restart();
            }

            terrain.drawAll();
            StdDraw.pause(10);
        }
        
    }    
}
