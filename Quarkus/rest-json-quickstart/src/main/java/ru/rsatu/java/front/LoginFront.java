package ru.rsatu.java.front;

import ru.rsatu.java.beans.LoginRequest;
import ru.rsatu.java.beans.LoginResponce;
import ru.rsatu.java.beans.RegisterRequest;
import ru.rsatu.java.services.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/login")
public class LoginFront {


    @Inject
    LoginService loginService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String testHello(){
        return "Hello";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signUp")
    public Response singUp(RegisterRequest request){
        if (loginService.addNewUser(request)){
            return  Response.status(Response.Status.ACCEPTED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Response test(Request request){
        System.out.println(request);
        return Response.ok(request).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/signIn")
    public Response singIn(LoginRequest request){
        LoginResponce loginResponce;
        if ((loginResponce = loginService.getUser(request)) != null){
            return Response.ok(loginResponce).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
