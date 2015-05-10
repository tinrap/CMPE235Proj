/**
 * 
 */
package sensorproj.sjsu.com.cmpe235proj;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Parnit
 * 
 */
public class LoginActivity extends Activity {
	protected EditText usernameField;
	protected EditText passwordField;
	private ProgressDialog progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);

		Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.createAccountButton);
		usernameField = (EditText) findViewById(R.id.usernameField);
		passwordField = (EditText) findViewById(R.id.passwordField);

		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = usernameField.getText().toString().trim();
				String password = passwordField.getText().toString().trim();

				if (username.length() == 0 || password.length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Please enter in username and password",
							Toast.LENGTH_SHORT).show();
					return;
				}

				new LoginAsycTask().execute(username,password);
			}

		});

        registerButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }

        });
	}

	class LoginAsycTask extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			
			  //create the login in progress dialog and display it progressBar
			progressBar  = new ProgressDialog(LoginActivity.this);
			  progressBar.setIndeterminate(true);
			  progressBar.setCancelable(false);
			  progressBar.setMessage("Logging In"); progressBar.show();
			 
		}

		@Override
		protected String doInBackground(String... input) {
			return NetworkingCall.login(input[0], input[1]);
           // return "";
		}

		protected void onPostExecute(String result) {

			if (result != null && !result.contains("<html>")) // correct Credentials
			{
               Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

			} else // incorrect credentials
			{
				Toast toast = Toast.makeText(getApplicationContext(),
						"Incorrect Credentials", Toast.LENGTH_SHORT);
				toast.show();

				// clear password field
				passwordField.setText("");
			}



            // close progress dialog
            progressBar.dismiss();
			
		}
	}
}
