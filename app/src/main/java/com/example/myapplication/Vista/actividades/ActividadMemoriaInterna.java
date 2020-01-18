package com.example.myapplication.Vista.actividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Modelo.Artista;
import com.example.myapplication.R;
import com.example.myapplication.Vista.adacter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ActividadMemoriaInterna extends AppCompatActivity implements View.OnClickListener {
    String direccionFoto;
    Button botonGuardar, botonBuscarTods, botonListartodos, botonSubir;
    EditText cajaNOmbres, cajaApellidos, cajaNomArtistico;
    TextView datos, mensaje, imagen;
    ImageView imageView;
    RecyclerView recyclerViewAris;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_interna);
        tomarControl();
    }

    private void tomarControl() {
        recyclerViewAris = findViewById(R.id.RecicleMemoria);
        botonGuardar = findViewById(R.id.btnMemoriaGuarfar);
        imagen = findViewById(R.id.lbldireImg);
        botonBuscarTods = findViewById(R.id.btnMemoriaBuscar);
        imageView = findViewById(R.id.imgMemoriaArtista);
        cajaNOmbres = findViewById(R.id.txtMemorianombres);
        cajaApellidos = findViewById(R.id.txtMemoriaApellidos);
        cajaNomArtistico = findViewById(R.id.txtMemoriaNomArtistico);
        datos = findViewById(R.id.lblMemoriaDatos);
        botonGuardar.setOnClickListener(this);
        botonBuscarTods.setOnClickListener(this);
        botonListartodos = findViewById(R.id.btnMemoriaListar);
        botonListartodos.setOnClickListener(this);
        botonSubir = findViewById(R.id.btndubitImg);
        botonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargarImagne();
                Toast.makeText(getApplicationContext(), "Seleccione una Imagen", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMemoriaGuarfar:
                try {
                    Artista artista = new Artista();
                    artista.setNombres(cajaNOmbres.getText().toString());
                    artista.setApellidos(cajaApellidos.getText().toString());
                    artista.setNombreArtistico(cajaNomArtistico.getText().toString());
                    artista.setFoto(R.drawable.ecuadoruniversitario);
                    artista.setFotoArt(imagen.getText().toString());
                    OutputStreamWriter esctibir = new OutputStreamWriter(openFileOutput("archivosArtistasSi3.txt", Context.MODE_APPEND));
                    esctibir.write(artista.getNombres() + ", " + artista.getApellidos() + ", " + artista.getNombreArtistico() + "," + artista.getFotoArt() + ";");
                    //presentar Mensaje al guardar-----

                    //------------------------------------------
                    esctibir.close();
                } catch (Exception ex) {
                    Log.e("archivoMi", "error de escritura -" + ex.getMessage());

                }
                break;
            case R.id.btnMemoriaBuscar:
                try {
                    BufferedReader lect = new BufferedReader(new InputStreamReader(openFileInput("archivosArtistasSi3.txt")));
                    String lineas = lect.readLine();
                    datos.setText(lineas);
                    lect.close();
                } catch (Exception ex) {
                    Log.e("archivoMi", "error de lectura -" + ex.getMessage());
                }
                break;
            case R.id.btnMemoriaListar:
                try {
                    listaArtista = new ArrayList<Artista>();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("archivosArtistasSi3.txt")));
                    String listaartista = lector.readLine();
                    Log.e("valor", listaartista);
                    String[] data = listaartista.split(";");
                    for (int i = 0; i < data.length; i++) {
                        String[] array;
                        array = data[i].split(",");
                        Artista artista = new Artista();
                        artista.setNombres(array[0]);
                        artista.setApellidos(array[1]);
                        artista.setNombreArtistico(array[2]);
                        artista.setFotoArt(Uri.parse(array[3]).toString());

                        //vamos añadir los datos a  la lista
                        listaArtista.add(artista);
                    }

                    datos.setText(listaartista);
                    recyclerViewAris.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new ArtistaAdapter(listaArtista);
                    recyclerViewAris.setAdapter(adapter);

                    adapter.setOnclickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cargarDialogo(view);
                        }
                    });


                    lector.close();

                } catch (Exception ex) {
                    Log.e("archivoMi", "error de lectura -" + ex.getMessage());
                }
                break;

        }


    }

    public void cargarDialogo(View view) {
        Dialog dialog = new Dialog(ActividadMemoriaInterna.this);
        dialog.setContentView(R.layout.dlg_artista);
        TextView lblnombres = dialog.findViewById(R.id.txtnombreDlg);
        TextView lblapellidos = dialog.findViewById(R.id.txtApellidosDlg);
        ImageView imageView = dialog.findViewById(R.id.imageDlg);
        lblnombres.setText("Nombres: " + listaArtista.get(recyclerViewAris.getChildAdapterPosition(view)).getNombres());
        lblapellidos.setText("Apellidos: " + listaArtista.get(recyclerViewAris.getChildAdapterPosition(view)).getApellidos());
        imageView.setImageURI(Uri.parse(listaArtista.get(recyclerViewAris.getChildAdapterPosition(view)).getFotoArt()));
        dialog.show();
    }

    private void cargarImagne() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "carge imagne "), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imageView.setImageURI(path);
            direccionFoto = path.toString();
            imagen.setText(direccionFoto);

        }
    }

}
