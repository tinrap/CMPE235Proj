package sensorproj.sjsu.com.cmpe235proj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Parnit on 4/28/2015.
 */
public class SensorListActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        Intent intent = getIntent();
        ArrayList<Sensor> sensors = (ArrayList<Sensor>) intent.getSerializableExtra(AppConstants.SENSOR_LIST);

        ListView listview = (ListView) findViewById(R.id.sensorListView);
        listview.setAdapter(new ListAdapter(this, sensors));
    }
}
