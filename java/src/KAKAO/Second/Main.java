package KAKAO.Second;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        int problemId = 1;

        start(problemId);
        int time = 0;
        while(true) {
            System.out.println("===============" + time + "===============");
            List<Location> locations = locations();
            List<Truck> trucks = trucks();

            List<Map<String, Object>> commands = new ArrayList<>();
            // maxPQ 대여요청 별로 없는 곳
            PriorityQueue<Location> maxPQ = new PriorityQueue<>(Comparator.comparingInt(Location::getLocated_bikes_count).reversed());
            maxPQ.addAll(locations);
            PriorityQueue<Location> minPQ = new PriorityQueue<>(Comparator.comparingInt(Location::getLocated_bikes_count));
            minPQ.addAll(locations);

            for (Truck truck: trucks) {
                Map<String, Object> command = new HashMap<>();
                List<Integer> cList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    cList.add(0);
                }
                command.put("truck_id", truck.getId());
                command.put("command", cList);
                commands.add(command);
            }
            Map<String, Object> response = simulate(commands);
            boolean isEnd = false;
            if (response != null) {
                String status = (String) response.get("status");
                isEnd = status.equals("finished");
                if(isEnd) break;
                time++;
                try {
                    Thread.sleep(40); // 40 request per second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(score());

    }
    private static int start(int problemId) {
        System.out.println(">>>> api.start()");
        // start
        int response = TokenManager.getInstance().start(problemId);
        System.out.println("Token : " + TokenManager.getInstance().getAuthKey());
        return response;
    }

    private static List<Location> locations() throws JsonProcessingException {
        return Connection.getInstance().locations();
    }

    private static List<Truck> trucks() {
        return Connection.getInstance().trucks();
    }

    private static Map<String, Object> simulate (List<Map<String, Object>> commands) {
        Map<String, Object> response = Connection.getInstance().simulate(commands);
        if (response.containsKey("response")) {
            int responseCode = (int) response.get("response");
            System.out.println("In simulate() : " + responseCode);

            if(responseCode == 400) {
                System.out.println("400:: problem_id 또는 number_of_elevators의 형식 또는 범위가 잘못됨");
            } else if(responseCode == 401) {
                System.out.println("401:: X-Auth-Token Header가 잘못됨");
            } else if(responseCode == 403) {
                System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
            } else if(responseCode == 500) {
                System.out.println("500:: 서버 에러, 문의 필요");
            }
            return null;
        } else {
            return response;
        }
    }

    private static double score() {
        System.out.println(">>>> api.score()");
        Map<String, Object> response = Connection.getInstance().score();

        if (response.containsKey("response")) {
            int responseCode = (int) response.get("response");
            if(responseCode == 400) {
                System.out.println("400:: problem_id 또는 number_of_elevators의 형식 또는 범위가 잘못됨");
            } else if(responseCode == 401) {
                System.out.println("401:: X-Auth-Token Header가 잘못됨");
            } else if(responseCode == 403) {
                System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
            } else if(responseCode == 500) {
                System.out.println("500:: 서버 에러, 문의 필요");
            }
            return -1;
        } else {
            return (double) response.get("score");
        }

    }
}
