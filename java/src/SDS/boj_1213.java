package SDS;

import java.io.*;
import java.util.*;

public class boj_1213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        char[] sArray = s.toCharArray();
        char[] ans = new char[sArray.length];
        Arrays.sort(sArray);

        HashMap<Character, Integer> a = count(sArray);

        if (!isValid(a, sArray)) {
            bw.write("I'm Sorry Hansoo\n");
            bw.flush();
            bw.close();
            return;
        }
        else {
            char c = 0;
            int i, n = 0, count = 0;
            for (i = 0; i < sArray.length;) {
                char cc = sArray[i];
                int get = a.get(cc);
                if(get % 2 == 0) {
                    ans[n] = ans[sArray.length-1-n] = cc;
                    i += 2;
                    n++;
                }
                else {
                    if(c == 0) {
                        c = cc;
                        count = get;
                        a.put(cc, a.get(cc) - 1);
                    }
                    i++;
                }
            }
            if(sArray.length % 2 != 0) {
                ans[n] = c;
            }
        }
        for (char c: ans) {
            bw.write(c);
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }
    public static HashMap<Character, Integer> count(char[] sArray) {
        HashMap<Character, Integer> a = new HashMap<>();
        for (char c : sArray) {
            Integer b = a.get(c);
            if (b != null) {
                a.put(c, ++b);
            } else {
                a.put(c, 1);
            }
        }
        return a;
    }
    public static boolean isValid(HashMap<Character, Integer> a, char[] sArray) {
        long count = sArray.length;
        int odd = countOdd(a);
        if(count % 2 == 0) { // 짝수개
            return odd <= 0;
        }
        else { // 홀수개
            return odd <= 1;
        }
    }
    public static int countOdd(HashMap<Character, Integer> a) {
        final int[] count = {0};
        a.forEach((k, v) -> {
            if(a.get(k) % 2 != 0) count[0]++;
        });
        return count[0];
    }
}
