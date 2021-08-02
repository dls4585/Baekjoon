package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_15651 {
    static int[] answer;
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

        for (int i = 1; i <= N; i++) {
            int count = 1;
            answer[count] = i;
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
            answer[count + 1] = j;
            backtrack(count + 1);
        }

    }
}
