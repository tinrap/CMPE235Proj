package sensorproj.sjsu.com.cmpe235proj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Parnit on 4/25/2015.
 * Description: Adapter class to set up home page
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Sensor> sensors;
    private User user;

    public ImageAdapter(Context c , ArrayList<Sensor> sensors, User user) {
        mContext = c;
        this.sensors = sensors;
        this.user = user;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView title;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            // if it's not recycled, initialize some attributes
            convertView = mInflater.inflate(R.layout.grid_item, parent, false);
        }

        imageView = (ImageView) convertView.findViewById(R.id.imageView);
        imageView.setImageResource(mThumbIds[position]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position ==2){
                    Intent intent = new Intent(mContext,SensorListActivity.class);
                    intent.putExtra(AppConstants.SENSOR_LIST,sensors);
                    intent.putExtra(AppConstants.USER, user);
                    mContext.startActivity(intent);
                }
                else{

                    Toast.makeText(mContext,titles[position],
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

       title =(TextView) convertView.findViewById(R.id.title);
        title.setText(titles[position]);
        return convertView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.home,R.drawable.register,
            R.drawable.gps, R.drawable.remote_control,
            R.drawable.monito, R.drawable.database
    };

    private String[] titles = {
           "Home", "Register ", "Location Based View",
            "Sensor Control", "Sensor Monitor", "Data Collection"
    };

}
