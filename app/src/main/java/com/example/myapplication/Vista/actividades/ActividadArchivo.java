package com.example.myapplication.Vista.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.Vista.fragmentos.FrgArtista;

public class ActividadArchivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_archivo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menuarchivo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.opcionArtistaMenuInterna:
                try{
                    FrgArtista artista = new FrgArtista();
                    FragmentTransaction transaccion1 = getSupportFragmentManager().beginTransaction();
                    transaccion1.replace(R.id.contenedorFrg,artista);
                    transaccion1.commit();
                    break;

                }catch (Exception ex){
                    Log.e("error" ,"al cargar fragmento" + ex.getMessage());

                }


        }
        return super.onOptionsItemSelected(item);
    }



}
