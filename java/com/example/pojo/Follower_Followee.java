package com.example.pojo;

/**
 * @author xiaojin
 * @version 1.0
 */
public class Follower_Followee {
    private String Follower;
    private String Followee;

    public String getFollower() {
        return Follower;
    }

    public void setFollower(String follower) {
        Follower = follower;
    }

    public String getFollowee() {
        return Followee;
    }

    public void setFollowee(String followee) {
        Followee = followee;
    }

    @Override
    public String toString() {
        return "Follower_Followee{" +
                "Follower='" + Follower + '\'' +
                ", Followee='" + Followee + '\'' +
                '}';
    }
}
