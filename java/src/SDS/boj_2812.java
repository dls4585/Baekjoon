package SDS;

import java.io.*;
import java.util.*;

public class boj_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        s = br.readLine();
        Deque<Long> dq = new ArrayDeque<>();
        long[] a = new long[N];
        for (int i = 0; i < s.length(); i++) {
            a[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
        }

        dq.push(a[0]);
        int target = K;
        target--;
        int i;
        for (i = 1; i < N && target >= 0; i++) {
            if (dq.getLast() < a[i]) {
                while (dq.getLast() < a[i] && target >= 0) {
                    dq.removeLast();
                    target--;
                    if (dq.size() == 0) break;
                }
            }
            dq.add(a[i]);
        }
        while (dq.size() > N - K) {
            dq.removeLast();
        }
        if (i != N && dq.size() < N - K) {
            for (int j = i; j < N; j++) {
                dq.add(a[j]);
            }
        }
        dq.forEach(integer -> {
            try {
                bw.write(Integer.toString(Math.toIntExact(integer)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.write("\n");
        bw.flush();
        bw.close();
    }
}
