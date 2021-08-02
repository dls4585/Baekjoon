package SDS;

import java.io.*;
import java.util.*;

public class boj_10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            students.add(new Student(st.nextToken(), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(students);
        students.forEach(student -> {
            try {
                bw.write(student.name + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.flush();
        bw.close();
    }
}

class Student implements Comparable<Student>{
    String name;
    int K;
    int E;
    int M;
    Student (String name, int K, int E, int M) {
        this.name = name;
        this.K = K;
        this.E = E;
        this.M = M;
    }

    @Override
    public int compareTo(Student o) {
        if(this.K < o.K) {
            return 1;
        } else if (this.K == o.K) {
            if (this.E > o.E) {
                return 1;
            } else if (this.E == o.E) {
                if(this.M < o.M) {
                    return 1;
                } else if(this.M == o.M) {
                    return this.name.compareTo(o.name);
                }
            }
        }
        return -1;
    }
}