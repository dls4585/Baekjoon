import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class boj_1339 {
    static String[] words;
    static int[] numbers = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static int[] alphabets = new int[26];
    static int[] aCount = new int[26];
    static int index = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        words = new String[N];
        Arrays.fill(aCount, 0);
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j++) {
                aCount[words[i].charAt(j) - 'A']++;
            }
        }

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int diff = o2.length() - o1.length();
                if (diff == 0) {
                    int i = 0, j = 0;
                    int ret = aCount[o2.charAt(i++) - 'A'] - aCount[o1.charAt(j++) - 'A'];
                    while(ret == 0 && i < o2.length() && j < o1.length()) {
                        ret = aCount[o2.charAt(i++) - 'A'] - aCount[o1.charAt(j++) - 'A'];
                    }
                    return ret;
                }
                return 0;
            }
        });
        Arrays.fill(alphabets, -1);
        System.out.println(Arrays.toString(words) + "\n");

        char[][] wordsArray = new char[N][words[0].length()];
        for (int i = 0; i < N; i++) {
            int n = words[i].length() - 1;
            for (int j = wordsArray[i].length - 1; j >= 0; j--) {
                if (n < 0) {
                    wordsArray[i][j] = '0';
                } else {
                    wordsArray[i][j] = words[i].charAt(n--);
                }
            }
        }
        int[][] intArray = new int[N][words[0].length()];
        for (int i = 0; i < wordsArray[0].length; i++) {
            for (int j = 0; j < wordsArray.length; j++) {
                if(wordsArray[j][i] == '0') {
                    intArray[j][i] = -1;
                }
                else {
                    if(alphabets[wordsArray[j][i] - 'A'] == -1) {
                        alphabets[wordsArray[j][i] - 'A'] = index++;
                    }
                    intArray[j][i] = numbers[alphabets[wordsArray[j][i] - 'A']];
                }
            }
        }
        int[] arr = new int[N];
        int ret = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = makeInt(intArray[i]);
            ret += arr[i];
        }
        bw.write(Integer.toString(ret) + "\n");
        bw.flush();
        bw.close();
    }
    public static int makeInt(int[] arr) {
        int n = arr.length - 1;
        int ret = 0;
        for (int i = 0; i < arr.length; i++, n--) {
            if(arr[i] == -1) {
                continue;
            }
            else {
                ret += arr[i] * Math.pow(10, n);
            }
        }
        return ret;
    }
}
