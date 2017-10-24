package ghoulish.util;

import ghoulish.labyrinth.GreatWall;
import ghoulish.labyrinth.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class LabReader {
    FileReader in;
    int n,m;

    public LabReader(FileReader _in){
        in = _in;
    }

    public Part[][] constructLab(){
        Tokenizer tk = new Tokenizer(in);
        Part[][] ans = null;
        try {
            n = tk.nextInt();
            m = tk.nextInt();

             ans = new Part[n+2][m+2];

            for(int i = 0; i < n + 2; i++){
                ans[i][0] = new GreatWall();
                ans[i][m+1] = new GreatWall();
            }

            for(int i = 0; i < m + 2; i++){
                ans[0][i] = new GreatWall();
                ans[n+1][i] = new GreatWall();
            }

            BlockChooser bc = new BlockChooser();

            for(int i = 1; i < n + 1; i++){
                for (int j = 1; j < m + 1; j++){
                    ans[i][j] = bc.chooseBlock(tk.nextInt());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ans;
    }

    private static class Tokenizer {

        private BufferedReader in;
        private StringTokenizer tokenizer;

        public Tokenizer(Reader reader) {
            in = new BufferedReader(reader);
        }

        public String next() throws Exception {
            while(tokenizer == null || !tokenizer.hasMoreElements()) {
                String line = in.readLine();
                if(line == null) return null;

                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

    }

    public int getN(){
        return n;
    }

    public int getM() {
        return m;
    }
}
