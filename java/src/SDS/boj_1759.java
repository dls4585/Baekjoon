package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1759 {
    static int L, C;
    static char[] arr, answer;
    static boolean[] isUsed;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        answer = new char[L];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st2.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        for (int i = 0; i < C; i++) {
            int count = 0;
            isUsed = new boolean[26];
            isUsed[arr[i] - 'a'] = true;
            answer[count] = arr[i];
            if (checkMo(arr[i])) backtrack(i, count, 1, 0);
            else backtrack(i, count, 0, 1);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static boolean checkMo(char c) {
        return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u';
    }

    static void backtrack(int p, int count, int moCount, int jaCount) {
        if (count == L - 1) {
            if (moCount >= 1 && jaCount >= 2) {
                for (int i = 0; i < L; i++) {
                    sb.append(answer[i]);
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = p; i < C; i++) {
            if (isUsed[arr[i] - 'a']) continue;
            isUsed[arr[i]-'a'] = true;
            answer[count + 1] = arr[i];
            if (checkMo(arr[i])) backtrack(i, count + 1, moCount+1, jaCount);
            else backtrack(i, count + 1, moCount, jaCount + 1);

            isUsed[arr[i] - 'a'] = false;
        }
    }
}
