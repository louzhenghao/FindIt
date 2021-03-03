package com.example.findit.entity;

public class User {

    private Integer user_id;
    private String user_nickname;
    private String user_password;
    private String user_headportrait;
    private String user_sex;
    private String user_phonenumber;
    private String user_address;
    private String user_email;
    private Integer old;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_headportrait() {
        return user_headportrait;
    }

    public void setUser_headportrait(String user_headportrait) {
        this.user_headportrait = user_headportrait;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_phonenumber() {
        return user_phonenumber;
    }



    public void setUser_phonenumber(String user_phonenumber) {
        this.user_phonenumber = user_phonenumber;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }


    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public User(Integer user_id, String user_nickname, String user_password, String user_headportrait, String user_sex, String user_phonenumber, String user_address, String user_email, Integer old) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_password = user_password;
        this.user_headportrait = user_headportrait;
        this.user_sex = user_sex;
        this.user_phonenumber = user_phonenumber;
        this.user_address = user_address;
        this.user_email = user_email;
        this.old = old;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_headportrait='" + user_headportrait + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_phonenumber='" + user_phonenumber + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_email='" + user_email + '\'' +
                ", old=" + old +
                '}';
    }
}
