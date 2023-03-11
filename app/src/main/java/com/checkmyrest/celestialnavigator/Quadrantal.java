package com.checkmyrest.celestialnavigator;

public class Quadrantal {

    private String NS;
    private double course;
    private String EW;

    public Quadrantal(String iNS, double iCourse, String iEW) {
        NS = iNS;
        course = iCourse % 360;
        EW = iEW;
    }

    public double getTrueCourse() {
        if (NS.equals("N")) {
            if (EW.equals("E")) {return course;} else {return 360 - course;}
        } else {
            if (EW.equals("E")) {return 180 - course;} else {return 180 + course;}
        }
    }

    public double getRawCourse() {return course;}
    public String EwDirection() {return EW;}
    public String NsDirection() {return NS;}

}
