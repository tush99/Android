package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.myapplication.R;

public class Datos extends AppCompatActivity {
        EditText cajaNombre,cajaApllidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        cargar();

    }
    public void cargar(){
        Bundle bundle = this.getIntent().getExtras();
        cajaNombre = findViewById(R.id.txtapllidos);
        cajaApllidos = findViewById(R.id.txtNombres);
        cajaNombre.setText(bundle.getString("nombres"));
        cajaApllidos.setText(bundle.getString("apellidos"));

    }

}
