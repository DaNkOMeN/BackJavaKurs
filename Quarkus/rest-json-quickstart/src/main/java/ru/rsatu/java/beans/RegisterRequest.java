package ru.rsatu.java.beans;


import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "users")
public class RegisterRequest extends PanacheEntity {

    private String login;
    private String password;
    private String email;
    private String role;

    public RegisterRequest(String login, String password, String email, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public RegisterRequest() {
        this.login = "default";
        this.password = "default";
        this.email = "default";
        this.role = "student";
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
}
