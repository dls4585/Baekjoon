package SDS;

import java.io.*;

public class boj_1032 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);
        String[] ss = new String[T];

        for (int i = 0; i < T; i++) {
            s = br.readLine();
            ss[i] = s;
        }
        boolean[] same = new boolean[ss[0].length()];
        for (int i = 0; i < ss[0].length(); i++) {
            char temp = ss[0].charAt(i); // a
            boolean isSame = true;
            for (int j = 1; j < T; j++) {
                if (ss[j].charAt(i) != temp) {
                   isSame = false;
                   break;
                }
            }
            same[i] = isSame;
        }

        for (int i = 0; i < ss[0].length(); i++) {
            if(same[i]) {
                bw.write(ss[0].charAt(i));
            }
            else {
                bw.write("?");
            }
        }
        bw.write("\n");
        bw.flush();
    }
}
