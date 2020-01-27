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
public class Corp {
    public int id;
    public String title;
    public boolean enable;
    
    public Corp(int id, String title, boolean enable){
        this.id = id;
        this.title = title;
        this.enable = enable;
    }
    public Corp(){
    }
}
