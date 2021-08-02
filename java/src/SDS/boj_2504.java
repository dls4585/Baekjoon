package SDS;

import java.io.*;
import java.util.Stack;

public class boj_2504 {
    static Stack<Character> stack = new Stack<>();
    static int index = 0;
    static int total = 0;
    static boolean impossible = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();

        int ret = 1;
        while(index < s.length()) {
            if(s.charAt(index) == '(') {
                stack.push(s.charAt(index));
                ret *= 2;
            }
            else if (s.charAt(index) == '[') {
                stack.push(s.charAt(index));
                ret *= 3;
            }
            else {
                if(s.charAt(index) == ')') {
                    if(stack.isEmpty() || stack.peek() != '(') {
                        impossible = true;
                        break;
                    }
                    else {
                        if(s.charAt(index-1) == '(') total += ret;
                        stack.pop();
                        ret /= 2;
                    }
                }
                else if (s.charAt(index) == ']') {
                    if(stack.isEmpty() || stack.peek() != '[') {
                        impossible = true;
                        break;
                    }
                    else {
                        if(s.charAt(index-1) == '[') total += ret;
                        stack.pop();
                        ret /= 3;
                    }
                }
            }
            index++;
        }
        if(impossible || !stack.isEmpty()) total = 0;
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
