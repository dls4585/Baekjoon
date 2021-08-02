package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        int[][] dp = new int[N][M];
        int max = 0;

        for (int i = 0; i < M; i++) {
            dp[0][i] = map[0][i] == 1 ? 1 : 0;
            max = Math.max(dp[0][i], max);
        }
        for (int i = 0; i < N; i++) {
            dp[i][0] = map[i][0] == 1 ? 1 : 0;
            max = Math.max(dp[i][0], max);

        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if(map[i][j] != 0) dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        bw.write(max * max + "\n");
        bw.flush();
        bw.close();
    }
}
