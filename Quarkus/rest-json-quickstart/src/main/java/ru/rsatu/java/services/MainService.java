package ru.rsatu.java.services;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.*;
import javax.transaction.Transactional;
import java.util.*;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import io.jsonwebtoken.Jwts;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.graalvm.compiler.lir.LIRInstruction;
import ru.rsatu.java.requests.*;
import ru.rsatu.java.tables.*;


@ApplicationScoped
public class MainService {

    @Transactional
    public List<Groups> addFromExcel(HSSFWorkbook workbook) {
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowiterator = sheet.iterator();
        while (rowiterator.hasNext()) {
            Row row = rowiterator.next();
            //System.out.println(row.getRowNum());
            if (row.getCell(1) != null)
                if (!row.getCell(0).getStringCellValue().equals("") && !row.getCell(0).getStringCellValue().contains("Группа")) {
                    //добавление группы
                    String groupName = row.getCell(0).getStringCellValue();
                    Groups groupInDatabase = Groups.find("nameOfGroup", groupName).firstResult();
                    if (groupInDatabase == null) {
                        Groups group = new Groups(groupName, true);
                        group.persist();
                    }
                    //добавление корпусов и аудиторий
                    String housingAndAuditoryString = row.getCell(7).getStringCellValue();
                    if (housingAndAuditoryString.contains("-")) {
                        String housingAndAuditory[] = housingAndAuditoryString.trim().split("-");
                        Housing housing = Housing.find("numberOfHousing", housingAndAuditory[0]).firstResult();
                        Housing newHousing = null;
                        if (housing == null) {
                            newHousing = new Housing(housingAndAuditory[0], true);
                            newHousing.persist();
                           // System.out.println("Хоусинг персист");
                        }

                        if (housingAndAuditory[1].contains(",")) {
                            String auditoryArray[] = housingAndAuditory[1].split(",");  //массив 305 306
                            for (int i = 0; i < auditoryArray.length; i++) {               //для каждого написанного через запятую
                                if (newHousing == null) {
                                    addAuditory(auditoryArray[i], housing.getNumberOfHousing());
                                } else {
                                    addAuditory(auditoryArray[i], newHousing.getNumberOfHousing());
                                }
                            }

                        } else {
                            if (newHousing == null) {
                                addAuditory(housingAndAuditory[1], housing.getNumberOfHousing());
                            } else {
                                addAuditory(housingAndAuditory[1], newHousing.getNumberOfHousing());
                            }
                        }
                    } else if (housingAndAuditoryString.contains("Корпус")) { //если корпус без номера аудитории (например физра)
                        String housingMas[] = housingAndAuditoryString.trim().split("Корпус");
                        Housing housing = Housing.find("numberOfHousing", housingMas[0].trim()).firstResult();
                        if (housing == null) {
                            Housing newHousing = new Housing(housingMas[0].trim(), true);
                            newHousing.persist();
                        }
                    }


                    //добавление преподавателей
                    User newTeacher = null;
                    if (!row.getCell(8).getStringCellValue().equals("")) {
                        String teacherName = row.getCell(8).getStringCellValue().trim();
                        User teacher = User.find("login", teacherName).firstResult();
                        if (teacher == null) {
                            newTeacher = addTeacher(teacherName);
                        } else {
                            newTeacher = teacher;
                        }
                    } else {
                        User teacher = User.find("login","Физкультура").firstResult();
                        if (teacher == null) {
                            addTeacher("Физкультура");
                        } else {
                            newTeacher = teacher;
                        }
                    }

                    //добавление пар
                }
        }
        return Groups.listAll();
    }


    private User addTeacher(String teacherLogin){
        User teacher = new User(teacherLogin, "default", teacherLogin+"@","teacher", false);
        teacher.persist();
        return teacher;
    }

    private void addAuditory(String auditoryNumber, String numberOfHousing) {
        List<Auditory> auditorys = Auditory.find("number", auditoryNumber).list();
        Auditory findauditory = null;
        for (Auditory auditoryy : auditorys) {
            if (auditoryy.getHousing().getNumberOfHousing().equals(numberOfHousing)) {
                findauditory = auditoryy;
                break;
            }
        }//если аудитории с таким корпусом нет, то добавляем
        Housing housing = Housing.find("numberOfHousing", numberOfHousing).firstResult();
        if (findauditory == null) {
            //System.out.println("Нет аудитории с таким номером");
            Auditory auditory = new Auditory(auditoryNumber, housing, false, false, true);
            auditory.persist();
        }
    }


    @Transactional
    public List<Housing> addHousing(HousingRequest housingRequest) {
        System.out.println("kek");
        Housing alreadyHaveHousing = Housing.find("numberOfHousing", housingRequest.getNumberOfHousing()).firstResult();
        if (alreadyHaveHousing != null) {
            System.out.println("Такой корпус есть");
        } else {
            Housing housing = new Housing(housingRequest.getNumberOfHousing(), housingRequest.isEnable());
            housing.persist();
        }
        System.out.println("----------------------------------------------");

        return Housing.listAll();
    }

    @Transactional
    public List<User> getUsers(){
        return User.listAll();
    }

    @Transactional
    public List<Housing> getHostings(){
        return Housing.listAll();
    }

    @Transactional
    public List<Groups> getGroups(){
        return Groups.listAll();
    }

    @Transactional
    public List<AuditoryResponse> getAuditories() {
        List<Auditory> aud  = Auditory.listAll();
        List<AuditoryResponse> audResp = new ArrayList<>();
        for (Auditory au : aud){
            audResp.add(new AuditoryResponse(au.id.intValue(), au.getNumber(),au.getHousing().getNumberOfHousing(), au.isComputer(), au.isProjector(), au.isAvailable()));
        }
        return audResp;
    }

    @Transactional
    public List<Groups> addGroup(GroupRequest request) {
        Groups alreadyHaveGroup = Groups.find("nameOfGroup", request.getNameOfGroup()).firstResult();
        if (alreadyHaveGroup != null) {
            System.out.println("Такая группа есть");
        } else {
            Groups group = new Groups(request.getNameOfGroup(), true);
            group.persist();
        }

        //List<Groups> list =;
        return Groups.listAll();
    }

    @Transactional
    public List<Groups> deleteGroup(GroupRequest request) {
        Groups.delete("nameOfGroup", request.getNameOfGroup());
        return Groups.listAll();
    }

    @Transactional
    public List<Housing> deleteHousing(Housing housing) {
        Housing.delete("numberOfHousing", housing.getNumberOfHousing());
        return Housing.listAll();
    }

    @Transactional
    public List<Auditory> addAuditory(AuditoryRequest request) {
        //addAuditory(request.getNumber(), request.getHousingNumber());
        Housing housing = Housing.find("numberOfHousing", request.getHousingNumber()).firstResult();
        if (housing != null) {
            if (housing.hasAuditory(request.getNumber())) {
                System.out.println("Такая аудитория есть");
            } else {
                Auditory auditory = new Auditory(request.getNumber(), housing, request.isComputer(), request.isProjector(), request.isAvailable());
                auditory.persist();
            }
        } else {
            System.out.println("Не найден корпус");
        }
        return Auditory.listAll();
    }

    @Transactional
    public List<User> addUser(UserRequest request) {
        PanacheQuery<User> queryUsers = User.find("login = ?1 or email = ?2", request.getLogin(), request.getEmail());
        if (queryUsers.list().size() > 0) {
            System.out.println("Такой человек существует");
        } else {
            User user = new User(request.getLogin(), request.getPassword(), request.getEmail(), request.getRole(), request.isBlock());
            user.persist();
        }
        return User.listAll();
    }

    @Transactional
    public List<PairRequest> addPair(PairRequest request) {
        PanacheQuery<Pair> queryPairs = Pair.find("dayOfWeek = ?1 and numberOfWeek = ?2 and numberOfPair = ?3",
                request.getDayOfWeek(), request.getNumberOfWeek(), request.getNumberOfPair());
        List<Pair> pairs = queryPairs.list();
        System.out.println(pairs.size());
        if (pairs.size() == 0) {
            User teacher = User.find("login", request.getUserLogin()).firstResult();
            System.out.println("teacher login " + teacher.getLogin());
            if (teacher != null) {
                System.out.println("Учитель найден");
                List<Groups> groups = new ArrayList<>();
                List<Groups> queryGroupss = Groups.listAll();
                for (Groups currentGroup : queryGroupss) {
                    for (String stringNameOfGroup : request.getGroupsName()) {
                        if (currentGroup.getNameOfGroup().equals(stringNameOfGroup)) {
                            groups.add(currentGroup);
                            break;
                        }
                    }
                }
                PanacheQuery<Auditory> queryAuditory = Auditory.find("number", request.getAuditoryNumber());
                List<Auditory> auditories = queryAuditory.list();
                Auditory needAuditory = null;
                for (Auditory auditory : auditories) {
                    if (auditory.getHousing().getNumberOfHousing().equals(request.getHousingNumber())) {
                        needAuditory = auditory;
                        break;
                    }
                }

                Pair pair = new Pair(request.getDayOfWeek(), request.getNumberOfWeek(), request.getNumberOfPair(), teacher, groups, needAuditory, request.getDescription());

                System.out.println("Добавлена");
                pair.persist();
            }
        } else {
            for (Pair currentPair : pairs) {
                if ((currentPair.getAuditory().getNumber().equals(request.getAuditoryNumber()))
                        && (currentPair.getAuditory().getHousing().getNumberOfHousing() == request.getHousingNumber())) {
                    System.out.println("Пара занята");
                } else {
                    System.out.println("Пара не занята");
                    User teacher = User.find("login", request.getUserLogin()).firstResult();
                    if (teacher != null) {
                        List<Groups> groups = new ArrayList<>();
                        List<Groups> queryGroupss = Groups.listAll();
                        for (Groups currentGroup : queryGroupss) {
                            for (String stringNameOfGroup : request.getGroupsName()) {
                                if (currentGroup.getNameOfGroup().equals(stringNameOfGroup)) {
                                    groups.add(currentGroup);
                                    break;
                                }
                            }
                        }
                        PanacheQuery<Auditory> queryAuditory = Auditory.find("number", request.getAuditoryNumber());
                        List<Auditory> auditories = queryAuditory.list();
                        Auditory needAuditory = null;
                        for (Auditory auditory : auditories) {
                            if (auditory.getHousing().getNumberOfHousing() == request.getHousingNumber()) {
                                needAuditory = auditory;
                                break;
                            }
                        }

                        Pair pair = new Pair(request.getDayOfWeek(), request.getNumberOfWeek(), request.getNumberOfPair(), teacher, groups, needAuditory, request.getDescription());
                        System.out.println("Добавлена");
                        pair.persist();
                    } else {
                        System.out.println("Нет такого пользователя");
                    }
                }
            }
        }
        System.out.println("-------------------------------");
        List<PairRequest> pairRequests = new ArrayList<>();
        List<Pair> allPairs = Pair.listAll();
        for (Pair pair : allPairs) {
            List<String> groupNameList = new ArrayList<>();
            for (Groups group : pair.getGroups()) {
                groupNameList.add(group.getNameOfGroup());
            }
            PairRequest r = new PairRequest(pair.getDayOfWeek(), pair.getNumberOfWeek(), pair.getNumberOfPair(),
                    pair.getTeacher().getLogin(), groupNameList, pair.getAuditory().getHousing().getNumberOfHousing(), pair.getAuditory().getNumber(), pair.getDescription());
            pairRequests.add(r);
        }

        return pairRequests;
    }

    @Transactional
    public List<User> updateUser(UpdateUserRequest updateRequest) {
        User user = User.findById(Long.parseLong(updateRequest.getId()));
        user.setBlock(!updateRequest.isEnable());
        user.setEmail(updateRequest.getEmail());
        user.setLogin(updateRequest.getLogin());
        user.setPassword(updateRequest.getPassword());
        user.setRole(updateRequest.getRole());
        user.persist();
        System.out.println(user.getLogin());
        return User.listAll();
    }

    @Transactional
    public List<Housing> updateHousings(List<UpdateHousingRequest> requests) {
        for (UpdateHousingRequest request : requests) {
            if (request != null) {
                String action = request.getAction();
                switch (action) {
                    case "Редактирование":
                        Housing housing = Housing.find("id", request.getCorp().id).firstResult();
                        housing.setNumberOfHousing(request.getCorp().getNumberOfHousing());
                        housing.setEnable(request.getCorp().isEnable());
                        housing.persist();
                        break;
                    case "Добавление":
                        Housing housing1 = new Housing(request.getCorp().getNumberOfHousing(), request.getCorp().isEnable());
                        housing1.persist();
                        break;
                    default:
                        break;
                }
            }
        }
        return Housing.listAll();
    }

    @Transactional
    public List<Groups> updateGroups(List<UpdateGroupsRequest> requests) {
        for (UpdateGroupsRequest request : requests) {
            if (request != null) {
                System.out.println("вошли для " + request.getGroups().getNameOfGroup());
                String action = request.getAction();
                switch (action) {
                    case "Редактирование":

                        Groups groups = Groups.findById(request.getGroups().id);
                        groups.setNameOfGroup(request.getGroups().getNameOfGroup());
                        groups.setEnable(request.getGroups().isEnable());
                        groups.persist();
                        break;
                    case "Добавление":
                        Groups groups1 = new Groups(request.getGroups().getNameOfGroup(), request.getGroups().isEnable());
                        groups1.persist();
                        break;
                    default:
                        break;
                }
            }
        }
        return Groups.listAll();
    }


    @Transactional
    public List<AuditoryResponse> updateAuditories(UpdateAuditoriesRequest request){
        System.out.println("Вошли в аудиторию" + request.getAuditory().getHousingNumber() + "-" + request.getAuditory().getNumber());
        String action = request.getAction();
        switch (action) {
            case "Редактирование":
                Auditory auditory = Auditory.findById(Long.valueOf(request.getAuditory().getId()));
                auditory.setAvailable(request.getAuditory().isEnable());
                auditory.setComputer(request.getAuditory().isComputer());
                auditory.setHousing(Housing.findById(Long.valueOf(request.getHousingId())));
                auditory.setNumber(request.getAuditory().getNumber());
                auditory.setProjector(request.getAuditory().isProjector());
                auditory.persist();
                break;
                case "Добавление":
                    Housing h = Housing.findById( Long.valueOf(request.getHousingId()));
                    Auditory auditory1 = new Auditory(request.getAuditory().getNumber(), h, request.getAuditory().isComputer(), request.getAuditory().isProjector(), request.getAuditory().isEnable());
                    auditory1.persist();
                    break;
                default:
                    break;
        }
        return getAuditories();
    }

    public List<User> getEnableTeacher() {
        List<User> teachers = User.find("role = ?1 and isBlock = ?2", "teacher", false).list();
//        for (User user :teachers){
//            System.out.println(user.getEmail());
//        }
        return teachers;
    }
    
    public List<Groups> getEnableGroups(){
        List<Groups> groups = Groups.find("enable",true).list();
         for (Groups group :groups){
            System.out.println(group.getNameOfGroup());
        }
         return groups;
    }
    
    public List<Housing> getEnableHoustings(){
        List<Housing> housings = Housing.find("enable", true).list();
        return housings;
    }

    
    public List<AuditoryResponse> getEnableAuditories(){
        List<Auditory> auditories = Auditory.find("enable", true).list();
        List<AuditoryResponse> enableAuditories = new ArrayList<>();
        for (Auditory auditory : auditories){
            enableAuditories.add(new AuditoryResponse(auditory.id.intValue(), auditory.getNumber(),
                    auditory.getHousing().getNumberOfHousing(),auditory.isComputer(),auditory.isProjector(), true));
        }
        return enableAuditories;
    }

}
