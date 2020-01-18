package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActividadMemoriaSD extends AppCompatActivity implements View.OnClickListener {
    EditText datosNombres,datosApellidos;
    TextView datos;
    Button botonGuardar,botonCargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_sd);
        cargarComponentes();
    }

    private void cargarComponentes(){
        datosNombres = findViewById(R.id.txtSDnombres);
        datosApellidos = findViewById(R.id.txtSDApellidos);
        datos = findViewById(R.id.lblSDDatos);
        botonGuardar = findViewById(R.id.btnSDGuarfar);
        botonCargar = findViewById(R.id.btnSDBuscar);
        botonCargar.setOnClickListener(this);
        botonGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSDGuarfar:
                BufferedWriter bufferedWriter= null;
                FileWriter fileWriter= null;
                try {
                    File file = Environment.getExternalStorageDirectory();//obtenemos la ruta del SD
                    File ruta = new File(file.getAbsoluteFile(), "archivos.txt");
                    //File ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"archivos.txt");
                    fileWriter = new FileWriter (ruta.getAbsoluteFile(),true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(datosNombres.getText().toString() + ","+datosApellidos.getText().toString()+ ";");
                    bufferedWriter.close();
                    /*OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(ruta));
                    streamWriter.write(datosNombres.getText().toString() + ","+datosApellidos.getText().toString()+ ";");
                    streamWriter.close();
                      */
                }catch (Exception es){
                    Log.e("Error SD",es.getMessage());
                }
              break;
            case R.id.btnSDBuscar:
                try {
                    File file = Environment.getExternalStorageDirectory();//obtenemos la ruta del SD
                    File ruta = new File(file.getAbsoluteFile(), "archivos.txt");
                    BufferedReader leReader = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
                    datos.setText(leReader.readLine());
                    leReader.close();
                }catch (Exception es){
                    Log.e("Error SD",es.getMessage());
                }
              break;
            case R.id.btnSDListar:
                break;
        }
    }
}
