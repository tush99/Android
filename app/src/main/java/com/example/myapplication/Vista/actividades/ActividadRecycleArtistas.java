package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.Modelo.Artista;
import com.example.myapplication.R;
import com.example.myapplication.Vista.adacter.ArtistaAdapter;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActividadRecycleArtistas extends AppCompatActivity  {
    RecyclerView recyclerViewArista;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_recycle_artistas);
        tomaControl();
        cargarRecycle();
    }
    private void tomaControl() {
        recyclerViewArista = findViewById(R.id.recycleArtista);

    }
    private  void cargarRecycle(){
        Artista artista1 = new Artista();
        artista1.setNombres("lucas");
        artista1.setApellidos("Miguel");
        artista1.setFoto(R.drawable.abduzcan);
        Artista artista2 = new Artista();
        artista2.setNombres("Don");
        artista2.setApellidos("Medardo");
        artista2.setFoto(R.drawable.calvo);

        listaArtista =  new ArrayList<Artista>();
        listaArtista.add(artista1);
        listaArtista.add(artista2);
        recyclerViewArista.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArtistaAdapter(listaArtista);

            adapter.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarDialogo(v);
                }
            });
        recyclerViewArista.setAdapter(adapter);
    }

    public void cargarDialogo(View view){
        Dialog dialog = new Dialog(ActividadRecycleArtistas.this);
        dialog.setContentView(R.layout.dlg_artista);
        TextView lblnombres = dialog.findViewById(R.id.txtnombreDlg);
        TextView lblapellidos = dialog.findViewById(R.id.txtApellidosDlg);
        ImageView imageView = dialog.findViewById(R.id.imageDlg);
        lblnombres.setText("Nombres" + listaArtista.get(recyclerViewArista.getChildAdapterPosition(view)).getNombres());
        lblapellidos.setText("Nombres" + listaArtista.get(recyclerViewArista.getChildAdapterPosition(view)).getApellidos());
        imageView.setImageResource( listaArtista.get(recyclerViewArista.getChildAdapterPosition(view)).getFoto());
        dialog.show();
    }

}
