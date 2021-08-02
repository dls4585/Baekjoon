package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_15552 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String T = br.readLine();
        int t = Integer.parseInt(T);
        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bw.write(A+B + "\n");
        }
        bw.flush();
        bw.close();
    }
}
