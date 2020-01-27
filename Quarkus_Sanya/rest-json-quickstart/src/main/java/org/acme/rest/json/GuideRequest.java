/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme.rest.json;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skale
 */
public class GuideRequest {
    String guideName;
    ArrayList<GuideLine> lines;

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public ArrayList<GuideLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<GuideLine> lines) {
        this.lines = lines;
    }

    public GuideRequest() {
    }
}
