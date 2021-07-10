import java.io.*;
import java.util.Stack;

public class boj_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            String s = br.readLine();
            if(s.equals(".")) break;
            boolean flag = true;
            Stack<Character> ss = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '(' || s.charAt(i) == '[') {
                    ss.push(s.charAt(i));
                }
                else if(s.charAt(i) == ')') {
                    if(!ss.empty()) {
                        if (ss.peek() == '(') {
                            ss.pop();
                        }
                        else if (ss.peek() == '[') {
                            bw.write("no\n");
                            flag = false;
                            break;
                        }
                    }
                    else {
                        bw.write("no\n");
                        flag = false;
                        break;
                    }
                }
                else if(s.charAt(i) == ']') {
                    if(!ss.empty()) {
                        if (ss.peek() == '[') {
                            ss.pop();
                        }
                        else if(ss.peek() == '(') {
                            bw.write("no\n");
                            flag = false;
                            break;
                        }
                    }
                    else {
                        bw.write("no\n");
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) {
                if(ss.size() > 0) {
                    bw.write("no\n");
                    flag = false;
                }
                else {
                    bw.write("yes\n");

                }
            }
        }
        bw.flush();
        bw.close();
    }
}
