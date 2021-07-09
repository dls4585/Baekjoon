import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_1547 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);

        HashMap<Integer, Integer> a = new HashMap<>();
        a.put(1, 1);
        a.put(2, 2);
        a.put(3, 3);

        for (int i = 0; i < T; i++) {
            String c = br.readLine();
            StringTokenizer st = new StringTokenizer(c);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            swap(a, A, B);
        }
        a.forEach((key, value) -> {
            if(value == 1) {
                try {
                    bw.write(key+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//        bw.write(Integer.toString(a.get(0))+"\n");
        bw.flush();
    }

    public static void swap(HashMap<Integer, Integer> a, int i, int j) {
        int temp = a.get(i);
        a.put(i, a.get(j));
        a.put(j, temp);
    }
}
