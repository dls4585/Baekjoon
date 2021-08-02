package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15811 {
    static boolean[] usedNumber = new boolean[10], usedAlphabet = new boolean[26];
    static int[] alphabet2Number = new int[26];
    static List<Integer> list = new ArrayList<>();
    static int num1, num2, num3, alphabetCount = 0;
    static String A, B, C;
    static boolean isBMS;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        A = st.nextToken();
        B = st.nextToken();
        C = st.nextToken();
        for (int i = 0; i < A.length(); i++) {
            usedAlphabet[A.charAt(i) - 'A'] = true;
        }
        for (int i = 0; i < B.length(); i++) {
            usedAlphabet[B.charAt(i) - 'A'] = true;
        }
        for (int i = 0; i < C.length(); i++) {
            usedAlphabet[C.charAt(i) - 'A'] = true;
        }
        for (int i = 0; i < 26; i++) {
            if(usedAlphabet[i]) {
                alphabetCount++;
                list.add(i);
            }
        }
        if(alphabetCount > 10) {
            bw.write("NO\n");
            bw.flush();
            bw.close();
            return;
        }
        backtracking(0);

        if(isBMS) bw.write("YES\n");
        else bw.write("NO\n");
        bw.flush();
        bw.close();
    }

    static int string2Number(String s) {
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            ret = ret * 10 + alphabet2Number[s.charAt(i) - 'A'];
        }
        return ret;
    }

    static void backtracking(int count) {
        if(isBMS) return;
        if(count == alphabetCount) { // 할당 완료
            // 세 문자열 변환
            num1 = string2Number(A);
            num2 = string2Number(B);
            num3 = string2Number(C);
            // 복면산이면 true
            if (num1 + num2 == num3) {
                isBMS = true;
            }
        }
        else { // 계속 할당
            for (int i = 0; i < 10; i++) {
                if(usedNumber[i]) continue;
                usedNumber[i] = true;
                alphabet2Number[list.get(count)] = i;
                backtracking(count + 1);
                usedNumber[i] = false;
            }
        }
    }
}
