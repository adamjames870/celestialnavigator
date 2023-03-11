package com.checkmyrest.celestialnavigator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Latitude {

    private double lat;
    private String latNS;

    public Latitude(double iLat, String iLatNS) {
        try {
            setLat(iLat);
            setLatNS(iLatNS);
        } catch (Exception e) {throw new IllegalArgumentException(e.toString());}
    }

    private void setLat(double iLat) {
        if (iLat < Constants.MIN_LAT || iLat > Constants.MAX_LAT) {
            throw new IllegalArgumentException("Unable to set Lat as " + Double.toString(iLat));
        } else {lat = iLat;}
    }

    private void setLatNS(String iLatNS) {
        if (!iLatNS.equals("N") && !iLatNS.equals("S")) {
            throw new IllegalArgumentException("Unable to set LatNS as " + iLatNS);
        } else {latNS = iLatNS;}
    }

    public double getLat() {return lat;}
    public String getLatNS() {return latNS;}

    public String getText() {
        NumberFormat nfLat = new DecimalFormat("00");
        NumberFormat nfMin = new DecimalFormat("00.0");
        nfLat.setRoundingMode(RoundingMode.DOWN);
        return nfLat.format(lat) + "\u00B0" + nfMin.format( (lat % 1) * 60 ) + "'" + latNS;
    }

}
