package com.example.myapplication.Vista.fragmentos;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Modelo.Artista;
import com.example.myapplication.R;
import com.example.myapplication.Vista.actividades.ActividadMemoriaInterna;
import com.example.myapplication.Vista.adacter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrgArtista.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrgArtista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrgArtista extends Fragment  implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context mBase;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String direccionFoto;
    Button botonGuardar, botonBuscarTods, botonListartodos, botonSubir;
    EditText cajaNOmbres, cajaApellidos, cajaNomArtistico;
    TextView datos, mensaje, imagen;
    ImageView imageView;
    RecyclerView recyclerViewAris;
    ArtistaAdapter adapter;
    List<Artista> listaArtista;

    private OnFragmentInteractionListener mListener;

    public FrgArtista() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrgArtista.
     */
    // TODO: Rename and change types and number of parameters
    public static FrgArtista newInstance(String param1, String param2) {
        FrgArtista fragment = new FrgArtista();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_frg_artista, container, false);
         cajaNOmbres = (EditText) view.findViewById(R.id.txtMemorianombresFrg);
         cajaApellidos = (EditText) view.findViewById(R.id.txtMemoriaApellidosFrg);
         cajaNomArtistico = (EditText) view.findViewById(R.id.txtMemoriaNomArtisticoFrg);
        recyclerViewAris = view.findViewById(R.id.RecicleMemoriaFrg);
        botonGuardar = view.findViewById(R.id.btnMemoriaGuarfarFrg);
        imagen = view.findViewById(R.id.lbldireImg);
        botonBuscarTods = view.findViewById(R.id.btnMemoriaBuscarFrg);
        imageView = view.findViewById(R.id.imgMemoriaArtistaFrg);
        datos = view.findViewById(R.id.lblMemoriaDatosFrg);
        botonGuardar.setOnClickListener(this);
        botonBuscarTods.setOnClickListener(this);
        botonListartodos = view.findViewById(R.id.btnMemoriaListarFrg);
        botonListartodos.setOnClickListener(this);
        botonSubir = view.findViewById(R.id.btndubitImgFrg);
        botonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagne();

            }
        });

         return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMemoriaBuscarFrg:
                try {

                    Artista artista = new Artista();
                    artista.setNombres(cajaNOmbres.getText().toString());
                    artista.setApellidos(cajaApellidos.getText().toString());
                    artista.setNombreArtistico(cajaNomArtistico.getText().toString());
                    artista.setFoto(R.drawable.ecuadoruniversitario);
                    artista.setFotoArt(imagen.getText().toString());
                    OutputStreamWriter esctibir = new OutputStreamWriter(openFileOutput("FrgArtista.txt", Context.MODE_APPEND));
                    esctibir.write(artista.getNombres() + ", " + artista.getApellidos() + ", " + artista.getNombreArtistico() + "," + artista.getFotoArt() + ";");
                    //presentar Mensaje al guardar-----

                    //------------------------------------------
                    //esctibir.close();
                } catch (Exception ex) {
                    Log.e("archivoMi", "error de escritura -" + ex.getMessage());

                }
                break;
            case R.id.btnMemoriaBuscar:
                try {
                    BufferedReader lect = new BufferedReader(new InputStreamReader(openFileInput("FrgArtista.txt")));
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
                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("FrgArtista.txt")));
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
                    recyclerViewAris.setLayoutManager(new LinearLayoutManager(FrgArtista.super.getContext()));
                    adapter = new ArtistaAdapter(listaArtista);
                    recyclerViewAris.setAdapter(adapter);




                    lector.close();

                } catch (Exception ex) {
                    Log.e("archivoMi", "error de lectura -" + ex.getMessage());
                }
                break;

        }


        }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void cargarImagne() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "carge imagne "), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imageView.setImageURI(path);
            direccionFoto = path.toString();
            imagen.setText(direccionFoto);

        }
    }

    public FileOutputStream openFileOutput(String name, int mode)
            throws FileNotFoundException {
        return mBase.openFileOutput(name, mode);
    }
    public FileInputStream openFileInput(String name)
            throws FileNotFoundException {
        return mBase.openFileInput(name);
    }
}

