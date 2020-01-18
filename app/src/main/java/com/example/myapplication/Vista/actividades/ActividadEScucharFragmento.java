package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.Modelo.Comunicador;
import com.example.myapplication.R;
import com.example.myapplication.Vista.fragmentos.FrgEnviar;
import com.example.myapplication.Vista.fragmentos.FrgResivir;

import static android.app.PendingIntent.getActivity;

public class ActividadEScucharFragmento extends AppCompatActivity   implements Comunicador, FrgEnviar.OnFragmentInteractionListener, FrgResivir.OnFragmentInteractionListener {
     Button botonEnviar,botonResivir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_escuchar_fragmento);


    }


   @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void responder(String datos) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FrgResivir frg =(FrgResivir) fragmentManager.findFragmentById(R.id.frgResivir);
        frg.cambiarTexto(datos);
    }
}
