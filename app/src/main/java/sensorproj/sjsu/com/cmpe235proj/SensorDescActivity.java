package sensorproj.sjsu.com.cmpe235proj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Parnit on 5/12/2015.
 */
public class SensorDescActivity  extends Activity {
    Sensor sensor;
    private ArrayList<SensorData> data = new ArrayList<SensorData>();
    private ProgressDialog progressBar;
    private ListView dataList;

    private TextView sensorname, sensorDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_detail);
        Intent intent = getIntent();
        sensor =  (Sensor) intent.getSerializableExtra(AppConstants.SENSOR);

        dataList = (ListView) findViewById(R.id.sensorDataList);
        sensorname = (TextView) findViewById(R.id.sensorName);
        sensorDesc = (TextView) findViewById(R.id.sensorDesc);

        sensorname.setText("Sensor Name: " + sensor.getName());
        sensorDesc.setText("Description: "+ sensor.getDescription());

        new GetSensorDataAsycTask().execute(sensor.getId());
    }



    class GetSensorDataAsycTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            //create the login in progress dialog and display it progressBar
            progressBar = new ProgressDialog(SensorDescActivity.this);
            progressBar.setIndeterminate(true);
            progressBar.setCancelable(false);
            progressBar.setMessage("Getting Sensor Data");
            progressBar.show();

        }

        @Override
        protected String doInBackground(String... input) {
            Log.i("link" , AppConstants.GET_SENSOR_INFO+ input[0]);
            return NetworkingCall.getJSON(AppConstants.GET_SENSOR_INFO+ input[0]);
            // return "";
        }

        protected void onPostExecute(String result) {

            if (result != null && !result.equalsIgnoreCase("[]")) // correct Credentials
            {
                Log.i("api call", result);
                data = JSONParser.parseSensorData(result);

                ArrayList<Sensor> sensors = new ArrayList<Sensor>();
                sensors.add(sensor);
                dataList.setAdapter(new DataListAdapter(SensorDescActivity.this, data, sensors, sensor));

            } else // incorrect credentials
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No Sensor Data", Toast.LENGTH_SHORT);
                toast.show();
            }



            // close progress dialog
            progressBar.dismiss();

        }
    }
}
