/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme.rest.json;

/**
 *
 * @author skale
 */
public class User extends RegRquest{
    public String token;
    public int ID;
    

    @Override
    public boolean isEnable() {
        return enable;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public User(){
}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
@Override
    public String getLogin() {
        return login;
    }
@Override
    public void setLogin(String login) {
        this.login = login;
    }
@Override
    public String getEmail() {
        return email;
    }
@Override
    public void setEmail(String email) {
        this.email = email;
    }
@Override
    public String getPassword() {
        return password;
    }
@Override
    public void setPassword(String password) {
        this.password = password;
    }
@Override
    public String getGrup() {
        return grup;
    }
@Override
    public void setGrup(String grup) {
        this.grup = grup;
    }
@Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }
}
