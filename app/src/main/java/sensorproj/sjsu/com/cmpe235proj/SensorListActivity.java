package sensorproj.sjsu.com.cmpe235proj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Parnit on 4/28/2015.
 */
public class SensorListActivity extends Activity {

    private ProgressDialog progressBar;
    private  ArrayList<Sensor> sensors;
    private  ListView listview;
    private User user;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(AppConstants.USER);
        //sensors = (ArrayList<Sensor>) intent.getSerializableExtra(AppConstants.SENSOR_LIST);

        listview = (ListView) findViewById(R.id.sensorListView);


        new GetSensorAsycTask().execute(user.getId());
    }


    class GetSensorAsycTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            //create the login in progress dialog and display it progressBar
            progressBar = new ProgressDialog(SensorListActivity.this);
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


                listview.setAdapter(new ListAdapter(SensorListActivity.this, sensors));

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
