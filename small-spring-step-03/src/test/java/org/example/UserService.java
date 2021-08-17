package org.example;

/**
 *
 * @date : 2021/8/16
 */
public class UserService {

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo(){
        System.out.println("Query User Info.");
    }
}
