package SDS;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_3860 {
    static int[][] map;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    static Map<start, end> holes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            if(W == 0 && H == 0) break;
            map = new int[H][W];
            int G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                StringTokenizer sst = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(sst.nextToken());
                int Y = Integer.parseInt(sst.nextToken());
                map[X][Y] = 'G';
            }
            int E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; i++) {
                StringTokenizer sstt = new StringTokenizer(br.readLine());
                int X1 = Integer.parseInt(sstt.nextToken());
                int Y1 = Integer.parseInt(sstt.nextToken());
                int X2 = Integer.parseInt(sstt.nextToken());
                int Y2 = Integer.parseInt(sstt.nextToken());
                int T = Integer.parseInt(sstt.nextToken());
                holes.put(new start(X1, Y1), new end(X2, Y2, T));
                map[X1][Y1] = 'E';
            }

            // 시작점부터

            // 이동할 수 있으면 cost = 1 -> 갱신
            // 귀신구멍이면 그 도착지까지 거리 = 귀신구멍까지 거리 + cost로 갱신

        }
    }
}
class start {
    int X1, Y1;
    start(int X1, int Y1) {
        this.X1 = X1;
        this.Y1 = Y1;
    }
}
class end {
    int X2, Y2, cost;
    end (int X2, int Y2, int cost) {
        this.X2 = X2;
        this.Y2 = Y2;
        this.cost = cost;
    }
}
