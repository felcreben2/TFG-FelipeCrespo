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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListPartidas extends Activity {

	private List<String> listaPartidas = new ArrayList<String>();
	private List<String> listaPartidas2 = new ArrayList<String>();
	private List<String> listaPartidas3 = new ArrayList<String>();
	ListView list1;
	ListView list2;
	ListView list3;
	private Button volver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listapartidas);
		
		list1= (ListView) findViewById(R.id.list1); 
		list2= (ListView) findViewById(R.id.list2); 
		list3= (ListView) findViewById(R.id.list3); 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaPartidas);
		list1.setAdapter(adapter);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaPartidas2);
		list2.setAdapter(adapter2);
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaPartidas3);
		list3.setAdapter(adapter3);
		DoPOST doPost = new DoPOST(ListPartidas.this);
		doPost.execute();

		
		list1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				String entry = (String) parent.getItemAtPosition(position);
				Intent i = new Intent(ListPartidas.this,
						Partida.class);
				i.putExtra("partida", entry);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();

			};
		});
		
		list3.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				String entry = (String) parent.getItemAtPosition(position);
				Intent i = new Intent(ListPartidas.this,
						Ganador.class);
				i.putExtra("partida", entry);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();

			};
		});

		volver = (Button) findViewById(R.id.bvolver);
		volver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(ListPartidas.this,
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
		listaPartidas.clear();
		
		DoPOST doPost = new DoPOST(ListPartidas.this);
		doPost.execute();

	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String ip = ListPartidas.this.getString(R.string.ip);

		List<String> partidas = new ArrayList<String>();
		List<String> partidas2 = new ArrayList<String>();
		List<String> partidas3 = new ArrayList<String>();
		// Result data

		String jugador = getIntent().getStringExtra("jugador");
		Exception exception = null;

		DoPOST(Context context) {
			mContext = context;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				// Setup the parameters
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// Add more parameters as necessary

				nameValuePairs.add(new BasicNameValuePair("JugadorToReto",
						jugador));
				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/listaPartidas.php");
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
				JSONArray jsonArray = new JSONArray(aux);
				JSONArray jsonArray2 = new JSONArray(aux2);
				JSONArray jsonArray3 = new JSONArray(aux3);
				JSONArray jsonArray4 = new JSONArray(aux4);
				
				
				// Retrieve the data from the JSON object
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String nuevo = (String) jsonObject.get("Nombre");
					partidas.add(nuevo);
				}
				for (int i = 0; i < jsonArray2.length(); i++) {
					JSONObject jsonObject = jsonArray2.getJSONObject(i);
					String nuevo = (String) jsonObject.get("Nombre");
					partidas2.add(nuevo);
				}
				for (int i = 0; i < jsonArray3.length(); i++) {
					JSONObject jsonObject = jsonArray3.getJSONObject(i);
					String nuevo = (String) jsonObject.get("Nombre");
					partidas3.add(nuevo);
				}
				for (int i = 0; i < jsonArray4.length(); i++) {
					JSONObject jsonObject = jsonArray4.getJSONObject(i);
					String nuevo = (String) jsonObject.get("Nombre");
					partidas2.add(nuevo);
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
				AlertDialog msj = new AlertDialog.Builder(ListPartidas.this).create();
				msj.setTitle("Error");
				msj.setMessage("No pudieron cargarse los cursos, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);

			for (String i : partidas) {
				if (!listaPartidas.contains(i)) {
					listaPartidas.add(i);
				}

			}

			list1.setAdapter(new ArrayAdapter<String>(ListPartidas.this,
					R.layout.list_white,R.id.list_content, listaPartidas));

			
			for (String i : partidas2) {
				if (!listaPartidas2.contains(i)) {
					listaPartidas2.add(i);
				}

			}

			list2.setAdapter(new ArrayAdapter<String>(ListPartidas.this,
					R.layout.list_white,R.id.list_content, listaPartidas2));
			
			for (String i : partidas3) {
				if (!listaPartidas3.contains(i)) {
					listaPartidas3.add(i);
				}

			}

			list3.setAdapter(new ArrayAdapter<String>(ListPartidas.this,
					R.layout.list_white,R.id.list_content, listaPartidas3));
			
		}
	}
}
