package SDS;

import java.io.*;

public class boj_2154 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        StringBuilder ss = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            ss.append(i);
        }
        bw.write(ss.indexOf(s)+1+"\n");
        bw.flush();
    }
}
