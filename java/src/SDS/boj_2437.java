package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(String.valueOf(st.nextToken())));
        }

        Collections.sort(a);
        if(a.get(0) > 1) {
            bw.write("1\n");
            bw.flush();
            bw.close();
            return;
        }
        int sum;
        if (a.size() == 1) {
            sum = a.get(0);
        } else {
            if (a.get(0) + 1 < a.get(1)) {
                sum = a.get(0);
            } else {
                sum = a.get(0) + a.get(1);
                for (int i = 2; i < a.size(); i++) {
                    if (sum < a.get(i) && sum + 1 != a.get(i)) {
                        break;
                    } else {
                        sum += a.get(i);
                    }
                }
            }
        }

        bw.write(Integer.toString(sum + 1));
        bw.flush();
        bw.close();
    }
}
