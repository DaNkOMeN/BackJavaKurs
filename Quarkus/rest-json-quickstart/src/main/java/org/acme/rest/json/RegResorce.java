package org.acme.rest.json;

import java.util.List;
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
    List<User> users;
    
    @POST
    public LoginResponce doRegistration(User req) {
        LoginResponce responce = new LoginResponce();
        User newUser = req;
        newUser.token = ""+newUser.hashCode();
        responce.token = newUser.token;
        responce.userName = newUser.login;
        users.add(newUser);
        System.out.println("/t/t/t/t/t Пришел запрос на регистрацию "+users);
        return responce;
    }
}
