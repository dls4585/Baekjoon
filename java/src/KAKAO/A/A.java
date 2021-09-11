package KAKAO.A;

import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, ID> ids = new HashMap<>(id_list.length);
        List<String> stopped = new ArrayList<>();
        for (String name : id_list) {
            ids.put(name, new ID(name));
        }
        for (String line : report) {
            StringTokenizer st = new StringTokenizer(line);
            ID who = ids.get(st.nextToken());
            ID whom = ids.get(st.nextToken());
            if(!who.report.containsKey(whom.name)) {
                who.report.put(whom.name, whom);
                whom.reported++;
                if(!whom.stopped && whom.reported >= k) {
                    whom.stopped = true;
                    stopped.add(whom.name);
                }
            }
        }
        for (String name : stopped) {
            ids.forEach((key, v) -> {
                if(v.report.containsKey(name)) v.mails++;
            });
        }
        int[] answer = new int[id_list.length];
        int i = 0;
        for (String name : id_list) {
            answer[i++] = ids.get(name).mails;
        }
        return answer;
    }
    static class ID {
        String name;
        Map<String, ID> report;
        boolean stopped;
        int mails, reported;

        public ID(String name) {
            this.name = name;
            report = new HashMap<>();
            stopped = false;
            reported = mails = 0;

        }
    }
}


public class A {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] id_list = {"con", "ryan"};
        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};

        System.out.println(Arrays.toString(s.solution(id_list, report, 3)));
    }
}