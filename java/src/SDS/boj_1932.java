package SDS;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1932 {
    static int[][] dp;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        int n = Integer.parseInt(s);
        dp = new int[n + 1][n + 1];
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = map[1][1];
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i + 1][j] = Math.max(dp[i][j], dp[i + 1][j]);
                dp[i + 1][j + 1] = Math.max(dp[i][j], dp[i + 1][j + 1]);
            }
            for (int j = 1; j <= i + 1; j++) {
                dp[i + 1][j] += map[i + 1][j];
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[n][i]);
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }
}
