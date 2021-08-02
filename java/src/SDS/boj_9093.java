package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_9093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String t = br.readLine();
        int T = Integer.parseInt(t);

        for (int i = 0; i < T; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            while(st.hasMoreTokens()) {
                StringBuffer b = new StringBuffer(st.nextToken());
                bw.write(b.reverse().toString());
                if(st.hasMoreTokens()) bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
