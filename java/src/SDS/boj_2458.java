package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2458 {
    static int[][] dist;
    static int max = 200000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = max;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());

            dist[a][b] = 1; // a가 b보다 작다
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            int outCount = 0;
            int inCount = 0;
            for (int j = 1; j <= N; j++) {
                if(dist[i][j] != max && dist[i][j] != 0) {
                    outCount++;
                }
                else if(dist[j][i] != max && dist[i][j] != 0) {
                    inCount++;
                }
            }
            if (inCount + outCount == N - 1) {
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}
