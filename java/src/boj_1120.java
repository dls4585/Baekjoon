import java.io.*;
import java.util.StringTokenizer;


// 따로 메모해놓기


public class boj_1120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        String A = st.nextToken();
        String B = st.nextToken();
        int diff = A.length();
        for (int i = 0; i <= B.length() - A.length(); i++) {
            int k = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j) != B.charAt(i + j)) {
                    k++;
                }
            }
            if(diff > k) {
                diff = k;
            }
        }

        bw.write(Integer.toString(diff) + "\n");
        bw.flush();
        bw.close();
    }
}
