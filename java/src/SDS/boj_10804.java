package SDS;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_10804 {
    public static void main(String[] args) throws IOException {
        int[] A= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Integer[] arr = Arrays.stream(A).boxed().toArray(Integer[]::new);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 10; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            rangeSwap(arr, start - 1, end - 1);
        }
        for (int i : arr) {
            bw.write(i + " ");
        }
        bw.flush();
    }

    public static void rangeSwap(Integer[] A, int start, int end) {
        int tempStart = start, tempEnd = end;
        while(tempStart < tempEnd) {
            Collections.swap(Arrays.asList(A), tempStart, tempEnd);
            tempStart++; tempEnd--;
        }
    }
}
