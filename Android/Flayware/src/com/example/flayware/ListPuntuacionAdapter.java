package com.example.flayware;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListPuntuacionAdapter extends BaseAdapter {

	Context context;

	protected List<Puntuacion> listPuntuacions;
	LayoutInflater inflater;

	public ListPuntuacionAdapter(Context context, List<Puntuacion> listPuntuacions) {
		this.listPuntuacions = listPuntuacions;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}



	public int getCount() {
		return listPuntuacions.size();
	}

	public Puntuacion getItem(int position) {
		return listPuntuacions.get(position);
	}

	public long getItemId(int position) {
		return listPuntuacions.get(position).getDrawableId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {

			holder = new ViewHolder();
			convertView = this.inflater.inflate(R.layout.layout_list_puntuacion, parent, false);

			holder.txtNombre = (TextView) convertView.findViewById(R.id.txt_nombre);
			holder.txtApellido = (TextView) convertView.findViewById(R.id.txt_apellido);
			holder.txtPuntuacion = (TextView) convertView.findViewById(R.id.txt_puntuacion);
			holder.imgPuntuacion   = (ImageView)convertView.findViewById(R.id.img_puntuacion);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Puntuacion puntuacion = listPuntuacions.get(position);
		holder.txtNombre.setText(puntuacion.getNombre());
		holder.txtApellido.setText(puntuacion.getApellido());
		holder.txtPuntuacion.setText(puntuacion.getPuntuacion()+"");
		holder.imgPuntuacion.setImageResource(puntuacion.getDrawableId());

		return convertView;
	}

	private class ViewHolder {
		TextView txtNombre;
		TextView txtApellido;
		TextView txtPuntuacion;
		ImageView imgPuntuacion;
	}

}

