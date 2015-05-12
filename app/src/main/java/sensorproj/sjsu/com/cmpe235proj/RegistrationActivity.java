package sensorproj.sjsu.com.cmpe235proj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Parnit on 5/6/2015.
 */
public class RegistrationActivity extends Activity {

    private ProgressDialog progressBar;
    private  EditText name, email, pass, passtwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        Button createButton = (Button) findViewById(R.id.createButton);


        name = (EditText) findViewById(R.id.nameView);
        email = (EditText) findViewById(R.id.emailView);
        pass  = (EditText) findViewById(R.id.passView);
        passtwo = (EditText) findViewById(R.id.rePassView);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateUserAsycTask().execute();
            }
        });
    }


    class CreateUserAsycTask extends AsyncTask<String, String, String> {
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


            return NetworkingCall.createUser(name.getText().toString(), email.getText().toString(),pass.getText().toString());
        }

        protected void onPostExecute(String result) {
            if(result.contains("<html>"))
                Toast.makeText(getApplicationContext(),"User Already exists. Try again",Toast.LENGTH_SHORT).show();

            else
            {
                Log.i("result", result);
                User user = JSONParser.parseUser(result);
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                intent.putExtra(AppConstants.USER, user);
                startActivity(intent);
                finish();
            }

            // close progress dialog
            progressBar.dismiss();
        }

    }
}

