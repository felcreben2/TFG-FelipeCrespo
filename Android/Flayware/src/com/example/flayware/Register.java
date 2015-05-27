package com.example.flayware;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

	//Inicializamos variables de la vista
	private Button bregister;
	private Button bcancel;
	private EditText name;
	private EditText surname;
	private EditText email;
	private EditText user;
	private EditText password;
	private EditText passwordagain;
	private long splashDelay = 5000; //3 segundos
	

	//Inicializamos los parametros con los del layout
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		name = (EditText) findViewById(R.id.Name1);
		surname = (EditText) findViewById(R.id.Surname1);
		email = (EditText) findViewById(R.id.Email1);
		user = (EditText) findViewById(R.id.NewUser);
		password = (EditText) findViewById(R.id.NewPasswordText1);
		passwordagain = (EditText) findViewById(R.id.NewPasswordAgain);
		

		bregister = (Button) findViewById(R.id.AcceptRegister);
		bregister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!password.getText().toString()
						.equals(passwordagain.getText().toString())) {
					AlertDialog msj = new AlertDialog.Builder(Register.this)
							.create();
					msj.setTitle("Error");
					msj.setMessage("Las contraseñas no coinciden");
					msj.show();
				} else {

					DoPOST mDoPOST = new DoPOST(Register.this, user
								.getText().toString(), UserLogin.md5(password
								.getText().toString()),name.getText().toString(), surname.getText().toString(), email.getText().toString());

						mDoPOST.execute();
						bregister.setEnabled(false);
						 TimerTask task = new TimerTask() {
					            @Override
					            public void run() {
					                Intent mainIntent = new Intent().setClass(Register.this, PantallaInicio.class);
					                startActivity(mainIntent);
					                finish();
					            }
					            
					        };
					        Timer timer = new Timer();
					        timer.schedule(task, splashDelay);//Pasado los 3 segundos dispara la tarea
					        
					

				}
			}
		});
		bcancel = (Button) findViewById(R.id.Cancel);
		bcancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String userToRegister = "";
		String passwordToRegister = "";
		String nameToRegister="";
		String surnameToRegister="";
		String emailToRegister="";
		String ip = Register.this.getString(R.string.ip);

		Exception exception = null;

		DoPOST(Context context, String userToRegister, String passwordToRegister, String nameToRegister, String surnameToRegister, String emailToRegister) {
			mContext = context;
			this.userToRegister = userToRegister;
			this.passwordToRegister = passwordToRegister;
			this.emailToRegister=surnameToRegister;
			this.nameToRegister=nameToRegister;
			this.surnameToRegister=emailToRegister;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("NameToRegister",
						nameToRegister));
				nameValuePairs.add(new BasicNameValuePair("SurnameToRegister",
						surnameToRegister));
				nameValuePairs.add(new BasicNameValuePair("EmailToRegister",
						emailToRegister));
				nameValuePairs.add(new BasicNameValuePair("UserToRegister",
						userToRegister));
				nameValuePairs.add(new BasicNameValuePair("PasswordToRegister",
						passwordToRegister));
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/register.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

			} catch (Exception e) {
				Log.e("ClientServerDemo", "Error:", e);
				exception = e;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid) {
			// Update the UI

			super.onPostExecute(valid);
			bregister.setEnabled(true);
			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(Register.this)
						.create();
				msj.setTitle("Error");
				msj.setMessage("El usuario introducido ya existe o hay un problema en el servidor, intentelo de nuevo");
				msj.show();
			
			} else {
				if(!userToRegister.equals("") ||  !passwordToRegister.equals("d41d8cd98f00b204e9800998ecf8427e") || !surnameToRegister.equals("") || !nameToRegister.equals("") || !emailToRegister.equals("")){
				AlertDialog msj = new AlertDialog.Builder(Register.this)
						.create();
				msj.setTitle("Exito");
				msj.setMessage("Usuario "
						+ user.getText().toString()
						+ " creado correctamente, por favor acceda a la aplicación para continuar");
				msj.show();
				}
				else{
					AlertDialog msj = new AlertDialog.Builder(Register.this)
					.create();
			msj.setTitle("Error");
			msj.setMessage("Mal introducido datos, intentelo de nuevo");
			msj.show();
				}
			}

		}

	}

}
