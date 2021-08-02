package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_7579 {
    static int N, M, costSum; // N apps, M bytes 확보 필요
    static int[] bytes, costs;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bytes = new int[N + 1];
        costs = new int[N + 1];
        String s1 = br.readLine();
        StringTokenizer st1 = new StringTokenizer(s1);
        for (int i = 1; i <= N; i++) {
            bytes[i] = Integer.parseInt(st1.nextToken());
        }
        String s2 = br.readLine();
        StringTokenizer st2 = new StringTokenizer(s2);
        costSum = 0;
        for (int i = 1; i <= N; i++) {
            costSum += costs[i] = Integer.parseInt(st2.nextToken());
        }
        dp = new int[N + 1][costSum + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= costSum; j++) {
                if(j - costs[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - costs[i]] + bytes[i]);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        for (int i = 0; i <= costSum; i++) {
            if(dp[N][i] >= M) {
                bw.write(i + "\n");
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}
