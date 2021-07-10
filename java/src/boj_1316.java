import java.io.*;
import java.util.ArrayList;

public class boj_1316 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);


        int count = 0;
        for (int i = 0; i < T; i++) {
            s = br.readLine();
            boolean flag = false;
            ArrayList<Character> a = new ArrayList<>();
            a.add(s.charAt(0));
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j - 1) != s.charAt(j)) {
                    if (!a.contains(s.charAt(j))) {
                        a.add(s.charAt(j));
                    }
                    else {
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag) count++;
        }
        bw.write(Integer.toString(count) + "\n");
        bw.flush();
        bw.close();
    }
}
