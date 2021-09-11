package KAKAO.C;

import java.util.*;

class Solution {
    static int baseTime, baseFee, unitTime, unitFee;
    public int[] solution(int[] fees, String[] records) {
        Comparator<String> comparator = String::compareTo;
        Map<String, Car> cars = new TreeMap<>(comparator);
        baseTime = fees[0];
        baseFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String number = st.nextToken();
            boolean isIn = st.nextToken().equals("IN");

            if(!cars.containsKey(number)) { // 무조건 첫 입차임
                cars.put(number, new Car(number, isIn, time));
            }
            else {
                Car car = cars.get(number);
                car.isIn = isIn;
                if(!isIn) { // 출차인 경우
                    car.time += calculateTime(car.inTime, time);
                } else {
                    car.inTime = time;
                }
            }
        }

        // 다 내보내기 23:59
        cars.forEach((k, v) -> {
            if(v.isIn) {
                v.time += calculateTime(v.inTime, "23:59");
                v.isIn = false;
            }
            v.fees = calculateFee(v.time);
        });
        int[] answer = new int[cars.size()];
        int i = 0;
        for (String name : cars.keySet()) {
            answer[i++] = cars.get(name).fees;
        }
        return answer;
    }
    static int calculateFee(int time) {
        if(time <= baseTime) return baseFee;
        int temp = time - baseTime;
        int unit = 0;
        if(temp % unitTime != 0) {
            unit = (temp / unitTime) + 1;
        } else {
            unit = temp / unitTime;
        }
        return baseFee + unit * unitFee;
    }
    static int calculateTime(String inTime, String outTime) {
        int[] iTemp = parseTime(inTime);
        int[] oTemp = parseTime(outTime);
        int iT = iTemp[0] * 60 + iTemp[1];
        int oT = oTemp[0] * 60 + oTemp[1];
        return oT - iT;
    }
    static int[] parseTime(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        return new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    }
    static class Car {
        String number;
        int time;
        int fees;
        boolean isIn;
        String inTime;

        public Car(String number, boolean isIn, String inTime) {
            this.number = number;
            this.isIn = isIn;
            this.inTime = inTime;
            time = 0;
            fees = 0;
        }
    }
}

public class C {
    public static void main(String[] args) {
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] fees = {180, 5000, 10, 600};
        Solution s = new Solution();
        s.solution(fees, records);
    }
}
