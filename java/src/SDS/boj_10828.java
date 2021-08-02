package SDS;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            String op = st.nextToken();
            switch (op) {
                case "push":
                    int operand = Integer.parseInt(st.nextToken());
                    stack.push(operand);
                    break;
                case "top":
                    if (stack.empty()) {
                        bw.write("-1\n");
                        continue;
                    }
                    int top = stack.peek();
                    bw.write(Integer.toString(top) + "\n");
                    break;
                case "pop":
                    if (stack.empty()) {
                        bw.write("-1\n");
                        continue;
                    }
                    int pop = stack.pop();
                    bw.write(Integer.toString(pop) + "\n");
                    break;
                case "size":
                    bw.write(Integer.toString(stack.size()) + "\n");
                    break;
                case "empty":
                    if (stack.isEmpty()) {
                        bw.write("1\n");
                    } else {
                        bw.write("0\n");
                    }
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}
