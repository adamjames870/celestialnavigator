package com.checkmyrest.celestialnavigator;

import java.text.DecimalFormat;

public class Intercept {

    private double intercept; //
    private String interceptDirection; // Towards or Away

    public Intercept (double iIntercept, String iInterceptDirection) {
        intercept = iIntercept;
        interceptDirection = iInterceptDirection;
    }

    public String toString() {
        DecimalFormat f = new DecimalFormat("0.0");
        double deg = Constants.Floor(intercept, 1);
        double min = (intercept - deg) * 60;
        return "Intercept " + f.format(intercept * 60) + "' " + interceptDirection;
    }

}
