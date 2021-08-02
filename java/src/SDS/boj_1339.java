package SDS;

import java.io.*;
import java.util.*;

public class boj_1339 {
    static Map<Character, Long> map = new HashMap<>();
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
        for (int i = 0; i < N; i++) {
            int len = words[i].length();
            for (int j = 0; j < len; j++) {
                long coef = (long) Math.pow(10, len - (j+1));
                char c = words[i].charAt(j);
                if(map.containsKey(c)) {
                    long get = map.get(c);
                    map.put(c, get + coef);
                }
                else {
                    map.put(c, coef);
                }
            }
        }
        List<Map.Entry<Character, Long>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        int val = 9;
        int answer = 0;
        for (Map.Entry<Character, Long> e : list) {
            answer += val-- * e.getValue();
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}