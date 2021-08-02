package SDS;

import java.io.*;

public class boj_5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        s = br.readLine();
        int M = Integer.parseInt(s);
        int targetI = N+1;
        int targetO = N;
        s = br.readLine();
        int Is = 0, Os = 0, count = 0;
        char previous = 0;
        for (int i = 0; i < M; i++) {
            if(Is == targetI && Os == targetO) {
                Is--;
                Os--;
                i--;
                previous = s.charAt(i);
                count++;
                continue;
            }
            if (Is == 0) {
                if(s.charAt(i) == 'O') continue;
                else{
                    previous = s.charAt(i);
                    Is++;
                }
            }
            else {
                if(previous == 'O') {
                    if(s.charAt(i) == 'I') {
                        previous = s.charAt(i);
                        Is++;
                    }
                    else {
                        Is = 0;
                        Os = 0;
                        previous = 0;
                    }
                } else {
                    if(s.charAt(i) == 'O') {
                        previous = s.charAt(i);
                        Os++;
                    }
                    else {
                        previous = s.charAt(i);
                        Is = 1;
                        Os = 0;
                    }
                }
            }
        }
        bw.write(Integer.toString(count) + "\n");
        bw.flush();
        bw.close();
    }
}
