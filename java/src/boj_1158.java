import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> a = new ArrayList<Integer>();
        int n = 0, k = K;


        while (n < N) {

            n++;
        }
        bw.write(String.valueOf(a));
        bw.flush();
    }
}
