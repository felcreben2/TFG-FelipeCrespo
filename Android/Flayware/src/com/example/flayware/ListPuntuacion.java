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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListPuntuacion extends ListActivity {


	private Button volver;	
	private ArrayList<Puntuacion> arrayPuntuacion= new ArrayList<Puntuacion>();
	private ListView listViewPuntuacion;
	private Context llevado;
	



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listapuntuacion);
		DoPOST doPost = new DoPOST(ListPuntuacion.this);
		doPost.execute();

		volver = (Button) findViewById(R.id.bvolver);
		volver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(ListPuntuacion.this,
						Menu.class);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		arrayPuntuacion.clear();
		setListAdapter(new ArrayAdapter<Puntuacion>(ListPuntuacion.this,
				android.R.layout.simple_list_item_1, arrayPuntuacion));

	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String ip = ListPuntuacion.this.getString(R.string.ip);

		List<String> puntuacion = new ArrayList<String>();
		// Result data

		Exception exception = null;

		DoPOST(Context context) {
			mContext = context;
			llevado=context;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				// Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/listaPuntuacion.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				String result = EntityUtils.toString(entity);
				// Create a JSON object from the request response
				JSONArray jsonArray = new JSONArray(result);

				// Retrieve the data from the JSON object
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String nombre1 = (String) jsonObject.get("Puntuacion");
					String nombre2 = (String) jsonObject.get("Nombre");
					String nombre3 = (String) jsonObject.get("Apellido");
					Double numero = Double.parseDouble(nombre1);
					Puntuacion p1 = null;
					if(i==0 ||i==1 || i==2 ||i==3 ||i==4 || i==5 ||i==6 ||i==7 || i==8){
						if(i==0){
							p1=new Puntuacion(R.drawable.numuno, nombre2, nombre3, numero);
						}if(i==1){
						p1=new Puntuacion(R.drawable.numdosdos, nombre2, nombre3, numero);
						}if(i==2){
						p1=new Puntuacion(R.drawable.numtres, nombre2, nombre3, numero);
						}
						if(i==3){
							p1=new Puntuacion(R.drawable.numcuatro, nombre2, nombre3, numero);
						}
						if(i==4){
							p1=new Puntuacion(R.drawable.numcinco, nombre2, nombre3, numero);
						}
						if(i==5){
							p1=new Puntuacion(R.drawable.numseis, nombre2, nombre3, numero);
						}
						if(i==6){
							p1=new Puntuacion(R.drawable.numsiete, nombre2, nombre3, numero);
						}
						if(i==7){
							p1=new Puntuacion(R.drawable.numocho, nombre2, nombre3, numero);
						}
						if(i==8){
							p1=new Puntuacion(R.drawable.numnueve, nombre2, nombre3, numero);
						}
					}
						else{
						p1=new Puntuacion(R.drawable.masdiez, nombre2, nombre3, numero);

					}
					arrayPuntuacion.add(p1);
					
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
				AlertDialog msj = new AlertDialog.Builder(ListPuntuacion.this).create();
				msj.setTitle("Error");
				msj.setMessage("No pudieron cargarse los cursos, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);
			ListPuntuacionAdapter listPuntuacionAdapter =new ListPuntuacionAdapter(llevado, arrayPuntuacion);
			setListAdapter(listPuntuacionAdapter);

		

		}
	}
}
