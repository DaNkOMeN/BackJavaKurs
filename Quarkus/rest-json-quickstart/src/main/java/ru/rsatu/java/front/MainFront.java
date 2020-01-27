package ru.rsatu.java.front;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;

import java.io.*;
import java.net.ResponseCache;
import java.security.Key;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.rsatu.java.requests.*;
import ru.rsatu.java.services.MainService;
import ru.rsatu.java.tables.Groups;
import ru.rsatu.java.tables.Housing;

//import static jdk.nashorn.internal.objects.NativeError.getFileName;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/rest/main")
public class MainFront {


    @Inject
    MainService mainService;


    @POST
    @Path("/testPA")
    public Response addPair(PairRequest request){
        System.out.println("Пришли");
        return Response.ok(mainService.addPair(request)).build();
    }

    @POST
    @Path("/testUA")
    public Response addUser(UserRequest request){
        return Response.ok(mainService.addUser(request)).build();
    }

    @POST
    @Path("/testGA")
    public Response addGroup(GroupRequest request){
        List<Groups> list = mainService.addGroup(request);
        System.out.println(list.get(0).getNameOfGroup());
        return Response.ok(list).build();
    }

    @POST
    @Path("/testGD")
    public Response deleteGroup(GroupRequest request){
        return Response.ok( mainService.deleteGroup(request)).build();
    }


    @POST
    @Path("/testHA")
    public Response addHousing(HousingRequest housing){
        return Response.ok( mainService.addHousing(housing)).build();
    }

    @POST
    @Path("/testHD")
    public Response deleteHousing(Housing housing){
        return Response.ok( mainService.deleteHousing(housing)).build();
    }


    @POST
    @Path("/testAA")
    public Response addAuditory(AuditoryRequest auditoryRequest){
        return Response.ok( mainService.addAuditory(auditoryRequest)).build();
    }

    @POST
    @Path("/updateUser")
    public Response updateUser(UpdateUserRequest updateRequest){
        return Response.ok(mainService.updateUser(updateRequest)).build();
    }

    @GET
    @Path("/getAllUsers")
    public Response getAllUsers(){
        return Response.ok(mainService.getUsers()).build();
    }

    @POST
    @Path("/updateCorps")
    public Response updateCorps(List<UpdateHousingRequest> requests){
        return Response.ok(mainService.updateHousings(requests)).build();
    }

    @GET
    @Path("/getAllGroups")
    public Response getAllGroups(){
        return Response.ok(mainService.getGroups()).build();
    }

    @POST
    @Path("/updateGroups")
    public Response updateGroups(List<UpdateGroupsRequest> requests){
        return Response.ok(mainService.updateGroups(requests)).build();
    }

    @POST
    @Path("/updateAuditories")
    public Response updateAuditories(UpdateAuditoriesRequest requests){
        return Response.ok(mainService.updateAuditories(requests)).build();
    }

    @GET
    @Path("/getAllHoustings")
    public Response getAllHoustings(){
        return Response.ok(mainService.getHostings()).build();
    }

    @GET
    @Path("/getAllAuditories")
    public Response getAllAuditories(){ return  Response.ok(mainService.getAuditories()).build();}

    @GET
    @Path("/getEnableTeachers")
    public Response getEnableTeachers(){
        return Response.ok(mainService.getEnableTeacher()).build();
    }
    
    @GET
    @Path("/getEnableGroups")
    public Response getEnableGroups(){
        return Response.ok(mainService.getEnableGroups()).build();
    }
    
    @GET
    @Path("/getEnableHoustings")
    public Response getEnableHoustings(){
        return Response.ok(mainService.getEnableHoustings()).build();
    }
    
    @GET
    @Path("/getEnableAuditories")
    public Response getEnableAuditories(){
        return Response.ok(mainService.getEnableAuditories()).build();
    }
    
    
    @POST
    @Path("/uploadFile")
    @Consumes("multipart/form-data")
    public Response uploadFile(MultipartFormDataInput input) {
        File excel = upload(input);
        try {
            FileInputStream inputStream = new FileInputStream(excel);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            return Response.ok(  mainService.addFromExcel(workbook)).build();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private File writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
        return file;
    }

    private File upload(MultipartFormDataInput input){
        String fileName = "raspisanie.xls";
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");
        for (InputPart inputPart : inputParts) {
            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                return writeFile(bytes, fileName);
         } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
