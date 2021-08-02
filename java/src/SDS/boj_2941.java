package SDS;

import java.io.*;

public class boj_2941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        String[] candidate = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        int count = 0;
        for (int i = 0; i < 8; i++) {
            s = s.replace(candidate[i], "?");
        }
        bw.write(s.length() + "\n");
        bw.flush();
        bw.close();

    }
}
