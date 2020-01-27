package ru.rsatu.java.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends PanacheEntity implements Serializable {

    @Column(name = "login", unique = true, nullable = false, length = 100)
    private String login;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 25)
    private String email;

    @Column(name = "role",  nullable = false, length = 25)
    private String role;

    @Column(name = "isBlock", nullable = false)
    private boolean isBlock;

    @OneToMany(mappedBy = "user" ,  cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private Set<Pair> pairsOfTeacher = new HashSet<Pair>();

    public User(String login, String password, String email, String role, boolean isBlock) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isBlock = isBlock;
    }

    public User() {
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

    public Set<Pair> getPairsOfTeacher() {
        return pairsOfTeacher;
    }

    public void setPairsOfTeacher(Set<Pair> pairsOfTeacher) {
        this.pairsOfTeacher = pairsOfTeacher;
    }

}


