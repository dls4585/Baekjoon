package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1182 {
    static int count = 0;
    static int[] arr;
    static int target = 0, N, S;
    static boolean[] isUsed;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < N; i++) { // 부분수열의 크기가 1인 경우
            if(arr[i] == S) count++;
        }

        for (int i = 2; i <= N; i++) { // 부분수열의 크기가 2~N인 경우
            target = i;
            for (int j = 0; j < N; j++) {
                isUsed = new boolean[N];
                isUsed[j] = true;
                backtracking(j, 1);
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }
    static void backtracking(int a, int cnt) {
        if(cnt == target) {
            int total = 0;
            for (int i = 0; i < N; i++) {
                if(isUsed[i]) {
                    total += arr[i];
                }
            }
            if(total == S) count++;
            return;
        }
        for (int i = a; i < N; i++) {
            if(isUsed[i]) continue;
            isUsed[i] = true;
            backtracking(i, cnt + 1);
            isUsed[i] = false;
        }
    }
}
