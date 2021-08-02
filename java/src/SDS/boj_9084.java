package SDS;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];

            dp[0] = 1;
            for (int i = 1; i <= N; i++) {
                for (int j = coins[i]; j <= M; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            bw.write(dp[M] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
