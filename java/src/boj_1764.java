import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> n = new HashMap<>();
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            n.put(s, 0);
        }
        for (int i = 0; i < M; i++) {
            s = br.readLine();
            if (n.containsKey(s)) {
                ans.add(s);
            }
        }
        bw.write(Integer.toString(ans.size()) + "\n");
        Collections.sort(ans);
        ans.forEach(item -> {
            try {
                bw.write(item+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bw.flush();
        bw.close();
    }
}
