package com.example.pojo;

/**
 * @author xiaojin
 * @version 1.0
 */
public class User_Article {
    private String userName;
    private String id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User_Article{" +
                "userName='" + userName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
