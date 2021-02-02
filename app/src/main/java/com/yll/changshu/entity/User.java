package com.yll.changshu.entity;

public class User {
    private int userId;
    private String user_name;
    private String psw;
    private String phone_number;
    private String company_name;
    private int user_rank;
    private String user_email;

    public User() { }

    public User(int userId, String user_name, String psw, String phone_number, String company_name, int user_rank, String user_email) {
        this.userId = userId;
        this.user_name = user_name;
        this.psw = psw;
        this.phone_number = phone_number;
        this.company_name = company_name;
        this.user_rank = user_rank;
        this.user_email = user_email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(int user_rank) {
        this.user_rank = user_rank;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
