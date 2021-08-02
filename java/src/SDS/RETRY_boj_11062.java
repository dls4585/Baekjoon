package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class RETRY_boj_11062 {
    static int N;
    static int[] arr;
    static int[][][] dp; // dp [0 : 근우 , 1: 명우] [출발범위] [도착범위] 카드가 출발~도착 까지 있을 때 근우 / 명우 차례에서 근우의 점수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N];
            dp = new int[2][N][N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }


        }
        bw.flush();
        bw.close();
    }

    static int DC(int start, int end, int flag) { // flag 0 - 근우 , flag 1 - 영우
        // 1. 마지막 카드라면 (SDS.start == SDS.end)
            // 근우 차례면
            // 영우 차레면
        // 2. 이미 알고 있다면 (!=초기값)
            // 리턴
        // 3. 게임을 진행해야 한다면
            // 근우 차례면
                // 왼쪽 선택한 경우, 오른쪽 선택한 경우 + 각 value 나눠서 max를 선택
            // 영우 차례면
                // 최소가 되도록 (값 안 더해야되는데 왜? -> dp의 정의 : 근우의 점수 이므로 최소로 해야 명우가 최선의 선택을 하는 것이 됨)

        //리턴
        return dp[flag][start][end];
    }
}
