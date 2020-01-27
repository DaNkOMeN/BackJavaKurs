package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rest/doRegistration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author skale
 */
public class RegResorce {
    Set<User> users = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    int id=0;
    @POST
    public LoginResponce doRegistration(RegRquest req) {
        LoginResponce responce = new LoginResponce();
        User newUser = new User();
        newUser.email = req.email;
        newUser.grup = req.grup;
        newUser.role = req.role;
        newUser.enable = req.enable;
        System.out.println(req.role);
        if("3".equals(req.role)) newUser.grup = "";
        newUser.login = req.login;
        newUser.password = req.password;
        newUser.ID=id;
        id++;
        newUser.token = ""+newUser.hashCode();
        responce.token = newUser.token;
        responce.userName = newUser.login;
        users.add(newUser);
        System.out.println("/t/t/t/t/t Пришел запрос на регистрацию "+users);
        return responce;
    }
    
    @GET
    public Set<User> getUsers() {
        System.out.println("Просят пользователей "+users);
        return users;
    }
}
