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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
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
import android.widget.TextView;
import android.widget.Toast;

public class RetosDetails extends Activity {

	private Button bcancel;
	private Button iniciarreto;
	private TextView nombreReto;
	private TextView tiempoMaximo;
	private TextView fechaValida;
	private long splashDelay = 5000;
	private String jugador;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.retodetails);

		
		nombreReto = (TextView) findViewById(R.id.NombreRetoMostrar);
		tiempoMaximo = (TextView) findViewById(R.id.TiempoMaxMostrar);
		fechaValida = (TextView) findViewById(R.id.FechaMaxMostrar);
		DoPOST mDoPOST = new DoPOST(RetosDetails.this, getIntent().getStringExtra("reto"));

		mDoPOST.execute();

		bcancel = (Button) findViewById(R.id.bvolver);
		bcancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(RetosDetails.this,
						ListRetos.class);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();
			}
		});

		iniciarreto= (Button) findViewById(R.id.iniciarreto);
		iniciarreto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DoPOSTIniciarReto mDoPOST2 = new DoPOSTIniciarReto(RetosDetails.this, getIntent().getStringExtra("reto"),getIntent().getStringExtra("jugador"));

				mDoPOST2.execute();
				iniciarreto.setEnabled(false);
				 TimerTask task = new TimerTask() {
	            @Override
	            public void run() {
	            	Intent i = new Intent(RetosDetails.this,
							Menu.class);
					i.putExtra("jugador", getIntent().getStringExtra("jugador"));
					startActivity(i);
	                finish();
	            }
	            
	        };
	        Timer timer = new Timer();
	        timer.schedule(task, splashDelay);//Pasado los 3 segundos dispara la tarea
	        
	}
		});
	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String nombreRetoSelec = "";
		String ip = RetosDetails.this.getString(R.string.ip);

		String nombreRetoR = "";
		String tiempoMax = "";
		String fechaValida = "";

		Exception exception = null;

		DoPOST(Context context, String nombreRetoSelecc) {
			mContext = context;
			this.nombreRetoSelec = nombreRetoSelecc;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("nombreRetoSelec",
						nombreRetoSelec));
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/retoDetails.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);

				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);

				// Retrieve the data from the JSON object
				this.nombreRetoR = (String) jsonObject.get("Nombre");
				this.tiempoMax = (String) jsonObject.get("TiempoMax");
				this.fechaValida = (String) jsonObject.get("FechaValida");
				

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
				AlertDialog msj = new AlertDialog.Builder(RetosDetails.this).create();
				msj.setTitle("Error");
				msj.setMessage("No se pudieron cargar los detalles del reto, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);
			RetosDetails.this.nombreReto.setText(nombreRetoR);
			RetosDetails.this.tiempoMaximo.setText(tiempoMax);
			RetosDetails.this.fechaValida.setText(fechaValida);
			

		}

	}

	private class DoPOSTIniciarReto extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String nombreRetoSelec = getIntent().getStringExtra("reto");
		String jugador = getIntent().getStringExtra("jugador");
		String ip = RetosDetails.this.getString(R.string.ip);


		String compro;
		String correo1;
		String correo2;
		Integer prueba;
		Exception exception = null;

		DoPOSTIniciarReto(Context context, String nombreRetoSelecc,String jugador) {
			mContext = context;
			this.nombreRetoSelec = nombreRetoSelecc;
			this.jugador = jugador;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("NombreToReto",
						nombreRetoSelec));
				nameValuePairs.add(new BasicNameValuePair("JugadorToReto",
						jugador));
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/iniciarReto.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				if(!result.equals("")){

					if(result.contains(".com")){
						JSONArray jsonArray = new JSONArray(result);

						// Retrieve the data from the JSON object
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							if(i==0){
								correo1=(String) jsonObject.get("Correo");
							}
							if(i==1){
								correo2=(String) jsonObject.get("Correo");
							}
						}
						DoPOSTEmail doPostEmail = new DoPOSTEmail(RetosDetails.this, correo1,correo2);

						doPostEmail.execute();
						this.compro = "";
					}else{
			
					JSONObject jsonObject = new JSONObject(result);
					this.compro="fallo";}
					
					

					// Retrieve the data from the JSON object
				}else{
					this.compro = result;
				}

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
			iniciarreto.setEnabled(true);
			
			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(RetosDetails.this).create();
				msj.setTitle("Error");
				msj.setMessage("No pudo inscrbrirse al reto,intentelo de nuevo o asegurese que no esta ya inscrito a este reto, será redireccionado al menu");
				msj.show();
			}else {
				if(compro.equals("")){
				AlertDialog msj = new AlertDialog.Builder(RetosDetails.this)
				.create();
				msj.setTitle("Exito");
				msj.setMessage("Buenas "+getIntent().getStringExtra("jugador").toString()+
				 "se le ha sido inscrito al reto: "+getIntent().getStringExtra("reto").toString() +", espere el correo y podrá comenzar a realizar una partida");
				msj.show();
				}else{
					AlertDialog msj = new AlertDialog.Builder(RetosDetails.this).create();
					msj.setTitle("Error");
					msj.setMessage("No pudo inscrbrirse al reto,intentelo de nuevo o asegurese que no esta ya inscrito a este reto, será redireccionado al menu");
					msj.show();
				}
		
	}


		}

	}


	private class DoPOSTEmail extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String nombreRetoSelec = getIntent().getStringExtra("reto");
		String jugador = getIntent().getStringExtra("jugador");
		String ip = RetosDetails.this.getString(R.string.ip);


		String compro;
		String correo1;
		String correo2;
		Integer prueba;
		Exception exception = null;

		DoPOSTEmail(Context context, String correo1,String correo2) {
			mContext = context;
			this.correo1 = correo1;
			this.correo2 = correo2;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("Correo1",
						correo1));
				nameValuePairs.add(new BasicNameValuePair("Correo2",
						correo2));
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/email.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				

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
			iniciarreto.setEnabled(true);
			
			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(RetosDetails.this).create();
				msj.setTitle("Error");
				msj.setMessage("No pudo enviar el mensaje al usuario");
				msj.show();
			}

		}

	}
	
}