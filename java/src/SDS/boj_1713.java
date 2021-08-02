package SDS;

import java.io.*;
import java.util.*;

public class boj_1713 {
    static Candidate[] students;
    static List<Candidate> frames;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        frames = new ArrayList<>();
        students = new Candidate[101];
        Arrays.fill(students, null);
        int L = Integer.parseInt(br.readLine());
        s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for (int i = 0; i < L; i++) {
            int l = Integer.parseInt(st.nextToken());
            if(students[l] == null) { // 처음 추천 받은 사람
                students[l] = new Candidate(l, i, 0, false);
            }
            if(!students[l].in){
                if(frames.size() == N) {
                    Collections.sort(frames);
                    for (int j = 0; j < frames.size(); j++) {
                        frames.get(j).frameIndex = j;
                    }
                    Candidate c = frames.get(N - 1);
                    c.fresh = -1;
                    c.in = false;
                    c.frameIndex = -1;
                    c.count = 0;
                    frames.remove(N - 1);
                }
                frames.add(students[l]);
                students[l].frameIndex = frames.size() - 1;
                students[l].count++;
                students[l].in = true;
                students[l].fresh = 0;
            }
            else {
                students[l].count++;
            }
            frames.forEach(o -> o.fresh++);
        }
        frames.sort(new Comparator<Candidate>() {
            @Override
            public int compare(Candidate o1, Candidate o2) {
                return o1.number - o2.number;
            }
        });
        frames.forEach(o1->{
            try {
                bw.write(Integer.parseInt(String.valueOf(o1.number)) + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.write("\n");
        bw.flush();
        bw.close();
    }
}

class Candidate implements Comparable<Candidate>{
    int number;
    int frameIndex;
    int count;
    int fresh;
    boolean in;

    Candidate(int number, int frameIndex, int count, boolean in) {
        this.number = number;
        this.frameIndex = frameIndex;
        this.fresh = 0;
        this.count = count;
        this.in = in;
    }

    @Override
    public int compareTo(Candidate o) {
        if(this.count > o.count) return -1;
        else if(this.count == o.count) {
            if(this.fresh > o.fresh) return 1;
            else return -1;
        }
        return 0;
        // -1 ㅂㅏ꾸지 않음
        // 0 같으니까 바꾸지 않음
        // 1 바
    }
}
