package com.example.flayware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends ActionBarActivity {
	
	private Button cursos;
	private Button partida;
	private Button retos;
	private Button salir;
	private Button descargar;
	private Button ranking;
	private String pruebajugador;
	private Button editar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		pruebajugador= getIntent().getStringExtra("jugador");
	
		salir = (Button) findViewById(R.id.salir);
		salir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 setResult(RESULT_OK);
                 finish();
			}
		});
		cursos = (Button) findViewById(R.id.cursos);
		cursos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchUserCurso(null);
				finish();

			}
		});
		
		
		descargar = (Button) findViewById(R.id.descargar);
		descargar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchDescargar(null);
				finish();

			}
		});
		
		retos= (Button) findViewById(R.id.retos);
		retos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchRetos(null);
				finish();
			}
		});
		partida= (Button) findViewById(R.id.partidas);
		partida.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchPartida(null);
				finish();
			}
		});
		ranking= (Button) findViewById(R.id.ranking);
		ranking.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchRanking(null);
				finish();
			}
		});
	
	editar= (Button) findViewById(R.id.editar);
	editar.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			launchEditar(null);
			finish();
		}
	});
}


	public void launchUserCurso(View view) {
		Intent i = new Intent(this, ListCursos.class);
		i.putExtra("jugador", getIntent().getStringExtra("jugador"));
		startActivity(i);
	}
	
	public void launchRetos(View view) {
		Intent i = new Intent(this, ListRetos.class);
		i.putExtra("jugador", getIntent().getStringExtra("jugador"));
		startActivity(i);
	}
	public void launchPartida(View view) {
		Intent i = new Intent(this, ListPartidas.class);
		i.putExtra("jugador", getIntent().getStringExtra("jugador"));
		startActivity(i);
	}
	public void launchDescargar(View view) {
		Intent i = new Intent(this, Descargas.class);
		i.putExtra("jugador", getIntent().getStringExtra("jugador"));
		startActivity(i);
	}
	public void launchRanking(View view) {
		Intent i = new Intent(this, ListPuntuacion.class);
		i.putExtra("jugador", getIntent().getStringExtra("jugador"));
		startActivity(i);
	}
	public void launchEditar(View view) {
		Intent i = new Intent(this, Editar.class);
		i.putExtra("jugador", getIntent().getStringExtra("jugador"));
		startActivity(i);
	}
}
