package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class boj_1377 {
    static List<PairII> a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            a.add(new PairII(Integer.parseInt(s), i));
        }
        Collections.sort(a);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if(ans < a.get(i).i - i) {
                ans = a.get(i).i - i;
            }
        }
        bw.write(Integer.toString(ans + 1) + "\n");
        bw.flush();
        bw.close();
    }
}
class PairII implements Comparable<PairII>{
    int number;
    int i;

    PairII(int number, int i) {
        this.number = number;
        this.i = i;
    }

    @Override
    public int compareTo(PairII o) {
        return (-1)*Integer.compare(o.number, this.number);
    }
}
