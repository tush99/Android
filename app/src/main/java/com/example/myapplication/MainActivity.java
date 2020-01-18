package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Vista.actividades.ActiviFragmento;
import com.example.myapplication.Vista.actividades.ActividadEScucharFragmento;
import com.example.myapplication.Vista.actividades.ActividadRecycleArtistas;
import com.example.myapplication.Vista.actividades.ActiviteSumar;
import com.example.myapplication.Vista.actividades.Activitelogin;
import com.example.myapplication.Vista.actividades.IngresarNombre;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    Button botoLogin,botonSumar,botonIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarComponentes();

    }
    private  void cargarComponentes(){
        botoLogin = findViewById(R.id.btnlogin);
        botonSumar = findViewById(R.id.btnsumar);
        botonIngresar = findViewById(R.id.btingresar);
        botoLogin.setOnClickListener(this);
        botonSumar.setOnClickListener(this);
        botonIngresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnlogin:
                intent = new Intent(MainActivity.this, Activitelogin.class);
                startActivity(intent);
            break;
            case R.id.btnsumar:
                intent = new Intent(MainActivity.this, ActiviteSumar.class);
                startActivity(intent);
            break;
            case R.id.btingresar:
                intent = new Intent(MainActivity.this, IngresarNombre.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //metodo para cargar los menus
        //MenuInflater = permite crear un objeto para manejar el archivo xml(main.xml)
        //metodo inflate permite agregar un menu implementado de un archivo xml a la actividad
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //este metodo permite realizar enventos en cada intem hilo de los menús
        Intent intent;
        switch (item.getItemId()){
            case R.id.opcionLogin:
                intent = new Intent(MainActivity.this,Activitelogin.class);
                startActivity(intent);
                break;
            case  R.id.opcionSumar:
                intent = new Intent(MainActivity.this,ActiviteSumar.class);
                startActivity(intent);
                break;
            case R.id.opcionesTrasferencia:
                intent = new Intent(MainActivity.this,IngresarNombre.class);
                startActivity(intent);
                break;
            case R.id.opcionColores:
                intent = new Intent(MainActivity.this, ActiviFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionDatos:
                intent = new Intent(MainActivity.this, ActividadEScucharFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionDlgSumar:
                final Dialog dlgSumar = new Dialog(MainActivity.this);
                dlgSumar.setContentView(R.layout.dlg_sumar);
                Button botonSumarDlg = dlgSumar.findViewById(R.id.btnSumarDlg);
                final EditText cajaN1 = dlgSumar.findViewById(R.id.txtDg1);
                final EditText cajaN2 = dlgSumar.findViewById(R.id.txt2Dg1);
                botonSumarDlg.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        int n1 = Integer.parseInt(cajaN1.getText().toString()) + Integer.parseInt(cajaN2.getText().toString());
                        Toast.makeText(MainActivity.this, "La suma es:" + n1, Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });

                dlgSumar.show();
                break;
            case R.id.opcionRecycle:
                intent = new Intent(MainActivity.this, ActividadRecycleArtistas.class);
                startActivity(intent);
                break;
        };
        return super.onOptionsItemSelected(item);
    }
}
