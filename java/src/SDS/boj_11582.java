package SDS;

import java.io.*;
import java.util.*;

public class boj_11582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        List<Integer> a = new ArrayList<>();
        s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }
        int k = Integer.parseInt(br.readLine());
        int current = N / 2;
        int qtyPerOne = N / current;
        List<Integer> b = null;
        while(current >= k) {
            b = DC(N, qtyPerOne, a);
            current /= 2;
            qtyPerOne = N / current;
        }
        b.forEach(i-> {
            try {
                bw.write(Integer.toString(i) + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.write("\n");
        bw.flush();
        bw.close();
    }
    public static List<Integer> sort(List<Integer> a) {
        Collections.sort(a);
        return a;
    }
    public static List<Integer> DC(int N, int qtyPerOne, List<Integer> a) {
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < N; i+=qtyPerOne) {
            List<Integer> c = sort(a.subList(i, i + qtyPerOne));
            b.addAll(c);
        }
        return b;
    }
}
