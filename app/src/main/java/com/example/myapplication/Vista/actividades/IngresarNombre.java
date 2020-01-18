package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class IngresarNombre extends AppCompatActivity implements View.OnClickListener {
        EditText cajaNombre,cajaApellidos;
        Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_nombre);
        EnviarNombre();
    }
    private void EnviarNombre(){
        cajaNombre = findViewById(R.id.txtnombre);
        cajaApellidos = findViewById(R.id.txtApellidos);
        enviar = findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(IngresarNombre.this,Datos.class);
        Bundle bundle = new Bundle();

        bundle.putString("apellidos",cajaApellidos.getText()+"");
        bundle.putString("nombres",cajaNombre.getText()+"");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
