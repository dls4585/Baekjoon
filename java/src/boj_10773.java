import java.io.*;
import java.util.LinkedList;
import java.util.Stack;

public class boj_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < T; i++) {
            s = br.readLine();
            int value = Integer.parseInt(s);
            if (value == 0) {
                st.pop();
            }
            else {
                st.push(value);
            }
        }
        int total = 0;
        int size = st.size();
        for (int i = 0; i < size; i++) {
            total += st.pop();
        }
        bw.write(Integer.toString(total) + "\n");
        bw.flush();
        bw.close();
    }
}
