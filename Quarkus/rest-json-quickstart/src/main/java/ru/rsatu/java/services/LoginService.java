package ru.rsatu.java.services;




import ru.rsatu.java.beans.LoginRequest;
import ru.rsatu.java.beans.LoginResponce;
import ru.rsatu.java.beans.RegisterRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import javax.xml.bind.DatatypeConverter;

@ApplicationScoped
public class LoginService {

    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

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
                    createToken(String.valueOf(result.id)), result.getRole());

            return responce;
        }
        System.out.println("Такого нет");
        return null;
    }

    private String createToken(String id){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).signWith(signatureAlgorithm, signingKey);
        System.out.println(builder);
        return builder.compact();
    }
}
