package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class Activitelogin extends AppCompatActivity implements View.OnClickListener{
    EditText cajaNombre,cajaPassword;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitelogin);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaNombre = findViewById(R.id.txtNombre);
        cajaPassword = findViewById(R.id.txtPassword);
        boton = findViewById(R.id.btnLogin);
        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(Activitelogin.this,"Usuario" + cajaNombre.getText() + "clave" + cajaPassword.getText(),Toast.LENGTH_SHORT).show();
    }
}
