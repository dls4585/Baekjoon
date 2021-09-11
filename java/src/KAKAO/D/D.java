package KAKAO.D;

import java.util.*;

class Solution {
    static int maxGap = -1;
    static int[] answer = new int[11];
    public int[] solution(int n, int[] info) {
        int[] temp = new int[11];
        backtrack(info, temp, 10, 0, n);
        if(maxGap <= 0) {
            answer = new int[]{-1};
        }
        return answer;
    }
    static void backtrack(int[] info, int[] temp, int target, int count, int n) {
        if(count > n) return;
        if(count == n) {
            // calculate score
            int rion = 0, apeach = 0;
            for (int i = 0; i < 11; i++) {
                if(info[i] != 0 || temp[i] != 0) {
                    if(info[i] < temp[i]) {
                        rion += 10 - i;
                    } else {
                        apeach += 10 - i;
                    }
                }
            }
            if(maxGap < rion - apeach) {
                maxGap = rion - apeach;
                System.arraycopy(temp, 0, answer, 0, 11);
            }
            return;
        }
        for (int i = target; i >= 0; i--) {
            temp[i]++;
            backtrack(info, temp, i, count + 1, n);
            temp[i]--;
        }
    }
}
public class D {
    public static void main(String[] args) {
        int[] info = {0, 0, 0, 0, 0, 0, 0, 1, 3, 3, 3};
        int n = 10;
        Solution s = new Solution();
        int[] result = s.solution(n, info);
        System.out.println(Arrays.toString(result));
    }
}
