package SDS;

import java.io.*;
import java.util.*;

public class boj_1158 {
    public static ArrayList<Integer> a = new ArrayList<Integer>();
    public static Queue<Integer> q = new LinkedList<>();
    public static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int n = 0;
        k = 1;

        for (int i = 0; i < N; i++) {
            q.add(i + 1);
        }
        int i = 0;
        while (n < N) {
            int ret = q.remove();
            i++;
            if (i == K) {
                i = 0;
                a.add(ret);
                n++;
            } else q.add(ret);
        }
        bw.write("<");
        for (int j = 0; j < a.size(); j++) {
            bw.write(Integer.toString(a.get(j)));
            if (j != a.size() - 1) {
                bw.write(", ");
            } else {
                bw.write(">");
            }
        }
//        bw.write(String.valueOf(a));
        bw.flush();
    }


}
