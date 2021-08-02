package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);

        for (int i = 0; i < T; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            B = B % 4 + 4;

            int result = (int) (Math.pow(A, B) % 10);
            if (result == 0) result = 10;

            bw.write(result + "\n");
        }
        bw.flush();
    }

}
