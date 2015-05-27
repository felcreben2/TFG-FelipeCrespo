package com.example.flayware;

import java.util.ArrayList;
import java.util.Date;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Partida20 extends Activity {

	ListView list1;
	private TextView pregunta;
	private TextView tiempo;
	private Button atras;
	private String tiempoupdate;
	private TextView seleccionado;
	private Context llevado;
	private String eleccionantes;
	private Integer tiempoBDpasar;
	private CountDownTimer countDownTimer=null;
	private String eleccion;
	private Button siguiente;
	private ArrayList<Respuesta> arrayRespuestas= new ArrayList<Respuesta>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partidaultima);
        seleccionado = (TextView)findViewById(R.id.seleccionado);
		
		pregunta = (TextView) findViewById(R.id.preguntaMostrar);
		tiempo = (TextView) findViewById(R.id.bienv2);
		
		
		
		
		list1= (ListView) findViewById(R.id.list1); 
		list1.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
	
		DoPOST doPost = new DoPOST(Partida20.this,getIntent().getStringExtra("partida"),getIntent().getStringExtra("jugador"));
		doPost.execute();


		atras = (Button) findViewById(R.id.anterior);
		atras.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(Partida20.this,
						Partida19.class);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				i.putExtra("partida", getIntent().getStringExtra("partida"));countDownTimer.cancel();
 				DoPOSTTiempo doPostTiempo = new DoPOSTTiempo(Partida20.this,getIntent().getStringExtra("partida"),getIntent().getStringExtra("jugador"),tiempoBDpasar);
					doPostTiempo.execute();
				
				startActivity(i);
				finish();
			}
		});
		
		siguiente = (Button) findViewById(R.id.siguiente);
		siguiente.setOnClickListener(new OnClickListener() {

 			@Override
 			public void onClick(View v) {

	            	if(eleccion!=null){
	 					DoPOSTSiguiente doPostSiguiente = new DoPOSTSiguiente(Partida20.this,getIntent().getStringExtra("partida"),getIntent().getStringExtra("jugador"),eleccion);
	 					doPostSiguiente.execute();
	            	}
	            	countDownTimer.cancel();
		 				DoPOSTTiempo doPostTiempo = new DoPOSTTiempo(Partida20.this,getIntent().getStringExtra("partida"),getIntent().getStringExtra("jugador"),tiempoBDpasar);
		 					doPostTiempo.execute();
		 					DoPOSTFinalizar doF = new DoPOSTFinalizar(Partida20.this,getIntent().getStringExtra("partida"),getIntent().getStringExtra("jugador"));
		 					doF.execute();
		 					
 				 TimerTask task = new TimerTask() {
 		            @Override
 		            public void run() {
 		 								
 		            	Intent i = new Intent(Partida20.this,
 								ListPartidas.class);
 		            	i.putExtra("jugador", getIntent().getStringExtra("jugador"));
 		 				i.putExtra("partida", getIntent().getStringExtra("partida"));
 		 				
 		 				startActivity(i);
 		 				finish();
 		            }
 		            
 				};
 		        Timer timer = new Timer();
 		        long splashDelay = 5000;
				timer.schedule(task, splashDelay);//Pasado los 3 segundos dispara la tarea
 				
 			}
 		});
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		arrayRespuestas.clear();
		

	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String ip = Partida20.this.getString(R.string.ip);

		String preguntaBD= "";
		String tiempoBD= "";
		Integer tiempoBDusar= 0;
		Integer tiempoBDpasar= 0;
		String partidaSelec= "";
		String jugadorSelec= "";
		// Result data

		String jugador = getIntent().getStringExtra("jugador");
		Exception exception = null;

		DoPOST(Context context,String partidaSelec,String jugadorSelec) {
			mContext = context;
			llevado=context;
			this.partidaSelec = partidaSelec;
			this.jugadorSelec=jugadorSelec;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				// Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// Add more parameters as necessary

				nameValuePairs.add(new BasicNameValuePair("JugadorSelec",
						jugadorSelec));
				nameValuePairs.add(new BasicNameValuePair("PartidaSelec",
						partidaSelec));
				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/partida20.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();


				String result = EntityUtils.toString(entity);
				String[] separada = result.split("]"); 
				String aux= separada[0]+"]";
				String aux2= separada[1]+"]";
				String aux3= separada[2]+"]";
				String aux4= separada[3]+"]";
				// Create a JSON object from the request response
				JSONArray jsonArray = new JSONArray(aux); // Enunciado
				JSONArray jsonArray2 = new JSONArray(aux2); //Tiempo
				JSONArray jsonArray3 = new JSONArray(aux3); //Lista
				JSONArray jsonArray4 = new JSONArray(aux4); //Eleccion
				
				
				// Retrieve the data from the JSON object
					JSONObject jsonObject = jsonArray.getJSONObject(0);
					preguntaBD = (String) jsonObject.get("Pregunta");
					
					JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
					tiempoBD = (String) jsonObject2.get("Tiempo");
				
					
				for (int i = 0; i < jsonArray3.length(); i++) {
					JSONObject jsonObject3 = jsonArray3.getJSONObject(i);
					String nuevo = (String) jsonObject3.get("Nombre");
					String otro = (String) jsonObject3.get("Imagen");
	
					//byte[] byteArray =  Base64.decode(jsonObject.getString("Imagen"), Base64.DEFAULT) ;
				   //byte[] image = jsonObject.getString("Imagen").getBytes();
					byte[] foto = null;
					if(jsonObject3.getString("Imagen")!="[]"){
					 foto =  Base64.decode(jsonObject3.getString("Imagen"), Base64.DEFAULT) ;
					
					}
					//byte[] foto = otro.toString().getBytes("utf-8");
				   Bitmap bmp1 = BitmapFactory.decodeByteArray(foto, 0, foto.length);
					  
					//String otro= (String) jsonObject3.get("Imagen");
					//
					if(!arrayRespuestas.contains(new Respuesta(bmp1, nuevo, 0))){
						arrayRespuestas.add(new Respuesta(bmp1, nuevo, 0));
					}
					}
				if(!jsonArray4.isNull(0)){
				JSONObject jsonObject4 = jsonArray4.getJSONObject(0);
				eleccionantes = (String) jsonObject4.get("Texto");
				}else{
					eleccionantes=null;
				}

			} catch (Exception e) {
				Log.e("ClientServerDemo", "Error:", e);
				exception = e;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid) {

			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(Partida20.this).create();
				msj.setTitle("Error");
				msj.setMessage("Falló al cargar la partida, intentelo de nuevo, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);
			
			if(eleccionantes!=null){
				seleccionado.setText("Has seleccionado: " + eleccionantes);
            	
			}
			
			ListRespuestaAdapter listRespuestaAdapter =new ListRespuestaAdapter(llevado, arrayRespuestas);
			//list1.setAdapter(new ArrayAdapter<String>(Partida.this,R.layout.layout_checkbox,listaPRespuestas));
			list1.setAdapter(listRespuestaAdapter);
//			list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			list1.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
		                int position, long id) {
		            	seleccionado.setText("Has seleccionado: " + arrayRespuestas.get(position).getTexto());
		            	eleccion=arrayRespuestas.get(position).getTexto();
		            }
		          });

			


			Partida20.this.pregunta.setText(preguntaBD);
			Partida20.this.tiempo.setText(tiempoBD);
			tiempoBDusar=Integer.parseInt(tiempoBD);
			countDownTimer = new CountDownTimer(tiempoBDusar*1000, 1000) {
		       	
	            public void onTick(long millisUntilFinished) {
	            	tiempo.setText("" + millisUntilFinished / 1000);
	            	Partida20.this.tiempoBDpasar=(int) (millisUntilFinished / 1000);
	            }
	        
	            public void onFinish() {
	            	//UPDATE TIEMPO.
	            	
	            	Intent i = new Intent(Partida20.this,
							ListPartidas.class);
					i.putExtra("jugador", getIntent().getStringExtra("jugador"));
					i.putExtra("partida", getIntent().getStringExtra("partida"));
					
	            	DoPOSTFinalizar doPOSTFinalizar = new DoPOSTFinalizar(Partida20.this,getIntent().getStringExtra("partida"),getIntent().getStringExtra("jugador"));
	            	doPOSTFinalizar.execute();

					startActivity(i);
					finish();
 					}
	         }.start();

	         
		}
		

	}
	
	
	private class DoPOSTSiguiente extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String ip = Partida20.this.getString(R.string.ip);

		String preguntaBD= "";
		String tiempoBD= "";
		String partidaSelec= "";
		String jugadorSelec= "";
		String eleccionSelec= "";
		// Result data

		String jugador = getIntent().getStringExtra("jugador");
		Exception exception = null;

		DoPOSTSiguiente(Context context,String partidaSelec,String jugadorSelec,String eleccionSelec) {
			mContext = context;
			this.partidaSelec = partidaSelec;
			this.jugadorSelec=jugadorSelec;
			this.eleccionSelec=eleccionSelec;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				// Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// Add more parameters as necessary

				nameValuePairs.add(new BasicNameValuePair("JugadorSelec",
						jugadorSelec));
				nameValuePairs.add(new BasicNameValuePair("PartidaSelec",
						partidaSelec));
				nameValuePairs.add(new BasicNameValuePair("EleccionSelec",
						eleccionSelec));
				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/eleccionultima.php");
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

			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(Partida20.this).create();
				msj.setTitle("Error");
				msj.setMessage("No pudo elegir la eleccion de su respuesta, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);
		}
	}
	
	
	private class DoPOSTTiempo extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String ip = Partida20.this.getString(R.string.ip);

		String preguntaBD= "";
		String tiempoBD= "";
		String partidaSelec= "";
		String jugadorSelec= "";
		String tiempo="";
		// Result data

		String jugador = getIntent().getStringExtra("jugador");
		Exception exception = null;

		DoPOSTTiempo(Context context,String partidaSelec,String jugadorSelec,Integer tiempo) {
			mContext = context;
			this.partidaSelec = partidaSelec;
			this.jugadorSelec=jugadorSelec;
			this.tiempo=Integer.toString(tiempo);
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				// Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// Add more parameters as necessary

				nameValuePairs.add(new BasicNameValuePair("JugadorSelec",
						jugadorSelec));
				nameValuePairs.add(new BasicNameValuePair("PartidaSelec",
						partidaSelec));
				nameValuePairs.add(new BasicNameValuePair("TiempoSelec",
						tiempo));
				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/tiempo.php");
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

			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(Partida20.this).create();
				msj.setTitle("Error");
				msj.setMessage("Fallo al actualizar el tiempo en la BD");
				msj.show();
			}

			super.onPostExecute(valid);
		}
	}
	
	private class DoPOSTFinalizar extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String ip = Partida20.this.getString(R.string.ip);

		String partidaSelec= "";
		String jugadorSelec= "";
		String tiempo="";
		// Result data

		Exception exception = null;

		DoPOSTFinalizar(Context context,String partidaSelec,String jugadorSelec) {
			mContext = context;
			this.partidaSelec = partidaSelec;
			this.jugadorSelec=jugadorSelec;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				// Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// Add more parameters as necessary

				nameValuePairs.add(new BasicNameValuePair("JugadorSelec",
						jugadorSelec));
				nameValuePairs.add(new BasicNameValuePair("PartidaSelec",
						partidaSelec));
				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/finalizarultima.php");
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

			if (exception != null) {
				Toast.makeText(mContext, exception.getMessage(),
						Toast.LENGTH_LONG).show();
				AlertDialog msj = new AlertDialog.Builder(Partida20.this).create();
				msj.setTitle("Error");
				msj.setMessage("Fallo al actualizar la finalización de la partida en la BD");
				msj.show();
			}else{
				AlertDialog msj = new AlertDialog.Builder(Partida20.this)
				.create();
				msj.setTitle("Finalizada la partida con exito. ");
				msj.setMessage(" Puedes ver los resultados en el menu descargas, documento sobre el curso");
				msj.show();
			}

			super.onPostExecute(valid);
		}
	}
	
}
