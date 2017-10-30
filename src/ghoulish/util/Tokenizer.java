package ghoulish.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class Tokenizer {

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
