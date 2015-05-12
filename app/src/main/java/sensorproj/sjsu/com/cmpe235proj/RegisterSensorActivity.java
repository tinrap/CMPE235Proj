package sensorproj.sjsu.com.cmpe235proj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Parnit on 5/11/2015.
 */
public class RegisterSensorActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private ProgressDialog progressBar;
    private EditText name, desc, type;
    private GoogleApiClient mGoogleApiClient;
    private Double longitude, latitude;
    private User user;

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_sensor);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(AppConstants.USER);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        Button createButton = (Button) findViewById(R.id.createButton);


        name = (EditText) findViewById(R.id.sensorNameView);
        desc = (EditText) findViewById(R.id.sensorDescView);
        type  = (EditText) findViewById(R.id.sensorTypeView);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateSensorAsycTask().execute();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            // Blank for a moment...
        }
        else {
            handleNewLocation(location);
        };
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void handleNewLocation(Location location) {
        Log.d("APP", location.toString());
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    class CreateSensorAsycTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            //create the login in progress dialog and display it progressBar
            progressBar = new ProgressDialog(RegisterSensorActivity.this);
            progressBar.setIndeterminate(true);
            progressBar.setCancelable(false);
            progressBar.setMessage("Logging In");
            progressBar.show();

        }

        @Override
        protected String doInBackground(String... input) {


            return NetworkingCall.createSensor(name.getText().toString(), user.getId(), desc.getText().toString(),type.getText().toString(),latitude,longitude);
        }

        protected void onPostExecute(String result) {
            if(result.contains("<html>"))
                Toast.makeText(getApplicationContext(), "Error adding sensor", Toast.LENGTH_SHORT).show();

            else
            {
                Log.i("result", result);
               /* User user = JSONParser.parseUser(result);
                Intent intent = new Intent(RegisterSensorActivity.this,MainActivity.class);
                intent.putExtra(AppConstants.USER, user);
                startActivity(intent);
                finish();*/
            }

            // close progress dialog
            progressBar.dismiss();
        }

    }
}
