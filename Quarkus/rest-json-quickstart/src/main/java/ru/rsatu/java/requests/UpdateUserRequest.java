package ru.rsatu.java.requests;

public class UpdateUserRequest {
    private String email;
    private boolean enable;
    private String id;
    private String login;
    private String password;
    private String role;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String email, boolean enable, String id, String login, String password, String role) {
        this.email = email;
        this.enable = enable;
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
