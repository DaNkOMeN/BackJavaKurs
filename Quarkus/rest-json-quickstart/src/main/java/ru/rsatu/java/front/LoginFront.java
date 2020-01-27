package ru.rsatu.java.front;


import ru.rsatu.java.requests.*;
import ru.rsatu.java.services.LoginService;
import ru.rsatu.java.services.MainService;
import ru.rsatu.java.tables.Housing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/rest/login")
public class LoginFront {

    @Inject
    LoginService loginService;

    @POST
    @Path("/doRegistration")
    public Response doRegistration(UserRequest request){
        return Response.ok(loginService.registration(request)).build();
    }

    @POST
    @Path("/doLogin")
    public Response doLogin(LoginRequest request){
        return Response.ok(loginService.login(request)).build();
    }

}
