import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;

public class Score{

    private String name;
    private int score;

    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public static String saveScore() throws IOException{
        String saves = "";

        File file = new File("src\\score");
 
        Reader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        
        String line;
        while((line = br.readLine()) != null) {
            saves += line;
        }
        br.close();

        return saves;
    }

    public void writeScore() throws IOException{
        String saves = saveScore();
        File outFile = new File("src\\score");
        FileWriter fileWriter = new FileWriter(outFile);
        fileWriter.write(saves);
        fileWriter.write(this.name+":"+this.score+"/");
        
        fileWriter.close();
    }

    public String toString() {
        return this.name + ":" + score;
    }

    public static Comparator scoreComparator = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Score s1 = (Score) o1;
            Score s2 = (Score) o2;
            if(s1.score > s2.score)
                return -1;
            if(s1.score == s2.score)
                return 0;
            return 1;
        }
    };
}
