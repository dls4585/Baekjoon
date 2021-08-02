package SDS;

import java.io.*;
import java.util.*;

public class boj_14003 {
    static int[] arr, index; // index[i] : arr[i]가 LIS에 들어간 위치
    static List<Integer> LIS = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        index = new int[N];

        index[0] = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(N == 1) {
            bw.write("1\n" + arr[0]);
            bw.flush();
            bw.close();
            return;
        }

        LIS.add(arr[0]);
        index[0] = 0;

        for (int i = 1; i < N; i++) {
            if (arr[i] > LIS.get(LIS.size() - 1)) {
                LIS.add(arr[i]);
                index[i] = LIS.size() - 1;
            }
            else {
                binarySearch(i);
            }
        }

        int size = LIS.size() - 1;
        sb.append((size + 1)).append("\n");
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; size >= 0 && i >= 0; i--) {
            if(index[i] == size) {
                stack.push(arr[i]);
                size--;
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
    static void binarySearch(int id) {
        int start, end, mid;
        start = 0;
        end = LIS.size() - 1;

        while(end > start) {
            mid = (start + end) / 2;
            if(LIS.get(mid) >= arr[id]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        LIS.set(start, arr[id]);
        index[id] = start;
    }
}
