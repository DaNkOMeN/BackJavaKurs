package ru.rsatu.java.front;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;

import java.io.*;
import java.security.Key;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import static jdk.nashorn.internal.objects.NativeError.getFileName;

@RequestScoped
@Path("/main")
public class MainFront {




    @POST
    @Path("/uploadFile")
    @Consumes("multipart/form-data")
    public Response uploadFile(MultipartFormDataInput input) {
        File excel = upload(input);
        try {
            FileInputStream inputStream = new FileInputStream(excel);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowiterator = sheet.iterator();
            String query;
            while (rowiterator.hasNext()) {
                Row row = rowiterator.next();
                System.out.println(row.getCell(1) + " " + row.getCell(2)+ " " + row.getCell(7));
            }
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.status(200)
                .entity("uploadFile is called, Uploaded file name : " + excel).build();

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
                System.out.println("Done");
                return writeFile(bytes, fileName);
         } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
