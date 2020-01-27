/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 *
 * @author skale
 */
public class Data {
    int id;
    String name;
    public Set<GuideLine> lines = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GuideLine> getLines() {
        return lines;
    }

    public void setLines(Set<GuideLine> lines) {
        this.lines = lines;
    }



}
