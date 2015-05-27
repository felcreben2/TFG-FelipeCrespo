package com.example.flayware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class PantallaInicio extends Activity {

	private Button volver;
	private Button usuario;
	private Button registrar;
	private ImageButton imageButton1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallainicio);
		volver = (Button) findViewById(R.id.ButtonBack);
		volver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				backToIndex(null);
				finish();
			}
		});
		usuario = (Button) findViewById(R.id.ButtonUser);
		usuario.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchUserLogin(null);

			}
		});
		
		
		registrar = (Button) findViewById(R.id.ButtonRegister);
		registrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchRegister(null);

			}
		});
		
		imageButton1= (ImageButton) findViewById(R.id.imageButton1);
		imageButton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			        Intent i = new Intent(PantallaInicio.this, AcercaDe.class);
			              startActivity(i);
			      
				
			}
		});
	}

	public void backToIndex(View view) {
		finish();
	}

	public void launchUserLogin(View view) {
		Intent i = new Intent(this, UserLogin.class);
		startActivity(i);
	}
	
	public void launchRegister(View view) {
		Intent i = new Intent(this, Register.class);
		startActivity(i);
	}
	public void launchAcercaDe(View view) {
		Intent i = new Intent(this, AcercaDe.class);
		startActivity(i);
	}
}
