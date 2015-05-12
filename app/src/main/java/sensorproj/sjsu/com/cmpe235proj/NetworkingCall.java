package sensorproj.sjsu.com.cmpe235proj;

/**
 * Created by Parnit on 5/9/2015.
 */
/**
 *
 */

        import android.util.Log;

        import java.io.IOException;
        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.HttpResponseException;
        import org.apache.http.client.ResponseHandler;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.BasicResponseHandler;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicHeader;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.protocol.HTTP;
        import org.apache.http.util.EntityUtils;
        import org.json.JSONException;
        import org.json.JSONObject;

/**
 * @author Parnit
 *
 */
public class NetworkingCall {

    /**
     * checks user credentials to database
     * @param email user's email
     * @param password user's password
     * @return user information or nothing
     */
    public static String login(String email, String password)
    {
        //variables declared
        String message ="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(AppConstants.LOGIN_URL+email);

        try {
            //add user information to post
           // List<NameValuePair> data = new ArrayList<NameValuePair>(1);
            //data.add(new BasicNameValuePair("email",email));
            //data.add(new BasicNameValuePair("password",password));
          //  httppost.setEntity(new UrlEncodedFormEntity(data));

            //HttpResponse response = httpclient.execute(httppost);

            ResponseHandler<String> handler = new BasicResponseHandler();
            message = httpclient.execute(httpget,handler);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(HttpResponseException e){
            message = null;
        }
        catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return message;
    }

    public static String createUser(String name, String email, String pass)
    {
        //variables declared
        String message ="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(AppConstants.CREATE_USER_URL);

        try {

            String[] split = name.split(" ");

            JSONObject user = new JSONObject();
            user.put(AppConstants.USER_FIRSTNAME,split[0]);
            user.put(AppConstants.USER_LASTNAME,split[1]);
            user.put(AppConstants.USER_EMAIL,email);
            user.put(AppConstants.USER_PASS,pass);

            StringEntity se = new StringEntity(user.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));


            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);

            message = EntityUtils.toString(response.getEntity());
            /*
            //add user information to post
            List<NameValuePair> data = new ArrayList<NameValuePair>(1);

            data.add(new BasicNameValuePair(AppConstants.USER_FIRSTNAME,split[0]));
            data.add(new BasicNameValuePair(AppConstants.USER_LASTNAME,split[1]));
            data.add(new BasicNameValuePair(AppConstants.USER_EMAIL,email));
            data.add(new BasicNameValuePair(AppConstants.USER_PASS,pass));
             httppost.setEntity(new UrlEncodedFormEntity(data));

            //HttpResponse response = httpclient.execute(httppost);

            ResponseHandler<String> handler = new BasicResponseHandler();
            message = httpclient.execute(httppost,handler);
            */

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(HttpResponseException e){
            message = null;
        }
        catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return message;
    }

    public static String createSensor(String name, String userid, String desc, String type, double latitude , double longitude)
    {
        //variables declared
        String message ="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(AppConstants.CREATE_SENSOR_URL);

        try {

            JSONObject sensor = new JSONObject();
            sensor.put(AppConstants.SENSOR_NAME,name);
            sensor.put(AppConstants.SENSOR_USER_ID,userid);
            sensor.put(AppConstants.SENSOR_LATITUDE,latitude);
            sensor.put(AppConstants.SENSOR_LONGITUDE,longitude);

            StringEntity se = new StringEntity(sensor.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));


            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);

            message = EntityUtils.toString(response.getEntity());


        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(HttpResponseException e){
            message = null;
        }
        catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.i("Results",message);
        return message;
    }

    public static String getJSON(String url){
        //variables declared
        String message ="";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httppost = new HttpGet(url);

        try {
            //set up response handler and execute post
            ResponseHandler<String> handler = new BasicResponseHandler();
            message = httpclient.execute(httppost,handler);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return message;

    }

}

