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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class ListCursos extends ListActivity {

	private List<String> listaCursos = new ArrayList<String>();
	private Button volver;
	private String pruebajugador;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listacursos);
		DoPOST doPost = new DoPOST(ListCursos.this);
		doPost.execute();
		pruebajugador= getIntent().getStringExtra("jugador");

		getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				String entry = (String) parent.getItemAtPosition(position);
				Intent i = new Intent(ListCursos.this,
						CursoDetails.class);
				i.putExtra("curso", entry);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();

			};
		});

		volver = (Button) findViewById(R.id.bvolver);
		volver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(ListCursos.this,
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
		listaCursos.clear();
		setListAdapter(new ArrayAdapter<String>(ListCursos.this,
				android.R.layout.simple_list_item_1, listaCursos));
		DoPOST doPost = new DoPOST(ListCursos.this);
		doPost.execute();

	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String ip = ListCursos.this.getString(R.string.ip);

		List<String> cursos = new ArrayList<String>();
		// Result data

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

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/listaCursos.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				String result = EntityUtils.toString(entity);

				// Create a JSON object from the request response
				JSONArray jsonArray = new JSONArray(result);

				// Retrieve the data from the JSON object
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String nombre1 = (String) jsonObject.get("Nombre");
					cursos.add(nombre1);
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
				AlertDialog msj = new AlertDialog.Builder(ListCursos.this).create();
				msj.setTitle("Error");
				msj.setMessage("No pudieron cargarse los cursos, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);

			for (String i : cursos) {
				if (!listaCursos.contains(i)) {
					listaCursos.add(i);
				}

			}

			setListAdapter(new ArrayAdapter<String>(ListCursos.this,
					R.layout.list_white,R.id.list_content, listaCursos));

		}
	}
}
