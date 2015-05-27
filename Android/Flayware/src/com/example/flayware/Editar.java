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

public class Editar extends Activity {

	//Inicializamos variables de la vista
	private Button bmodf;
	private Button bcancel;
	private EditText name;
	private EditText surname;
	private EditText password;
	private EditText passwordagain;
	private long splashDelay = 2000; //2 segundos
	

	//Inicializamos los parametros con los del layout
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar);

		name = (EditText) findViewById(R.id.Name1);
		surname = (EditText) findViewById(R.id.Surname1);
		password = (EditText) findViewById(R.id.NewPasswordText1);
		passwordagain = (EditText) findViewById(R.id.NewPasswordAgain);
		

		bmodf = (Button) findViewById(R.id.bmodf);
		bmodf.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!password.getText().toString()
						.equals(passwordagain.getText().toString())) {
					AlertDialog msj = new AlertDialog.Builder(Editar.this)
							.create();
					msj.setTitle("Error");
					msj.setMessage("Las contraseñas no coinciden");
					msj.show();
				} else {

					DoPOST mDoPOST = new DoPOST(Editar.this, getIntent().getStringExtra("jugador").toString(), UserLogin.md5(password
								.getText().toString()),name.getText().toString(), surname.getText().toString());

						mDoPOST.execute();
						bmodf.setEnabled(false);
						 TimerTask task = new TimerTask() {
					            @Override
					            public void run() {
					                Intent mainIntent = new Intent().setClass(Editar.this, Menu.class);
					                mainIntent.putExtra("jugador", getIntent().getStringExtra("jugador"));
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
		String userToEditar = "";
		String passwordToEditar = "";
		String nameToEditar="";
		String surnameToEditar="";
		String ip = Editar.this.getString(R.string.ip);

		Exception exception = null;

		DoPOST(Context context, String userToEdit, String passwordToEdit, String nameToEdit, String surnameToEdit) {
			mContext = context;
			this.userToEditar = userToEdit;
			this.passwordToEditar = passwordToEdit;
			this.nameToEditar=nameToEdit;
			this.surnameToEditar=surnameToEdit;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("NameToEditar",
						nameToEditar));
				nameValuePairs.add(new BasicNameValuePair("SurnameToEditar",
						surnameToEditar));
				nameValuePairs.add(new BasicNameValuePair("UserToEditar",
						userToEditar));
				nameValuePairs.add(new BasicNameValuePair("PasswordToEditar",
						passwordToEditar));
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/editar.php");
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
			bmodf.setEnabled(true);
			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(Editar.this)
						.create();
				msj.setTitle("Error");
				msj.setMessage("El usuario introducido ya existe o hay un problema en el servidor, intentelo de nuevo");
				msj.show();
			
			} else {
				if(!passwordToEditar.equals("d41d8cd98f00b204e9800998ecf8427e") || !surnameToEditar.equals("") || !nameToEditar.equals("") ){
				AlertDialog msj = new AlertDialog.Builder(Editar.this)
						.create();
				msj.setTitle("Exito");
				msj.setMessage("Usuario "
						+ getIntent().getStringExtra("jugador").toString()
						+ " modificado correctamente");
				msj.show();
				}
				else{
					AlertDialog msj = new AlertDialog.Builder(Editar.this)
					.create();
			msj.setTitle("Error");
			msj.setMessage("Mal introducido datos, intentelo de nuevo");
			msj.show();
				}
			}

		}

	}

}
