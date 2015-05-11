package sensorproj.sjsu.com.cmpe235proj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Parnit on 5/9/2015.
 */
public class JSONParser {

    public static ArrayList<Sensor> parseSensors(String json){
        ArrayList<Sensor> sensors = new ArrayList<Sensor>();
        JSONObject s;
        Sensor sensor;

        try {
            JSONArray sensorArray = new JSONArray(json);
            int size =  sensorArray.length();

            for(int count = 0; count < size; count++){
                s = sensorArray.getJSONObject(count);
                sensor = new Sensor();


                if(s.has(AppConstants.SENSOR_ID))
                    sensor.setId(s.getString(AppConstants.SENSOR_ID));
                if(s.has(AppConstants.SENSOR_NAME))
                    sensor.setName(s.getString(AppConstants.SENSOR_NAME));
                if(s.has(AppConstants.SENSOR_USER_ID))
                     sensor.setUserId(s.getString(AppConstants.SENSOR_USER_ID));
                if(s.has(AppConstants.SENSOR_LATITUDE))
                    sensor.setLatitude(s.getDouble(AppConstants.SENSOR_LATITUDE));
                if(s.has(AppConstants.SENSOR_LONGITUDE))
                    sensor.setLongitude(s.getDouble(AppConstants.SENSOR_LONGITUDE));

                sensors.add(sensor);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sensors;
    }

    public static User parseUser(String json){
        User user =  new User();;

        try {
            JSONObject jsonObj = new JSONObject(json);

            if (jsonObj.has(AppConstants.USER_ID))
                user.setId(jsonObj.getString(AppConstants.USER_ID));
            if (jsonObj.has(AppConstants.USER_FIRSTNAME))
                user.setFirstName(jsonObj.getString(AppConstants.USER_FIRSTNAME));
            if (jsonObj.has(AppConstants.USER_LASTNAME))
                user.setLastName(jsonObj.getString(AppConstants.USER_LASTNAME));
            if (jsonObj.has(AppConstants.USER_EMAIL))
                 user.setEmail(jsonObj.getString(AppConstants.USER_EMAIL));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
