package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class ActiviteSumar extends AppCompatActivity implements View.OnClickListener {
    EditText cajaNumero1,cajaNumero2,cajaResultado;

    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_sumar);
        Sumar();

    }
 private  void Sumar(){
        cajaNumero1 = findViewById(R.id.txtNumero1);
        cajaNumero2 = findViewById(R.id.txtNUmero2);
        cajaResultado = findViewById(R.id.txtResultado);
        boton = findViewById(R.id.btnSumar);
        boton.setOnClickListener(this);

 }

    @Override
    public void onClick(View view) {
        int numero1 = Integer.parseInt(cajaNumero1.getText().toString());
        int numero2 = Integer.parseInt(cajaNumero2.getText().toString());
        int resultado = numero1 + numero2;
        cajaResultado.setText(resultado +"");
    }
}
