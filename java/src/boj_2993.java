import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class boj_2993 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        ArrayList<String> sl = new ArrayList<>();

        for (int i = 0; i < s.length() - 2; i++) {
            for (int j = i + 1; j < s.length() - 1; j++) {
                for (int k = j + 1; k < s.length(); k++) {
                    String t = new StringBuffer(s.substring(0, j)).reverse().toString()
                            + new StringBuffer(s.substring(j, k)).reverse().toString()
                            + new StringBuffer(s.substring(k)).reverse().toString();
                    sl.add(t);
                }
            }
        }
        bw.write(Collections.min(sl));
        bw.flush();
    }
}
