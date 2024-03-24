package com.example.ejercicio24;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio24.Configuracion.Signature;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.SViewHolder> {

    private List<Signature> items;

    public Adaptador(List<Signature> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Adaptador.SViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista, viewGroup, false);
        return new SViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SViewHolder sviewHolder, int i) {
        Bitmap BMP = BitmapFactory.decodeByteArray(items.get(i).getImage(), 0,items.get(i).getImage().length);

        sviewHolder.firma.setImageBitmap(BMP);
        sviewHolder.descripcion.setText(items.get(i).getDescripcion());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView firma;
        public TextView id, nombre, fecha, formato, bytes, descripcion;

        public SViewHolder(View v) {
            super(v);
            firma = (ImageView) v.findViewById(R.id.firma);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
        }
    }
}
