package org.acme.rest.json;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        return responce;
    }
}
