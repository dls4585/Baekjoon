package KAKAO.B;

class Solution {
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        makeKJinsu(n, k, sb);
        String kJinsu = sb.reverse().toString();
        int len = kJinsu.length();
        sb = new StringBuilder();
        int answer = 0;
        for (int i = 0; i < len; i++) {
            if(sb.length() == 0) {
                if (kJinsu.charAt(i) != '0') {
                    sb.append(kJinsu.charAt(i));
                }
            } else {
                if(kJinsu.charAt(i) == '0') {
                    if(isPrime(Long.parseLong(sb.toString()))) {
                        answer++;
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(kJinsu.charAt(i));
                }
            }
        }
        if(sb.length() != 0) {
            if(isPrime(Long.parseLong(sb.toString()))) {
                answer++;
            }
        }
        return answer;
    }
    static boolean isPrime(long a) {
        if(a == 1) return false;
        for (long i = 2; i*i <= a; i++) {
            if(a % i == 0) return false;
        }
        return true;
    }
    static void makeKJinsu(int n, int k, StringBuilder sb) {
        for (int i = n; i > 0;) {
            sb.append(i % k);
            i = i / k;
        }
    }
}

public class B {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(437674, 3));
    }
}
