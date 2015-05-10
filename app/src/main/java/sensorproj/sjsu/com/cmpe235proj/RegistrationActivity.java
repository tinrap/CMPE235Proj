package sensorproj.sjsu.com.cmpe235proj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Parnit on 5/6/2015.
 */
public class RegistrationActivity extends Activity {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        Button createButton = (Button) findViewById(R.id.createButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginAsycTask().execute();
            }
        });
    }


    class LoginAsycTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {

            //create the login in progress dialog and display it progressBar
            progressBar = new ProgressDialog(RegistrationActivity.this);
            progressBar.setIndeterminate(true);
            progressBar.setCancelable(false);
            progressBar.setMessage("Logging In");
            progressBar.show();

        }

        @Override
        protected String doInBackground(String... input) {
            //return NetworkingCall.getJSON(AppConstants.LOGIN_URL + input[0]);
            return "";
        }

        protected void onPostExecute(String result) {

            // close progress dialog
            progressBar.dismiss();

            Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}

