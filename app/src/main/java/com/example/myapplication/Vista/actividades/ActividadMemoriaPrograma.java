package com.example.myapplication.Vista.actividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Modelo.Artista;
import com.example.myapplication.R;
import com.example.myapplication.Vista.adacter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActividadMemoriaPrograma extends AppCompatActivity implements View.OnClickListener {

    InputStream input;
    BufferedReader lector;
    Button cargar ;
    TextView datos;
    ArtistaAdapter artistaAdapter;
    RecyclerView recyclerView;
    List<Artista> listaArtista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_programa);
        tomar();
    }
    private  void tomar(){

        input = getResources().openRawResource(R.raw.archivo_pro);
        recyclerView = findViewById(R.id.recyMePro);
        cargar = findViewById(R.id.btnMeProLista);
        datos = findViewById(R.id.lblMeProDatos);
        cargar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMeProLista:
                try {
                    lector = new BufferedReader(new InputStreamReader(input));
                    listaArtista = new ArrayList<Artista>();
                    String listaartista = lector.readLine();
                    Log.e("valor", listaartista);
                    String[] data = listaartista.split(";");
                    int numero;
                    for (int i = 0; i < data.length; i++) {
                        String[] array;
                        array = data[i].split(",");
                        Artista artista = new Artista();
                        artista.setNombres(array[0]);
                        artista.setApellidos(array[1]);
                        artista.setNombreArtistico(array[2]);
                         artista.setFoto(Integer.parseInt(array[3]));                           //vamos añadir los datos a  la lista
                        listaArtista.add(artista);
                    } 

                    datos.setText(listaartista);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    artistaAdapter = new ArtistaAdapter(listaArtista);
                    recyclerView.setAdapter(artistaAdapter);

                    lector.close();
                }catch (Exception ex){
                    Log.e("error","error de lectura " + ex.getMessage());

                }


        }
    }


}
