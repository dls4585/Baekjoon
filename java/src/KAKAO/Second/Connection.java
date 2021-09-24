package KAKAO.Second;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {
    public static Connection instance = null;

    public static Connection getInstance() {
        if(instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public Map<String, Object> start (String X_AUTH_KEY, int problem) {
        JSONObject responseJson = null;
        Map<String, Object> ret = null;
        try {
            URL url = new URL(GlobalData.HOST_URL+"/start");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-Auth-Token", X_AUTH_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);


            HashMap<String, Object> requstMap = new HashMap<>();
            // body에 데이터 설정
            requstMap.put("problem", problem);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(requstMap);
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            os.flush();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                conn.disconnect();
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(sb.toString());
                responseJson = (JSONObject) obj;
                ret = getMapFromJsonObject(responseJson);
            } else {
                ret = new HashMap<>();
                ret.put("response", responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Location> locations() {
        JSONObject responseJson = null;
        List<Location> ret = null;
        try {
            URL url = new URL(GlobalData.HOST_URL+"/locations");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", TokenManager.getInstance().getAuthKey());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int responseCode = conn.getResponseCode();

            if(responseCode == 400) {
                System.out.println("400:: problem_id 또는 number_of_elevators의 형식 또는 범위가 잘못됨");
            } else if(responseCode == 401) {
                System.out.println("401:: X-Auth-Token Header가 잘못됨");
            } else if(responseCode == 403) {
                System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
            } else if(responseCode == 500) {
                System.out.println("500:: 서버 에러, 문의 필요");
            } else if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                conn.disconnect();
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(sb.toString());
                responseJson = (JSONObject) obj;
                ret = new ObjectMapper().readValue(responseJson.get("locations").toString(), new TypeReference<>() {});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Truck> trucks() {
        JSONObject responseJson = null;
        List<Truck> ret = null;
        try {
            URL url = new URL(GlobalData.HOST_URL+"/trucks");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", TokenManager.getInstance().getAuthKey());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int responseCode = conn.getResponseCode();

            if(responseCode == 400) {
                System.out.println("400:: problem_id 또는 number_of_elevators의 형식 또는 범위가 잘못됨");
            } else if(responseCode == 401) {
                System.out.println("401:: X-Auth-Token Header가 잘못됨");
            } else if(responseCode == 403) {
                System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
            } else if(responseCode == 500) {
                System.out.println("500:: 서버 에러, 문의 필요");
            } else if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                conn.disconnect();
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(sb.toString());
                responseJson = (JSONObject) obj;
                ret = new ObjectMapper().readValue(responseJson.get("trucks").toString(), new TypeReference<>() {});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Map<String, Object> simulate(List<Map<String, Object>> commands) {
        JSONObject responseJson = null;
        Map<String, Object> ret = null;
        try {
            URL url = new URL(GlobalData.HOST_URL+"/simulate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Authorization", TokenManager.getInstance().getAuthKey());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            // body에 데이터 설정
            Map<String, Object> req = new HashMap<>();
            req.put("commands", commands);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(req);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();

            int responseCode = conn.getResponseCode();
            if(responseCode == 502) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                conn.disconnect();
                System.out.println(sb.toString());
            }
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                conn.disconnect();
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(sb.toString());
                responseJson = (JSONObject) obj;
                ret = getMapFromJsonObject(responseJson);
            } else {
                ret = new HashMap<>();
                ret.put("response", responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Map<String, Object> score() {
        JSONObject responseJson = null;
        Map<String, Object> ret = null;
        try {
            URL url = new URL(GlobalData.HOST_URL+"/score");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", TokenManager.getInstance().getAuthKey());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                conn.disconnect();
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(sb.toString());
                responseJson = (JSONObject) obj;
                ret = getMapFromJsonObject(responseJson);
            } else {
                ret = new HashMap<>();
                ret.put("response", responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject(JSONObject jsonObj) {
        Map<String, Object> map = null;

        try {

            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class);

        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

}