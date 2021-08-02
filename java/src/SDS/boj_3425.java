package SDS;

import java.io.*;
import java.util.*;

public class boj_3425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<String> commands = new ArrayList<>();
        boolean errorFlag = false;
        long max = 1000000000;
        while(true) {
            String s = br.readLine();
            if(s.equals("")) continue;
            if(s.equals("QUIT")) break;
            if(s.equals("END")) {
                int N = Integer.parseInt(br.readLine());
                for (int i = 0; i < N; i++) {
                    errorFlag = false;
                    Stack<Long> ss = new Stack<>();
                    long a = Long.parseLong(br.readLine());
                    ss.push(a);
                    for(String command : commands) {
                        StringTokenizer st = new StringTokenizer(command);
                        String op = st.nextToken();
                        if(op.equals("DUP")) {
                            if(ss.empty()) {
                                errorFlag = true;
                                break;
                            }
                            long dup = ss.peek();
                            ss.push(dup);
                        } else if (command.equals("MUL")) {
                            if(ss.size() < 2) {
                                errorFlag = true;
                                break;
                            }
                            long first = ss.pop();
                            long second = ss.pop();
                            long res = first * second;
                            if(Math.abs(res) > max) {
                                errorFlag = true;
                                break;
                            }
                            ss.push(res);
                        } else if (op.equals("NUM")) {
                            long operand = Long.parseLong(st.nextToken());
                            ss.push(operand);
                        } else if (op.equals("ADD")) {
                            if(ss.size() < 2) {
                                errorFlag = true;
                                break;
                            }
                            long first = ss.pop();
                            long second = ss.pop();
                            long res = first + second;
                            if(Math.abs(res) > max) {
                                errorFlag = true;
                                break;
                            }
                            ss.push(res);
                        } else if (op.equals("POP")) {
                            if(ss.empty()) {
                                errorFlag = true;
                                break;
                            }
                            ss.pop();
                        } else if (op.equals("INV")) {
                            if (ss.empty()) {
                                errorFlag = true;
                                break;
                            }
                            long pop = ss.pop();
                            long res = -1 * pop;
                            if(Math.abs(res) > max) {
                                errorFlag = true;
                                break;
                            }
                            ss.push(res);
                        } else if (op.equals("SWP")) {
                            if (ss.size() < 2) {
                                errorFlag = true;
                                break;
                            }
                            long first = ss.pop();
                            long second = ss.pop();
                            ss.push(first);
                            ss.push(second);
                        } else if(op.equals("SUB")) {
                            if (ss.size() < 2) {
                                errorFlag = true;
                                break;
                            }
                            long first = ss.pop();
                            long second = ss.pop();
                            long res = second - first;
                            if(Math.abs(res) > max) {
                                errorFlag = true;
                                break;
                            }
                            ss.push(res);
                        } else if(op.equals("DIV")) {
                            if(ss.size() < 2) {
                                errorFlag = true;
                                break;
                            }
                            long first = ss.pop();
                            long second = ss.pop();
                            if(first == 0) {
                                errorFlag = true;
                                break;
                            }
                            long res = second / first;
                            if(Math.abs(res) > max) {
                                errorFlag = true;
                                break;
                            }
                            ss.push(res);
                        } else if (op.equals("MOD")) {
                            if(ss.size() < 2) {
                                errorFlag = true;
                                break;
                            }
                            long first = ss.pop();
                            long second = ss.pop();
                            if(first == 0) {
                                errorFlag = true;
                                break;
                            }
                            long res = second % first;
                            if(Math.abs(res) > max) {
                                errorFlag = true;
                                break;
                            }
                            ss.push(res);
                        }
                    }
                    if(errorFlag || ss.size() != 1) {
                        bw.write("ERROR\n");
                    }
                    else {
                        bw.write(Long.toString(ss.pop()) + "\n");
                    }
                }
                bw.write("\n");
                commands.clear();
            }
            else {
                commands.add(s);
            }
        }
        bw.flush();
        bw.close();
    }
}
