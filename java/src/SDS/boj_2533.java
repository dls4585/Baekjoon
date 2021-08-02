package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2533 {
    static int[][] memo;
    static boolean[][] check;
    static Person[] ps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        check = new boolean[N+1][2];
        memo = new int[N+1][2];

        ps = new Person[N + 1];
        for (int i = 0; i < N; i++) {
            ps[i+1] = new Person(i+1);
        }
        for (int i = 0; i < N - 1; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            ps[A].link(ps[B]);
            ps[B].link(ps[A]);
        }

        for (int[] ints : memo) {
            Arrays.fill(ints, 0);
        }
        for (boolean[] bools : check) {
            Arrays.fill(bools, false);
        }
        int ret = Math.min(dp(1, 0, 1, 0), dp(1, 1, 1, 1));
        bw.write(Integer.toString(ret) + "\n");
        bw.flush();
        bw.close();
    }

    public static int dp(int x, int c, int mx, int mc) {
        if(check[x][c]) return memo[x][c];
        check[x][c] = true;
        if(ps[x].e.size() == 1) { // only has parent
            if(mc == 0) return memo[x][c] = 1;
        }
        for (Person y: ps[x].e) {
            if(y.n == mx) continue;
            if(c == 0) memo[x][c] += dp(y.n, 1, x, 0) + 1;
            else memo[x][c] += Math.min(dp(y.n, 1, x, 1) + 1, dp(y.n, 0, x, 1));
        }
        return memo[x][c];
    }
}

class Person {
    int n;
    List<Person> e;

    Person(int n) {
        this.n = n;
        e = new ArrayList<>();
    }

    public void link(Person B) {
        this.e.add(B);
    }
}
