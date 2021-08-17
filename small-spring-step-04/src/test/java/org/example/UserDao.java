package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : landon
 * @date : 2021/8/17
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "zhang san");
        hashMap.put("10002", "li si");
        hashMap.put("10003", "wang wu");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
