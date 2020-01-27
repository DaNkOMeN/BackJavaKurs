package org.acme.rest.json;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/rest/doLogin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author skale
 */
public class RestResorce {
       
    @POST
    public LoginResponce doLogin(LoginRequest req) {
        LoginResponce responce = new LoginResponce();
        responce.SetLogin(req.GetLogin());
        responce.SetToken(req.getPassword()+"!!!");
        System.out.println("/t/t/t/t/t Пришел запрос на логин ");
        return responce;
    }

    @GET
    public String something(){
        return "Hello hello";
    }
}
