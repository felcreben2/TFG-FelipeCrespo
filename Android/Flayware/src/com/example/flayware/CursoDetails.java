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

public class CursoDetails extends Activity {

	private Button bcancel;
	private TextView nombreCurso;
	private TextView tematicaCurso;
	private TextView numPreguntasCurso;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cursodetails);

		
		nombreCurso = (TextView) findViewById(R.id.NombreCursoMostrar);
		tematicaCurso = (TextView) findViewById(R.id.TematicaMostrar);
		numPreguntasCurso = (TextView) findViewById(R.id.NumPreguntasMostrar);
		DoPOST mDoPOST = new DoPOST(CursoDetails.this, getIntent().getStringExtra("curso"));

		mDoPOST.execute();

		bcancel = (Button) findViewById(R.id.volver);
		bcancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(CursoDetails.this,
						ListCursos.class);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();
			}
		});

	}

	private class DoPOST extends AsyncTask<String, Void, Boolean> {

		Context mContext = null;
		String nombreCursoSelec = "";
		String ip = CursoDetails.this.getString(R.string.ip);

		String nombreCursoR = "";
		String tematicaCurso = "";
		String numPreguntasCurso = "";

		Exception exception = null;

		DoPOST(Context context, String nombreCursoSelecc) {
			mContext = context;
			this.nombreCursoSelec = nombreCursoSelecc;
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("nombreCursoSelec",
						nombreCursoSelec));
				// Add more parameters as necessary

				// Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				// Setup timeouts
				HttpConnectionParams
						.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost("http://" + ip
						+ "/services/cursoDetails.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);

				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);

				// Retrieve the data from the JSON object
				this.nombreCursoR = (String) jsonObject.get("Nombre");
				this.tematicaCurso = (String) jsonObject.get("Tematica");
				this.numPreguntasCurso = (String) jsonObject.get("NumPreguntas");
				

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
				AlertDialog msj = new AlertDialog.Builder(CursoDetails.this).create();
				msj.setTitle("Error");
				msj.setMessage("No se pudieron cargar los detalles del curso, intente de nuevo");
				msj.show();
			}

			super.onPostExecute(valid);
			CursoDetails.this.nombreCurso.setText(nombreCursoR);
			CursoDetails.this.tematicaCurso.setText(tematicaCurso);
			CursoDetails.this.numPreguntasCurso.setText(numPreguntasCurso);
			

		}

	}




}