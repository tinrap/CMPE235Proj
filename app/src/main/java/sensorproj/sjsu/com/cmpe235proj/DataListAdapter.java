package sensorproj.sjsu.com.cmpe235proj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Parnit on 5/12/2015.
 */
public class DataListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SensorData> data;
    private ArrayList<Sensor> sensors;
    private Sensor sensor;

    public DataListAdapter(Context c, ArrayList<SensorData> data, ArrayList<Sensor> sensors, Sensor sensor) {
        mContext = c;
        this.data= data;
        this.sensor = sensor;
        this.sensors = sensors;
    }


    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView name, desc, location;
        LinearLayout row;
        final SensorData entry;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            // if it's not recycled, initialize some attributes
            convertView = mInflater.inflate(R.layout.list_row, parent, false);
        }

        //set up a sensors list fields
        entry = data.get(position);

        name = (TextView) convertView.findViewById(R.id.name);
        desc = (TextView) convertView.findViewById(R.id.desc);
        location = (TextView) convertView.findViewById(R.id.location);
        row = (LinearLayout) convertView.findViewById(R.id.row);

        name.setText("Data: "+ entry.getData());
        desc.setText("Status: " + entry.getStatus());

        Date date = new Date(entry.getTime());
        date.setTime(entry.getTime());
        location.setText("Time: " + date.toString());

        //if sensor is clicked, show it on the map
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MapActivity.class);
                intent.putExtra(AppConstants.SENSOR, sensor);
                intent.putExtra(AppConstants.SENSOR_LIST, sensors);
                intent.putExtra(AppConstants.MAP_TYPE, AppConstants.FROM_LIST);
                mContext.startActivity(intent);

            }
        });
        return convertView;
    }

}
