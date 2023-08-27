package com.example.pojo;

/**
 * @author xiaojin
 * @version 1.0
 */
public class User_Detail {
    private String userName;
    private String introduce;
    private String sex;
    private String birthday;

    private String administrator;

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User_Detail{" +
                "userName='" + userName + '\'' +
                ", introduce='" + introduce + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", administrator='" + administrator + '\'' +
                '}';
    }
}
