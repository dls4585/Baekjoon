package KAKAO.Second;

import java.util.Map;

public class TokenManager {
    public static TokenManager instance;
    private String authKey = "";
    private static final String X_AUTH_TOKEN = "64cd80f66d5cc5704650018a7457768f";

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public String getX_AUTH_TOKEN() { return X_AUTH_TOKEN; }

    public String getAuthKey() {
        return this.authKey;
    }

    public synchronized int start(int problemId) {
        String authKey = null;
        Map<String, Object> response = Connection.getInstance().start(X_AUTH_TOKEN, problemId);
        int responseCode = 200;
        if(response.containsKey("response")) {
            responseCode = (int) response.get("response");
            if(responseCode == 400) {
                System.out.println("400:: problem_id 또는 number_of_elevators의 형식 또는 범위가 잘못됨");
            } else if(responseCode == 401) {
                System.out.println("401:: X-Auth-Token Header가 잘못됨");
            } else if(responseCode == 403) {
                System.out.println("403:: user_key가 잘못되었거나 10초 이내에 생성한 토큰이 존재");
                authKey = this.authKey;
            } else if(responseCode == 500) {
                System.out.println("500:: 서버 에러, 문의 필요");
            }
        } else {
            authKey = (String) response.get("auth_key");
        }
        this.authKey = authKey;

        return responseCode;
    }
}
