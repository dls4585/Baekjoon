package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class boj_11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        if(N == 1) {
            return;
        }
        int n = 2;
        while(N > 1) {
            if (N % n == 0) {
                bw.write(n + "\n");
                N /= n;
            }
            else {
                n++;
            }
        }
        bw.flush();
        bw.close();
    }

}
