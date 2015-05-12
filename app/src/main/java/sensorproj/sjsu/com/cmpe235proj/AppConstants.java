package sensorproj.sjsu.com.cmpe235proj;

/**
 * Created by Parnit on 4/28/2015.
 */
public  class  AppConstants {
    public static String APP_PREFS = "apppreferencesstring";
    public static String SENSOR ="sensor";
    public static String SENSOR_LIST ="sensor list";

    public static String SENSOR_URL = "http://ec2-52-24-164-202.us-west-2.compute.amazonaws.com:8080/Sensitouch/rest/sensitouch/sensor/userid?userId=";
    public static String LOGIN_URL = "http://ec2-52-24-164-202.us-west-2.compute.amazonaws.com:8080/Sensitouch/rest/sensitouch/user/byemail?email=";
    public static String  CREATE_USER_URL = "http://ec2-52-24-164-202.us-west-2.compute.amazonaws.com:8080/Sensitouch/rest/sensitouch/user/create";
    public static String CREATE_SENSOR_URL = "http://ec2-52-24-164-202.us-west-2.compute.amazonaws.com:8080/Sensitouch/rest/sensitouch/sensor/create";

    public static String SENSOR_ID = "id";
    public static String SENSOR_NAME ="sensorName";
    public static String SENSOR_USER_ID = "userid";
    public static String SENSOR_LATITUDE = "latitude";
    public static String SENSOR_LONGITUDE = "longitude";

    public static String USER = "user";
    public static String USER_ID = "userId";
    public static String USER_FIRSTNAME = "firstName";
    public static String USER_LASTNAME = "lastName";
    public static String USER_EMAIL = "emailId";

    public static String USER_PASS = "password";

    public static long LOCATION_REFRESH_TIME = 100;
    public static float LOCATION_REFRESH_DISTANCE = 100;




}
