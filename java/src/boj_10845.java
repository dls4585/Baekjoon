import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        Queue<Integer> q = new LinkedList<>();
        int last = 0;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            String op = st.nextToken();
            switch (op) {
                case "push":
                    last = Integer.parseInt(st.nextToken());
                    q.add(last);
                    break;
                case "pop":
                    if (q.isEmpty()) {
                        bw.write("-1\n");
                        continue;
                    }
                    int pop = q.remove();
                    bw.write(Integer.toString(pop) + "\n");
                    break;
                case "size":
                    bw.write(Integer.toString(q.size()) + "\n");
                    break;
                case "empty":
                    if (q.isEmpty()) {
                        bw.write("1\n");
                    } else {
                        bw.write("0\n");
                    }
                    break;
                case "front":
                    if (q.isEmpty()) {
                        bw.write("-1\n");
                        continue;
                    }
                    int top = q.peek();
                    bw.write(Integer.toString(top) + "\n");
                    break;
                case "back" :
                    if(q.isEmpty()) {
                        bw.write("-1\n");
                        continue;
                    }
                    bw.write(Integer.toString(last) + "\n");
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}
