import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_3425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();
        Queue<String> q = new LinkedList<>();
        boolean inputFlag = false;
        while(true) {
            String s = br.readLine();
            if(s.equals("QUIT")) break;
            if(!inputFlag) {
                if(!s.equals("END")) {
                    q.add(s);
                    inputFlag = true;
                }
            }
            else {
                StringTokenizer opSt = new StringTokenizer(q.remove());
                String op = opSt.nextToken();
                String operand = null;
                if(opSt.hasMoreTokens()) operand = opSt.nextToken();
                int N = Integer.parseInt(s);
                stack.push(N);
                if(op.equals("DUP")) {
                    int dup = stack.peek();
                    stack.push(dup);
                } else if (op.equals("MUL")) {
                    try{
                        int first = stack.pop();
                        int second = stack.pop();
                        stack.push(first * second);
                    } catch (Error error) {
                        bw.write("Error\n");
                    }
                } else if (op.equals("NUM")) {
                    int OPERAND = Integer.parseInt(operand);
                    stack.push(OPERAND);
                } else if (op.equals("ADD")) {
                    try{
                        int first = stack.pop();
                        int second = stack.pop();
                        stack.push(first + second);
                    } catch (Error error) {
                        bw.write("Error\n");
                    }
                }
            }
        }
    }
}
