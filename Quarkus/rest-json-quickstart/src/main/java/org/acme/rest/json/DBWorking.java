/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author skale
 */
@Path("/rest/updateCorps")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DBWorking {
    
    public Set<GuideLine> corps = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    public Set<GuideLine> groups = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    public Set<GuideLine> kabs = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public DBWorking() {
        corps.add(new GuideLine(0, "Корпус", true));
        corps.add(new GuideLine(1, "Первый", true));
        corps.add(new GuideLine(2, "Второй", true));
        corps.add(new GuideLine(3, "Третий", true));
        
        kabs.add(new GuideLine(0, "Г-101", true));
        kabs.add(new GuideLine(1, "Г-102", true));
        kabs.add(new GuideLine(2, "Г-103", true));
        kabs.add(new GuideLine(3, "Г-104", true));
        
        groups.add(new GuideLine(0, "ИВБ", true));
        groups.add(new GuideLine(1, "ИВМ", true));
        groups.add(new GuideLine(2, "ПИБ", true));
        groups.add(new GuideLine(3, "ПИМ", true));
    }


    @POST
    public Set<GuideLine> updateCorps(GuideRequest corps) {
        if ("Корпуса".equals(corps.guideName)) {
        this.corps = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for (GuideLine l : corps.lines)
               this.corps.add(l);
        } else if ("Группы".equals(corps.guideName)) {
        this.corps = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for (GuideLine l : corps.lines)
               this.corps.add(l);
        } else if ("Кабинеты".equals(corps.guideName)) {
        this.corps = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for (GuideLine l : corps.lines)
               this.corps.add(l);
        }
        return this.corps;
    }
    
    @GET
    public Set<Data> loadData() {
        Data temp1 = new Data();
        Set data = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        temp1.id = 1;
        temp1.name = "Корпуса";
        temp1.lines = this.corps;
        data.add(temp1);
        
        Data temp2 = new Data();
        temp2.id = 2;
        temp2.name = "Группы";
        temp2.lines = this.groups;
        data.add(temp2);
        
        Data temp3 = new Data();
        temp3.id = 3;
        temp3.name = "Кабинеты";
        temp3.lines = this.kabs;
        data.add(temp3);
        return data;
    }
}

