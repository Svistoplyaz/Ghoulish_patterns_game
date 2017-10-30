package ghoulish.util;

import ghoulish.labyrinth.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class LabReader {
    private FileReader in;
    private int n,m;
    private String[] voc = {"W", "F", "SF", "TF", "STF"};

    public LabReader(FileReader _in){
        in = _in;
    }

    public String[][] fillLab(){
        Tokenizer tk = new Tokenizer(in);
        String[][] ans;

        try {
            n = tk.nextInt();
            m = tk.nextInt();
        }catch (Exception e){
            e.printStackTrace();
        }

        ans = new String[n+2][m+2];

        for(int i = 0; i < m + 2; i++){
            ans[0][i] = "W";
            ans[n + 1][i] = "W";
        }

        for(int i = 0; i < n + 2; i++){
            ans[i][0] = "W";
            ans[i][m + 1] = "W";
        }

        try {
            for(int i = 1; i < n + 1; i++)
                for(int j = 1; j < m + 1; j++)
                    ans[i][j] = voc[tk.nextInt()-1];

        }catch (Exception e){
            e.printStackTrace();
        }

        return ans;
    }

    public int getN(){
        return n;
    }

    public int getM() {
        return m;
    }
}
