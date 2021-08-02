package SDS;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class boj_5582 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String A = br.readLine();
        String B = br.readLine();

        int N = A.length();
        int M = B.length();
        dp = new int[N+1][M+1];

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }
}

