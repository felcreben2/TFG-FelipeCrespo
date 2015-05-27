package com.example.flayware;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

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

//Clase para Login en la aplicación de usuario.
public class UserLogin extends Activity {
	private EditText username;
	private EditText password;
	private Button login;
	private String passwordBD = "";
	private String userBD = "";

	//Crea la vista de login. Para ello accede a layout donde esta la vista.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);

		username = (EditText) findViewById(R.id.UserLoginText);
		password = (EditText) findViewById(R.id.UserPasswordText);
		login = (Button) findViewById(R.id.LoginButton);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DoPOST mDoPOST = new DoPOST(UserLogin.this, username.getText()
						.toString());

				mDoPOST.execute();
				login.setEnabled(false);

			}
		});

	}

	//Método para comprobar se cumple la contraseña corresponde. Usamos método aprendido en Seguridad.
	public static String md5(String s) {
		 final String MD5 = "MD5";
		    try {
		        // Create MD5 Hash
		        MessageDigest digest = java.security.MessageDigest
		                .getInstance(MD5);
		        digest.update(s.getBytes());
		        byte messageDigest[] = digest.digest();

		        // Create Hex String
		        StringBuilder hexString = new StringBuilder();
		        for (byte aMessageDigest : messageDigest) {
		            String h = Integer.toHexString(0xFF & aMessageDigest);
		            while (h.length() < 2)
		                h = "0" + h;
		            hexString.append(h);
		        }
		        return hexString.toString();

		    } catch (NoSuchAlgorithmException e) {
		        e.printStackTrace();
		    }
		    return "";
	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String jugadorSelec = "";
		String ip = UserLogin.this.getString(R.string.ip);
		String userres;
		String passres;

		Exception exception = null;

		DoPOST(Context context, String jugadorSelec) {
			mContext = context;
			this.jugadorSelec = jugadorSelec;
		}

		//Metodo de clase asincrona: donde doInBackground (Parámetros ...), invocados en el subproceso de fondo inmediatamente después OnPreExecute () termina de ejecutarse. Este paso se utiliza para realizar el fondo de cálculo que puede tomar mucho tiempo. Los parámetros de la tarea asíncrona se pasan a este paso. El resultado del cálculo debe ser devuelto por este paso y se pasará de nuevo a la última etapa. Este paso también puede utilizar publishProgress (Progreso ...) publiquen una o más unidades de progreso. Estos valores se publicarán en el hilo de interfaz de usuario, en el paso onProgressUpdate (Progreso ...).
		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("JugadorSelec",
						jugadorSelec));

				// Crea httpresquest y establece de tiempos de espera
				HttpParams httpParameters = new BasicHttpParams();

				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				
				//Utiliza el servicio php para obtener el JSON
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/login.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				String result = EntityUtils.toString(entity);

				// Crea el JSON object desde la respuesta de request
				JSONObject jsonObject = new JSONObject(result);

				userres = (String) jsonObject.get("username");
				passres = (String) jsonObject.get("password");

			} catch (Exception e) {
				Log.e("ClientServerDemo", "Error:", e);
				exception = e;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid) {
			// Update the UI
			userBD = this.userres;
			passwordBD = this.passres;

			
			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(UserLogin.this).create();
				msj.setTitle("Error");
				msj.setMessage("Revise los datos introducidos");
				msj.show();
			}

			super.onPostExecute(valid);
			login.setEnabled(true);
			if(passres!=null){
			if (passwordBD.equals(md5(password.getText().toString()))) {
				finish();
				launchMenu(null);
			} 
			if (!passwordBD.equals(md5(password.getText().toString()))){
				AlertDialog msj = new AlertDialog.Builder(UserLogin.this).create();
				msj.setTitle("Error");
				msj.setMessage("Revise los datos introducidos contraseña no se corresponde para este usuario");
				msj.show();
			}
			}
			else{
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(UserLogin.this).create();
				msj.setTitle("Error");
				msj.setMessage("Revise los datos introducidos");
				msj.show();
			}

		}

	}


	public void backToIndex(View view) {
		finish();
	}
	
	public void launchMenu(View view) {
		Intent i = new Intent(this, Menu.class);
		i.putExtra("jugador", userBD);
		startActivity(i);
		}

}
