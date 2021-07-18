import java.io.*;
import java.util.StringTokenizer;

public class boj_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        int[] R = new int[N];
        int[] G = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            R[i] = Integer.parseInt(st.nextToken());
            G[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
        int[][] memo = new int[N][3];
        memo[0][0] = R[0];
        memo[0][1] = G[0];
        memo[0][2] = B[0];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0: // R
                        memo[i][j] = Math.min(memo[i - 1][1] + R[i], memo[i - 1][2] + R[i]);
                        break;
                    case 1: // G
                        memo[i][j] = Math.min(memo[i - 1][0] + G[i], memo[i - 1][2] + G[i]);
                        break;
                    case 2: // B
                        memo[i][j] = Math.min(memo[i - 1][0] + B[i], memo[i - 1][1] + B[i]);
                        break;
                }
            }
        }
        int min = 1000 * N + 1;
        for (int i = 0; i < 3; i++) {
            min = Math.min(memo[N - 1][i], min);
        }
        bw.write(Integer.toString(min) + "\n");
        bw.flush();
        bw.close();
    }
}
