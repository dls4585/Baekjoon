package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        double percent = (double) Y * 100/ X;
        percent = Math.floor(percent);
        int percentInt = (int) Math.floor(percent);
        if(percentInt == 100 || percentInt == 99 ) {
            bw.write("-1\n");
            bw.flush();
            bw.close();
            return;
        }

        long start = 0;
        long end = 2000000001;
        long mid = (start + end) / 2;
        double newP;
        while(start <= end) {
            newP = (double) (Y+mid)* 100 / (X + mid);
            if(newP == percentInt + 1) break;
            else if(newP < percentInt + 1) {
                start = mid;
                mid = (start + end) / 2;
            } else {
                end = mid;
                mid = (start + end) / 2;
            }
            if(end - start == 1) {
                mid++;
                break;
            }
        }
        bw.write(Long.toString(mid) + "\n");
        bw.flush();
        bw.close();
    }
}
