import java.io.*;
import java.util.StringTokenizer;

public class boj_1152 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);

        bw.write(st.countTokens() + "\n");
        bw.flush();
        bw.close();
    }
}
