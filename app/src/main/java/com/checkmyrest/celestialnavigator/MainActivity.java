package com.checkmyrest.celestialnavigator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculateAzimuth(View view) {
        try {
            DecimalFormat f = new DecimalFormat("#.0");
            ((TextView) findViewById(R.id.txtAzimuth)).setText("Azimuth: " + f.format(FetchSimpleSightReduction().Azimuth().getTrueCourse()) + "\u00B0T");
            ((TextView) findViewById(R.id.txtAltitide)).setText("Altitude: " + Constants.toSexisegimal(FetchSimpleSightReduction().CalculatedAltitude()));
        } catch (Exception e) {
            ((TextView) findViewById(R.id.txtAzimuth)).setText(e.toString());
        }
    }

    private SightReduction FetchSimpleSightReduction() {
        return new SightReduction(LocalHourAngle(), CorrectedDeclination(), new LatLonLocation(FetchLatitude(), FetchLongitude()));
    }

/*    private SightReduction FetchFullSightReduction() {
        return new SightReduction(LocalHourAngle(), CorrectedDeclination(), new LatLonLocation(FetchLatitude(), FetchLongitude()),
                FetchSextantAngle(), FetchIndexError(), FetchDip(), FetchAdjustment());
    }*/

    private double FetchGha() {
        double ghaDeg = Nz( ( (TextView) findViewById(R.id.txtGhaDeg) ).getText().toString() );
        double ghaMin = Nz( ( (TextView) findViewById(R.id.txtGhaMin) ).getText().toString() );
        return ghaDeg + (ghaMin / 60);
    }

    private double FetchIncrement() {
        double incDeg = Nz( ( (TextView) findViewById(R.id.txtIncDeg) ).getText().toString());
        double incMin = Nz(((TextView) findViewById(R.id.txtIncMin)).getText().toString());
        return incDeg + (incMin / 60);
    }

    private double FetchCv() {
        double cvDeg = Nz( ( (TextView) findViewById(R.id.txtCvDeg) ).getText().toString() );
        double cvMin = Nz( ( (TextView) findViewById(R.id.txtCvMin) ).getText().toString());
        String cvPlusMinus = String.valueOf(( (Spinner) findViewById(R.id.cmbCvPlusMinus) ).getSelectedItem());
        double returnValue = cvDeg + (cvMin / 60);
        if (cvPlusMinus.equals("-")) {return returnValue * -1;} else {return returnValue;}
    }

    private Longitude FetchLongitude() {
        double lonDeg = Nz( ( (TextView) findViewById(R.id.txtLonDeg) ).getText().toString() );
        double lonMin = Nz( ( (TextView) findViewById(R.id.txtLonMin) ).getText().toString() );
        String lonEw = String.valueOf(((Spinner) findViewById(R.id.cmbLonEw)).getSelectedItem());
        return new Longitude(lonDeg + (lonMin / 60), lonEw);
    }

    private Latitude FetchLatitude() {
        double latDeg = Nz( ( (TextView) findViewById(R.id.txtLatDeg) ).getText().toString() );
        double latMin = Nz( ( (TextView) findViewById(R.id.txtLatMin) ).getText().toString() );
        String latNs = String.valueOf(((Spinner) findViewById(R.id.cmbLatNs)).getSelectedItem());
        return new Latitude(latDeg + (latMin / 60), latNs);
    }

    private Latitude FetchDeclination() {
        double decDeg = Nz( ( (TextView) findViewById(R.id.txtDecDeg) ).getText().toString() );
        double decMin = Nz( ( (TextView) findViewById(R.id.txtDecMin) ).getText().toString() );
        String decNs = String.valueOf(((Spinner) findViewById(R.id.cmbDecNs)).getSelectedItem());
        return new Latitude(decDeg + (decMin / 60), decNs);
    }

    private double FetchCd() {
        double cdDeg = Nz( ( (TextView) findViewById(R.id.txtCdDeg) ).getText().toString() );
        double cdMin = Nz( ( (TextView) findViewById(R.id.txtCdMin) ).getText().toString());
        String cdPlusMinus = String.valueOf(((Spinner) findViewById(R.id.cmbCdPlusMinus)).getSelectedItem());
        double returnValue = cdDeg + (cdMin / 60);
        if (cdPlusMinus.equals("-")) {return returnValue * -1;} else {return returnValue;}
    }

/*
    private double FetchSextantAngle() {
        double saDeg = Double.parseDouble( ( (TextView) findViewById(R.id.txtSaDeg) ).getText().toString() );
        double saMin = Double.parseDouble( ( (TextView) findViewById(R.id.txtSaMin) ).getText().toString());
        return saDeg + (saMin / 60);
    }

    private double FetchIndexError() {
        double ieDeg = Double.parseDouble( ( (TextView) findViewById(R.id.txtIeDeg) ).getText().toString() );
        double ieMin = Double.parseDouble( ( (TextView) findViewById(R.id.txtIeMin) ).getText().toString());
        String ieOnOff = String.valueOf(((Spinner) findViewById(R.id.cmbIeOnOff)).getSelectedItem());
        double returnValue = ieDeg + (ieMin / 60);
        if (ieOnOff.equals("On")) {return returnValue * -1;} else {return returnValue;}
    }

    private double FetchDip() {
        double dipDeg = Double.parseDouble( ( (TextView) findViewById(R.id.txtDipDeg) ).getText().toString() );
        double dipMin = Double.parseDouble( ( (TextView) findViewById(R.id.txtDipMin) ).getText().toString());
        String dipPlusMinus = String.valueOf(((Spinner) findViewById(R.id.cmbDipPlusMinus)).getSelectedItem());
        double returnValue = dipDeg + (dipMin / 60);
        if (dipPlusMinus.equals("-")) {return returnValue * -1;} else {return returnValue;}
    }

    private double FetchAdjustment () {
        double adjDeg = Double.parseDouble( ( (TextView) findViewById(R.id.txtAdjDeg) ).getText().toString() );
        double adjMin = Double.parseDouble( ( (TextView) findViewById(R.id.txtAdjMin) ).getText().toString());
        String adjPlusMinus = String.valueOf(((Spinner) findViewById(R.id.cmbAdjPlusMinus)).getSelectedItem());
        double returnValue = adjDeg + (adjMin / 60);
        if (adjPlusMinus.equals("-")) {return returnValue * -1;} else {return returnValue;}
    }
*/

    private double LocalHourAngle() {
        double corrGha = FetchGha() + FetchIncrement() + FetchCv();
        if (FetchLongitude().getLonEW().equals("E"))
            {return Constants.CorrectTo360( corrGha + FetchLongitude().getLon() );}
        else
            {return Constants.CorrectTo360( corrGha - FetchLongitude().getLon() );}
    }

    private Latitude CorrectedDeclination() {
        return new Latitude(FetchDeclination().getLat() + FetchCd(), FetchDeclination().getLatNS());
    }

    private double Nz(String x) {
        if (x.equals("")) {return 0;} else {return Double.parseDouble(x);}
    }

}
