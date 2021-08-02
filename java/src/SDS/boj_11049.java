package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_11049 {
    static int[][] dp;
    static int[] r;
    static int[] c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        r = new int[N];
        c = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int min = divideConquer(0, N - 1);

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    static int divideConquer(int a, int b) {
        if(a==b) return 0;
        if(dp[a][b] != Integer.MAX_VALUE) return dp[a][b];

        int left, right;
        for (int i = a; i < b; i++) {
            left = divideConquer(a, i);
            right = divideConquer(i + 1, b);

            dp[a][b] = Math.min(dp[a][b], left + right + r[a] * c[i] * c[b]);
        }
        return dp[a][b];
    }
}
