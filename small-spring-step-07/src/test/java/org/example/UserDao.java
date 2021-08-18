package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @date : 2021/8/17
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod() {
        System.out.println("invoke :userDao init-method");
        hashMap.put("10001", "zhang san");
        hashMap.put("10002", "li si");
        hashMap.put("10003", "wang wu");
    }

    public void destroyMethod() {
        System.out.println("invoke userDao destroy-method");
        hashMap.clear();
    }


    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
