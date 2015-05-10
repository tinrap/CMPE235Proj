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
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.HttpResponseException;
        import org.apache.http.client.ResponseHandler;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.BasicResponseHandler;
        import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author Parnit
 *
 */
public class NetworkingCall {
    private final String LOGIN_URL="";


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
        Log.i("url",AppConstants.LOGIN_URL+email);
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

