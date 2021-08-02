package SDS;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15654 {
    static boolean[] isUsed;
    static int[] answer, arr;
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = new int[M + 1];
        arr = new int[N + 1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }
        Arrays.sort(arr);
        for (int i = 1; i <= N; i++) {
            isUsed = new boolean[N + 1];
            int count = 1;
            isUsed[i] = true;
            answer[count] = arr[i];
            backtrack(count);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void backtrack(int count) {
        if(count >= M) {
            for (int j = 1; j <= M; j++) {
                sb.append(answer[j]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int j = 1; j <= N; j++) {
            if(isUsed[j]) continue;
            isUsed[j] = true;
            answer[count + 1] = arr[j];
            backtrack(count + 1);
            isUsed[j] = false;
        }

    }
}
