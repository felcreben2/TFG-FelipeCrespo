package com.example.flayware;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class Descargas extends ActionBarActivity {

	private Button bcancel;

	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.descargas);
	
	
		bcancel = (Button) findViewById(R.id.bvolver);
		bcancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Descargas.this,
						com.example.flayware.Menu.class);
				i.putExtra("jugador", getIntent().getStringExtra("jugador"));
				startActivity(i);
				finish();
			}
		});



}
}
