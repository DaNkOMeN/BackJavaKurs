package ru.rsatu.java.requests;

public class UserRequest {
    private String login;
    private String password;
    private String email;
    private String role;
    private boolean isBlock;


    public UserRequest() {
    }

    public UserRequest(String login, String password, String email, String role, boolean isBlock) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isBlock = isBlock;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }
}
