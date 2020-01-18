package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Vista.actividades.ActiviFragmento;
import com.example.myapplication.Vista.actividades.ActividadArchivo;
import com.example.myapplication.Vista.actividades.ActividadEScucharFragmento;
import com.example.myapplication.Vista.actividades.ActividadMemoriaInterna;
import com.example.myapplication.Vista.actividades.ActividadMemoriaPrograma;
import com.example.myapplication.Vista.actividades.ActividadMemoriaSD;
import com.example.myapplication.Vista.actividades.ActividadProductoHelper;
import com.example.myapplication.Vista.actividades.ActividadProgramaReyes;
import com.example.myapplication.Vista.actividades.ActividadRecycleArtistas;
import com.example.myapplication.Vista.actividades.ActiviteSumar;
import com.example.myapplication.Vista.actividades.Activitelogin;
import com.example.myapplication.Vista.actividades.IngresarNombre;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
                intent = new Intent(Main2Activity.this, Activitelogin.class);
                startActivity(intent);
                break;
            case  R.id.opcionSumar:
                intent = new Intent(Main2Activity.this, ActiviteSumar.class);
                startActivity(intent);
                break;
            case R.id.opcionesTrasferencia:
                intent = new Intent(Main2Activity.this, IngresarNombre.class);
                startActivity(intent);
                break;
            case R.id.opcionColores:
                intent = new Intent(Main2Activity.this, ActiviFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionDatos:
                intent = new Intent(Main2Activity.this, ActividadEScucharFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionDlgSumar:
                final Dialog dlgSumar = new Dialog(Main2Activity.this);
                dlgSumar.setContentView(R.layout.dlg_sumar);
                Button botonSumarDlg = dlgSumar.findViewById(R.id.btnSumarDlg);
                final EditText cajaN1 = dlgSumar.findViewById(R.id.txtDg1);
                final EditText cajaN2 = dlgSumar.findViewById(R.id.txt2Dg1);
                botonSumarDlg.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        int n1 = Integer.parseInt(cajaN1.getText().toString()) + Integer.parseInt(cajaN2.getText().toString());
                        Toast.makeText(Main2Activity.this, "La suma es:" + n1, Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });

                dlgSumar.show();
                break;
            case R.id.opcionRecycle:
                intent = new Intent(Main2Activity.this, ActividadRecycleArtistas.class);
                startActivity(intent);
                break;
            case R.id.opcionMemoriaInterna:
                intent = new Intent(Main2Activity.this, ActividadArchivo.class);
                startActivity(intent);
                break;

            case R.id.opcionInternaReyes:
                intent = new Intent(Main2Activity.this, ActividadProgramaReyes.class);
                startActivity(intent);
                break;

            case R.id.opcionProducto:
                intent = new Intent(Main2Activity.this, ActividadProductoHelper.class);
                startActivity(intent);
                break;
        };
        return super.onOptionsItemSelected(item);
    }
}
