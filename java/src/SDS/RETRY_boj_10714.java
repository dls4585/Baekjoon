package SDS;

import java.io.*;

public class RETRY_boj_10714 {
//    static long[][][] dp; // dp[ JOI | IOI 턴 ] [ start ] [ end ] : SDS.start~SDS.end 까지의 조각이 있을 때, 각 턴에서 JOI의 최대 점수
    static long[][] dp; // dp [left] [right] :  left~right 케익을 먹었을 때 JOI가 갖고 있는 최대 크기
    static boolean[] choosed; // i번째 조각이 선택됐는가?
    static int[] A; // Ai - i번쨰 조각의 크기
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][N + 1];
        choosed = new boolean[N + 1];
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            // 어떤 거를 먼저 선택하는게 최선인지 모르니 다 해보는 것 고르고 IOI에게 턴을 넘김
            // ans = max()
        }
    }

    static int goRight(int id) {
        return (id + 1) % N;
    }
    static int goLeft(int id) {
        return (id + N - 1) % N;
    }
}
