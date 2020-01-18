package com.example.myapplication.Vista.adacter;


import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Modelo.Artista;
import com.example.myapplication.R;

import java.util.List;

public class ArtistaAdapter  extends RecyclerView.Adapter<ArtistaAdapter.ViewHolderArtista> implements View.OnClickListener{
    //Una lista dinamica
    private View.OnClickListener clickArtista;
    List<Artista> lista;
    public ArtistaAdapter(List<Artista> lista){

        this.lista = lista;
    }

        //inflater sirve para cargar la vista;
    //se carga la vista
    @NonNull
    @Override
    public ViewHolderArtista onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artista,null);
        view.setOnClickListener(this);
        return new ViewHolderArtista(view);
    }

    @Override
    //realiza modificaciones a cada item
    public void onBindViewHolder(@NonNull ViewHolderArtista viewHolderArtista, int pos) {
        viewHolderArtista.datoNombres.setText(lista.get(pos).getNombres());
        viewHolderArtista.datpsApellidos.setText(lista.get(pos).getApellidos());
        viewHolderArtista.datosArtisticos.setText(lista.get(pos).getNombreArtistico());
        if (lista.get(pos).getFotoArt() != null){
            Log.e("Error", "Entra" + lista.get(pos).getFotoArt());
            viewHolderArtista.datoFoto.setImageURI(Uri.parse(lista.get(pos).getFotoArt()));
        } else {
            Log.e("error", "sale");
            viewHolderArtista.datoFoto.setImageResource(lista.get(pos).getFoto());
        }


    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnclickListener(View.OnClickListener onclickListener){
        this.clickArtista = onclickListener;
    }
    @Override
    public void onClick(View view) {
            if(clickArtista != null){
                clickArtista.onClick(view);
        }
    }


    public static  class ViewHolderArtista extends RecyclerView.ViewHolder{
        TextView datoNombres;
        TextView datpsApellidos;
        TextView datosArtisticos;
        ImageView datoFoto;


        public ViewHolderArtista(@NonNull View itemView) {
            super(itemView);
            datoNombres = itemView.findViewById(R.id.lblNombreArtista);
            datpsApellidos = itemView.findViewById(R.id.lblApellidosArtista);
            datosArtisticos = itemView.findViewById(R.id.lblNoArtista);
            datoFoto = itemView.findViewById(R.id.ImgAbduzcan);

        }
    }
}
