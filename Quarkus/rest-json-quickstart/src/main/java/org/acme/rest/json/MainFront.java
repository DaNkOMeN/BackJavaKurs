package org.acme.rest.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import javax.enterprise.context.RequestScoped;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


@RequestScoped
@Path("/rest/")
public class MainFront {
    @POST
    @Path("uploadFile")
    @Consumes("multipart/form-data")
    public Response uploadFile(MultipartFormDataInput input) {
        System.out.println("Прибыл файл");
        File excel= upload(input);
        System.out.println(excel.getAbsoluteFile()+"  "+excel.canRead());
        try {
            FileInputStream inputStream = new FileInputStream(excel);
                    System.out.println("_-----------------------------------1");
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                    System.out.println("_-----------------------------------2");
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
                .entity("uploadFile is called, Uploaded file name : " ).build();

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