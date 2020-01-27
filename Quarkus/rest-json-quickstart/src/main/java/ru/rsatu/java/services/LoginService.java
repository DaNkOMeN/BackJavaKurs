package ru.rsatu.java.services;

import ru.rsatu.java.requests.LoginRequest;
import ru.rsatu.java.requests.UserRequest;
import ru.rsatu.java.requests.UserResponse;
import ru.rsatu.java.tables.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class LoginService {

    @Transactional
    public UserResponse registration(UserRequest request) {
        User user = new User(request.getLogin(), request.getPassword(), request.getEmail(), request.getRole(), request.isBlock());
        user.persist();
        UserResponse response = new UserResponse(request.getLogin(), request.getRole());
        return response;
    }

    @Transactional
    public UserResponse login(LoginRequest request){
        User findUser = User.find("email", request.getEmail()).firstResult();
        System.out.println(request.getEmail());
        System.out.println(findUser.getLogin());
        UserResponse response = null;
        if (findUser != null){
            if (findUser.getPassword().equals(request.getPassword())){
                response = new UserResponse(findUser.getLogin(), findUser.getRole());
                System.out.println("Юзер найден");
            } else {
                System.out.println("Неправильный пароль");
            }
        } else {
            System.out.println("Такого пользователя нет");
        }

        return response;
    }
}
