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
@Path("/rest/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DBWorking {
    
    public Set<GuideLine> corps = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    public Set<GuideLine> groups = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    public Set<GuideLine> kabs = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public DBWorking() {
    }


    @POST
    @Path("updateCorps")
    public Set<GuideLine> updateCorps(GuideRequest corps) {
        if ("Корпуса".equals(corps.guideName)) {
        this.corps = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for (GuideLine l : corps.lines)
               this.corps.add(l);
            System.out.println("Новые корпуса прибыли "+this.corps.size());
        return this.corps;
        } else if ("Кабинеты".equals(corps.guideName)) {
        this.kabs = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for (GuideLine l : corps.lines)
               this.kabs.add(l);
            System.out.println("Новые кабинеты прибыли "+this.kabs.size());
        return this.kabs;
        } else if ("Группы".equals(corps.guideName)) {
        this.groups = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for (GuideLine l : corps.lines)
               this.groups.add(l);
            System.out.println("Новые группы прибыли "+this.groups.size());
        return this.groups;}
        return null;
    }
    
    @GET 
    @Path("loadCorps")
    public Set<GuideLine> loadCorps() {
        System.out.println("Запрос корпусов "+this.corps.size());
        return this.corps;
    }
    
    @GET 
    @Path("loadGrups")
    public Set<GuideLine> loadData() {
        System.out.println("Запрос групп "+this.groups.size());
        return  this.groups;
    }
}

