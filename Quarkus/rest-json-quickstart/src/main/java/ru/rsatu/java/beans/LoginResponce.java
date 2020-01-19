package ru.rsatu.java.beans;

public class LoginResponce {

    private String userName;
    private String token;
    private String role;

    public LoginResponce(String userName, String token, String role) {
        this.userName = userName;
        this.token = token;
        this.role = role;
    }

    public LoginResponce() {
        this.userName = "default";
        this.token = "default";
        this.role = "student";
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
