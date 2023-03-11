package com.checkmyrest.celestialnavigator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

final public class Constants {

    public static final double MIN_LAT = 0;
    public static final double MAX_LAT = 90;
    public static final double MIN_LON = 0;
    public static final double MAX_LON = 180;

    private Constants() {
    }

    public static double Ceiling(double value, double ceiling) {
        return ceiling * Math.ceil(value / ceiling);
    }

    public static double Floor(double value, double floor) {
        return floor * Math.floor(value / floor);
    }

    public static String getOppositeNS(String iNS) {
        if (iNS.equals("N")) {
            return "S";
        } else {
            return "N";
        }
    }

    public static String getOppositeEW(String iEW) {
        if (iEW.equals("W")) {
            return "E";
        } else {
            return "W";
        }
    }

    public static double CorrectTo360(double i) {
        if (i < 0) {return i + 360;}
        else if (i > 360) {return i - 360;}
        else {return i;}
    }

    public static String toSexisegimal(double x) {
        NumberFormat nfLat = new DecimalFormat("00");
        NumberFormat nfMin = new DecimalFormat("00.0");
        nfLat.setRoundingMode(RoundingMode.DOWN);
        return nfLat.format(x) + "\u00B0" + nfMin.format( (x % 1) * 60 ) + "'";
    }

}
