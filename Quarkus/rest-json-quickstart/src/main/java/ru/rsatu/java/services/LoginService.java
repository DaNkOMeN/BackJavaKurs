package ru.rsatu.java.services;


import ru.rsatu.java.beans.LoginRequest;
import ru.rsatu.java.beans.LoginResponce;
import ru.rsatu.java.beans.RegisterRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ApplicationScoped
public class LoginService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public boolean addNewUser(RegisterRequest request) {
        RegisterRequest testLogin = RegisterRequest.find("login", request.getLogin()).firstResult();
        RegisterRequest testEmail = RegisterRequest.find("email", request.getEmail()).firstResult();
        if (testLogin != null) return false;
        if (testEmail != null) return false;
        entityManager.persist(request);
        System.out.println("Добавлен");
        return true;
    }

    @Transactional
    public LoginResponce getUser(LoginRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("login", request.getLogin());
        params.put("password", request.getPassword());
        RegisterRequest result = RegisterRequest.find("login = :login and password = :password", params).firstResult();
        if (result != null) {

            LoginResponce responce = new LoginResponce(result.getLogin(),
                    createToken(result.getLogin(), result.getPassword()), result.getRole());

            return responce;
        } return null;
    }

    private String createToken(String login, String password){
        return login + password;
    }
}
