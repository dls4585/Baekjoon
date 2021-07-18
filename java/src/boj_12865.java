import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] W = new int[N];
        int[] V = new int[N];
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st2 = new StringTokenizer(s);
            int w = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());
            W[i] = w;
            V[i] = v;
        }
        int[][] memo = new int[N + 1][K + 1];
        int max = 0;
        Arrays.fill(memo[0], 0);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if(W[i-1] <= j) {
                    memo[i][j] = Math.max(V[i-1] +memo[i-1][j-W[i-1]], memo[i-1][j]);
                } else {
                    memo[i][j] = memo[i-1][j];
                }
                max = Math.max(memo[i][j], max);
            }
        }
        bw.write(Integer.toString(max) + "\n");
        bw.flush();
        bw.close();

    }
}
