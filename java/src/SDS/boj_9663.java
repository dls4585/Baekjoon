package SDS;

import java.io.*;
public class boj_9663 {
    static int[] cols;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        cols = new int[N];

        backtrack(0, N);

        bw.write(Integer.toString(count) + "\n");
        bw.flush();
        bw.close();
    }
    public static boolean check(int index) {
        for (int i = 0; i < index; i++) {
            if (cols[index] == cols[i] || index - i == Math.abs(cols[index] - cols[i])) { // 같은 열이거나, 대각선에 있거나(열의 차이가 행의 차이랑 같음)
                return false;
            }
        }
        return true;
    }

    public static void backtrack(int index, int N) {
        if(index == N)  {
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            cols[index] = i; // index행의 i열에 퀸을 놓아보기
            if(check(index)) { // 놓을 수 있다면
                backtrack(index + 1, N); // 다음 행으로 넘어간다.
            }
        }
    }
}
