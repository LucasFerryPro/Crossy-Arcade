import java.io.IOException;

import Gui.StdDraw;

public class Game {

    public static Field terrain = new Field(16);
    public static Canevas canevas = new Canevas(terrain.getSize(), terrain);
    public static int waitTime = 100;
    public static int compteurTime = waitTime;

    public static void main(String[] args) throws IOException {

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
                Score score = new Score(p.getName(),p.getScore());
                score.writeScore();
                terrain.gameOverScreen();
            }

            terrain.drawAll();

            if(p.getCanMove()){
                canevas.affichescore(terrain.getSize() - terrain.getSize()/8, terrain.getSize() - 1, p.getScore());
                canevas.affichecoincount(terrain.getSize()/9, terrain.getSize() - 1, p.getCoinCount(), terrain.getTotalCoin());
            }
            
            StdDraw.show();
            StdDraw.pause(10);
        }
        
    }    
}
