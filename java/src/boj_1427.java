import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class boj_1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        ArrayList<Integer> a  = makeArray(s);
        a.sort(Collections.reverseOrder());
        for (int j : a) {
            bw.write(Integer.toString(j));
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }

    public static ArrayList<Integer> makeArray(String s) {
        int size = s.length();
        ArrayList<Integer> b = new ArrayList<>();
        int n = Integer.parseInt(s);
        while (size > 0) {
            b.add(n / (int) Math.pow(10, size - 1));
            n %= (int) Math.pow(10, size - 1);
            size--;
        }
        return b;
    }
}
