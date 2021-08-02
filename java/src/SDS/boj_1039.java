package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1039 {
    static int N, K, max = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        String stringN = st.nextToken();
        N = Integer.parseInt(stringN);
        K = Integer.parseInt(st.nextToken());
        int len = stringN.length();

        for (int i = 0; i < len; i++) {
            String s = stringN;
            backtrack(s, i, 0);
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    static void backtrack(String s, int i, int count) {
        if(count == K) {
            if (s.charAt(0) != '0') {
                int val = Integer.parseInt(s);
                max = Math.max(max, val);
            }
            return;
        }
        int len = s.length();
        for (int j = i + 1; j < len; j++) {
            if(i == 0 && s.charAt(j) == '0') continue;
            s = swap(s, i, j);
            backtrack(s, i, count + 1);
            s = swap(s, j, i);
        }
    }

    static String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder();
        char[] c = s.toCharArray();
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
        for (char value : c) {
            sb.append(value);
        }
        s = sb.toString();
        return s;
    }
}
