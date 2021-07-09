import java.io.*;
import java.util.Arrays;

public class boj_2577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int A = Integer.parseInt(s);
        int result = A;

        for (int i = 1; i < 3; i++) {
            s = br.readLine();
            result *= Integer.parseInt(s);
        }

        String resString = Integer.toString(result);
        int[] ans = new int[10];
        Arrays.fill(ans, 0);
        for (int i = 0; i < resString.length(); i++) {
            int index = Integer.parseInt(String.valueOf(resString.charAt(i)));
            ans[index]++;
        }

        Arrays.stream(ans).forEach(item -> {
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
