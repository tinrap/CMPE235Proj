package sensorproj.sjsu.com.cmpe235proj;

/**
 * Created by Parnit on 4/28/2015.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        //initialize map and markers
        GoogleMap  map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        ArrayList<Marker> markers = new ArrayList<Marker>();

        //get sensors details
        Intent intent = getIntent();
        Sensor sensor = (Sensor) intent.getSerializableExtra(AppConstants.SENSOR);
        ArrayList<Sensor> sensors = (ArrayList<Sensor>) intent.getSerializableExtra(AppConstants.SENSOR_LIST);



        //if sensor is not null, set it as a main point in map
        if (sensor != null) {
            LatLng myLocation = new LatLng(sensor.getLatitude(), sensor.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,8));
        }

        //add all sensors to mapp
        for (Sensor s: sensors){
            markers.add(map.addMarker(new MarkerOptions()
                    .position(new LatLng(s.getLatitude(), s.getLongitude()))
                    .title(s.getName())
                    .snippet(s.getDescription())));
        }


    }


}