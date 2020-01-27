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
public class LoginResponce {
    public String userName;
    public String token;
    
    public void SetLogin(String log){
        this.userName=log;
    } 
    
    public void SetToken(String token){
        this.token=token;
    } 
    
    public LoginResponce(){
        this.userName = "NoName";
        this.token = "123";
    }
}
