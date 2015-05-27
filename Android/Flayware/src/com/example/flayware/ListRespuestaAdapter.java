package com.example.flayware;


import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListRespuestaAdapter extends BaseAdapter {

	Context context;

	protected List<Respuesta> listRespuestas;
	LayoutInflater inflater;

	public ListRespuestaAdapter(Context context, List<Respuesta> listRespuestas) {
		this.listRespuestas = listRespuestas;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}



	public int getCount() {
		return listRespuestas.size();
	}

	public Respuesta getItem(int position) {
		return listRespuestas.get(position);
	}

	public long getItemId(int position) {
		return listRespuestas.get(position).getValue();
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {

			holder = new ViewHolder();
			convertView = this.inflater.inflate(R.layout.layout_checkbox, parent, false);

			holder.txtTexto = (TextView) convertView.findViewById(R.id.code);
			//holder.radiobutton = (RadioButton) convertView.findViewById(R.id.checkBox);
			holder.imgrespuesta   = (ImageView)convertView.findViewById(R.id.img_respuesta);

	    
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Respuesta respuesta = listRespuestas.get(position);
		holder.txtTexto.setText(respuesta.getTexto());
//		if(respuesta.getValue()==1){
//			holder.radiobutton.setChecked(true);
//		}else{
//			holder.radiobutton.setChecked(false);
//		}
		holder.imgrespuesta.setImageBitmap(respuesta.getImage());
//	    byte [] image = listRespuestas.get(position).getImage();
//        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
//        holder.imgrespuesta.setImageBitmap(bmp);
		return convertView;
	}

	private class ViewHolder {
		TextView txtTexto;
		//RadioButton radiobutton;
		ImageView imgrespuesta;
	}


}

