package com.example.flayware;

import java.util.ArrayList;
import java.util.List;

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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ganador extends Activity {

	private Button bcancel;
	private TextView puntuacion;
	private TextView nombre;
	private TextView apellidos;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ganador);

		
		puntuacion = (TextView) findViewById(R.id.PuntuacionMostrar);
		nombre = (TextView) findViewById(R.id.NombreMostrar);
		apellidos = (TextView) findViewById(R.id.ApellidosMostrar);
		DoPOST mDoPOST = new DoPOST(Ganador.this, getIntent().getStringExtra("partida"),getIntent().getStringExtra("jugador"));

		mDoPOST.execute();

		bcancel = (Button) findViewById(R.id.volver);
		bcancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Ganador.this,
						ListPartidas.class);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();
			}
		});

	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String jugadorSelec = "";
		String partidaSelec = "";
		String ip = Ganador.this.getString(R.string.ip);

		String puntuacionB = "";
		String nombreB = "";
		String apellidosB = "";

		Exception exception = null;

		DoPOST(Context context, String partidaSelec,String jugadorSelec) {
			mContext = context;
			this.partidaSelec = partidaSelec;
			this.jugadorSelec=jugadorSelec;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("partidaSelec",
						partidaSelec));
				nameValuePairs.add(new BasicNameValuePair("jugadorSelec",
						jugadorSelec));
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/ganador.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);

				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);

				// Retrieve the data from the JSON object
				this.puntuacionB = (String) jsonObject.get("Puntuacion");
				this.nombreB = (String) jsonObject.get("Nombre");
				this.apellidosB = (String) jsonObject.get("Apellidos");
				

			} catch (Exception e) {
				Log.e("ClientServerDemo", "Error:", e);
				exception = e;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid) {
			// Update the UI

			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(Ganador.this).create();
				msj.setTitle("Error");
				msj.setMessage("No se pudieron cargar los detalles de la partida, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);
			Ganador.this.nombre.setText(nombreB);
			Ganador.this.puntuacion.setText(puntuacionB);
			Ganador.this.apellidos.setText(apellidosB);
			

		}

	}




}