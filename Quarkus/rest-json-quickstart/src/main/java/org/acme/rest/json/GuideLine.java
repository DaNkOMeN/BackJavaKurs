/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme.rest.json;

/**
 *
 * @author skale
 */
public class GuideLine {
    int id;
    String title;
    boolean enable;

    public GuideLine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public GuideLine(int id, String title, boolean enable){
        this.id = id;
        this.title = title;
        this.enable = enable;
    }
}
