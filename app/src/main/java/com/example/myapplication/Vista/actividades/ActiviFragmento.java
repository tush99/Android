package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.Vista.fragmentos.Frg1;
import com.example.myapplication.Vista.fragmentos.Frg2;

public class ActiviFragmento extends AppCompatActivity implements View.OnClickListener , Frg1.OnFragmentInteractionListener, Frg2.OnFragmentInteractionListener {
    Button botonfrg1,Botonfrg2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activi_fragmento);
        cargarComponentes();
    }

    private void cargarComponentes(){
        botonfrg1 = findViewById(R.id.btnFragmento1);
        Botonfrg2 = findViewById(R.id.btnFragmento2);
        botonfrg1.setOnClickListener(this);
        Botonfrg2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFragmento1:
                Frg1 fragmeto1 = new Frg1();
                FragmentTransaction transaccion1 = getSupportFragmentManager().beginTransaction();
                transaccion1.replace(R.id.contenedorFragmentos,fragmeto1);
                transaccion1.commit();
                break;
            case R.id.btnFragmento2:
               Frg2 fragmeto2 = new Frg2();
               FragmentTransaction transaccion2 = getSupportFragmentManager().beginTransaction();
                transaccion2.replace(R.id.contenedorFragmentos,fragmeto2);
               transaccion2.commit();
               break;
        };
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
