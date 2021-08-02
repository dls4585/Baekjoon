package SDS;

import java.io.*;

public class boj_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);
        for (int i = 0; i < T; i++) {
            s = br.readLine();
            char[] sArray = s.toCharArray();
            int head = 0, tail = sArray.length - 1;
            boolean retP = checkPellin(s, head, tail);
            if(retP) {
                bw.write("0\n");
            }
            else {
                boolean ret = check(s, head, tail, 0);
                if(ret) {
                    bw.write("1\n");
                } else {
                    bw.write("2\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
    public static boolean checkPellin(String s, int head, int tail) {
        while(head < tail) {
            if(s.charAt(head) == s.charAt(tail)) {
                head++; tail--;
            } else {
                return false;
            }
        }
        return true;
    }
    public static boolean check(String s, int head, int tail, int diff) {
        if(diff > 1) return false;
        if(tail - head == 1){
            return s.charAt(head) == s.charAt(tail);
        } else if(head == tail) return true;

        if(s.charAt(head) == s.charAt(tail)) {
            return check(s, head + 1, tail - 1, diff);
        } else {
            boolean b1 = check(s, head + 1, tail, diff + 1);
            boolean b2 = false;
            if(!b1) {
                diff++;
                b2 = check(s, head, tail - 1, diff);
            }
            return b1 || b2;
        }
    }
}