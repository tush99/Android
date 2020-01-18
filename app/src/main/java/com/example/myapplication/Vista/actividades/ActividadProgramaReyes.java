package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Modelo.Reyes;
import com.example.myapplication.R;
import com.example.myapplication.Vista.adacter.ReyesAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ActividadProgramaReyes extends AppCompatActivity implements View.OnClickListener {
    TextView datos;
    Button botonCargar;
    RecyclerView recyclerView;
    ReyesAdapter reyesAdapter;
    List<Reyes> reyesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_programa_reyes);
        cargarComponentes();

    }

    private void cargarComponentes (){
         datos = findViewById(R.id.lblReyes);
         recyclerView = findViewById(R.id.recycleReyes);
         botonCargar = findViewById(R.id.btnCargarReyes);
         botonCargar.setOnClickListener(this);
    }
    private void obtenerRecicleReyes(){
        reyesList = new ArrayList<Reyes>();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.reyes_raw);
            DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();
            NodeList Listgodos= document.getElementsByTagName("godos");
            Toast.makeText(this,"numero de res es: " + Listgodos.getLength(),Toast.LENGTH_SHORT).show();
            datos.setText("Numero de reyes es " + Listgodos.getLength());
            for (int i = 0;i<Listgodos.getLength(); i ++){
                Node  nod = Listgodos.item(i);
                if (nod.getNodeType() == Node.ELEMENT_NODE ) {
                    Element element = (Element) nod;
                    String nombres = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String periodo = element.getElementsByTagName("periodo").item(0).getTextContent();
                    //ENvio datos
                    Reyes reyes  = new Reyes();
                    reyes.setNombre(nombres);
                    reyes.setPeriodo(periodo);
                    Log.e("Datos","Datos de la lista" + reyesList);
                    reyesList.add(reyes);


                }
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reyesAdapter = new ReyesAdapter(reyesList);
            recyclerView.setAdapter(reyesAdapter);

        }catch (Exception ec){
            Log.e("Error","Datos de la lista" + reyesList);
        }
    }

    @Override
    public void onClick(View v) {
        obtenerRecicleReyes();
    }
}
