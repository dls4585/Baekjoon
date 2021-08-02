package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_11660 {
    static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        dp[1][1] = map[1][1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i][j-1] + dp[i - 1][j] - dp[i - 1][j - 1] + map[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st2.nextToken());
            int y1 = Integer.parseInt(st2.nextToken());
            int x2 = Integer.parseInt(st2.nextToken());
            int y2 = Integer.parseInt(st2.nextToken());

            int ans = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
            sb.append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
