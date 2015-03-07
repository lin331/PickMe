package com.example.aneesh.testing_app;

//import android.support.v7.app.ActionBarActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;

/*
public class MapActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
}*/

import android.animation.IntEvaluator;
        import android.animation.ValueAnimator;
        import android.graphics.Color;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.animation.AccelerateDecelerateInterpolator;

        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.Circle;
        import com.google.android.gms.maps.model.CircleOptions;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

        import java.util.Timer;
        import java.util.TimerTask;

public class MapActivity extends ActionBarActivity {

    private GoogleMap mMap;

    //tryna get marker to blink
    /*private GoogleMap marker;
    private boolean blinkMarker(){


        if(marker == true){
            current.setVisible(true);
            marker = false;
        }
        else if(marker == false){
            current.setVisible(false);
            marker = true;
        }
        return marker;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //hackaton
        //setUpMapIfNeeded();
        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }

        //san jose marker
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.340974, -121.892257))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                .title("7.9"));

        mMap.setMyLocationEnabled(true);

        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = (new CircleOptions()
                .center(new LatLng(37.554295, -121.980382))
                .radius(1000) // In meters
                        //.fillColor(Color.GREEN)
                .fillColor(0xff00bb00)
                        //.strokeColor(Color.GREEN));
                .strokeColor(0xff000000)).strokeWidth(2);
        // Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);

        final Circle SFcircle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(37.776061, -122.425328))
                .radius(100)
                .strokeColor(Color.RED).radius(1000));

        ValueAnimator vAnimator = new ValueAnimator();
        vAnimator.setRepeatCount(ValueAnimator.INFINITE);
        vAnimator.setRepeatMode(ValueAnimator.RESTART);  /* PULSE */
        vAnimator.setIntValues(0, 100);
        vAnimator.setDuration(1000);
        vAnimator.setEvaluator(new IntEvaluator());
        vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                // Log.e("", "" + animatedFraction);
                SFcircle.setRadius(animatedFraction * 1000);
            }
        });
        vAnimator.start();

        //tryna get marker to blink
        /*
        MyMarker = mMap.addMarker(new MarkerOptions().position(current_loc).title(address).snippet(city).
                icon(BitmapDescriptorFactory.fromResource(R.drawable.bus4)));

        markertimer = new Timer();
        markertimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        blinkMarker();
                    }
                });
            }
        }, 0, 500);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}