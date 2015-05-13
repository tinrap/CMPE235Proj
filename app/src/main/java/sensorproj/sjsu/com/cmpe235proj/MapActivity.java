package sensorproj.sjsu.com.cmpe235proj;

/**
 * Created by Parnit on 4/28/2015.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity {
    private   ArrayList<Sensor> sensors ;
    private ProgressDialog progressBar;
    private  ArrayList<Marker> markers;
    private GoogleMap  map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        //initialize map and markers
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        markers = new ArrayList<Marker>();

        //get sensors details
        Intent intent = getIntent();
        Sensor sensor = (Sensor) intent.getSerializableExtra(AppConstants.SENSOR);
        ArrayList<Sensor> sensors = (ArrayList<Sensor>) intent.getSerializableExtra(AppConstants.SENSOR_LIST);
        String map_type = (String) intent.getSerializableExtra(AppConstants.MAP_TYPE);



        //if sensor is not null, set it as a main point in map
        if(map_type.equals(AppConstants.FROM_HOME))
        {
            User user = (User) intent.getSerializableExtra(AppConstants.USER);

            LatLng myLocation = new LatLng(user.getLatitude(), user.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,8));
            new GetSensorAsycTask().execute(user.getId());
        }
       else  if (sensor != null) {
            LatLng myLocation = new LatLng(sensor.getLatitude(), sensor.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,8));



            //add all sensors to mapp
            for (Sensor s: sensors){
                markers.add(map.addMarker(new MarkerOptions()
                        .position(new LatLng(s.getLatitude(), s.getLongitude()))
                        .title(s.getName())
                        .snippet(s.getDescription())));
            }
        }


    }

    class GetSensorAsycTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            //create the login in progress dialog and display it progressBar
            progressBar = new ProgressDialog(MapActivity.this);
            progressBar.setIndeterminate(true);
            progressBar.setCancelable(false);
            progressBar.setMessage("Logging In");
            progressBar.show();

        }

        @Override
        protected String doInBackground(String... input) {
            return NetworkingCall.getJSON(AppConstants.SENSOR_URL + input[0]);
            // return "";
        }

        protected void onPostExecute(String result) {

            if (result != null && !result.equalsIgnoreCase("[]")) // correct Credentials
            {
                Log.i("api call", result);
                sensors = JSONParser.parseSensors(result);



                //add all sensors to mapp
                for (Sensor s: sensors){
                    markers.add(map.addMarker(new MarkerOptions()
                            .position(new LatLng(s.getLatitude(), s.getLongitude()))
                            .title(s.getName())
                            .snippet(s.getDescription())));
                }


            } else // incorrect credentials
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No Sensors", Toast.LENGTH_SHORT);
                toast.show();
            }



            // close progress dialog
            progressBar.dismiss();

        }
    }


}