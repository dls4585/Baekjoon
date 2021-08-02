package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class boj_10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        List<Integer> a = new ArrayList<>();
        boolean flag = false;
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            a.add(Integer.parseInt(String.valueOf(s.charAt(i))));
        }
        a.sort(Collections.reverseOrder());
        if (a.get(a.size() - 1) != 0) {
            bw.write("-1\n");
            bw.flush();
            bw.close();
            return;
        }
        for (Integer integer : a) {
            total += integer;
        }
        if(total % 3 != 0) {
            bw.write("-1\n");
            bw.flush();
            bw.close();
            return;
        }
        else {
            a.forEach(i -> {
                try {
                    bw.write(i.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
