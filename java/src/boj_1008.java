import java.io.*;
import java.util.StringTokenizer;

public class boj_1008 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        double A = Double.parseDouble(st.nextToken());
        double B = Double.parseDouble(st.nextToken());

        bw.write(Double.toString(A / B));
        bw.flush();
    }
}
