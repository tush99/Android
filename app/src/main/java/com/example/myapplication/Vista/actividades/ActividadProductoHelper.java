package com.example.myapplication.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.Modelo.Artista;
import com.example.myapplication.Modelo.Producto;
import com.example.myapplication.R;
import com.example.myapplication.Vista.adacter.ProductoAdapter;
import com.example.myapplication.controlador.HelperProducto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ActividadProductoHelper extends AppCompatActivity implements View.OnClickListener {
    EditText cajaCodigo, cajaDescripcion, cajaPrecio, cajaCantidad;
    Button botonGuardar, botonCargar, botonEliminar, botonModificar, botonEliminarTo, botonListar;
    RecyclerView recyclerView;
    ProductoAdapter adapter;
    List<Producto> productoList;
    HelperProducto helperProducto;
    int codigo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_producto_helper);
        cargarElementos();
    }

    private void cargarElementos() {
        recyclerView = findViewById(R.id.RecicleHelper);
        cajaCodigo = findViewById(R.id.txtCodigoHelper);
        cajaDescripcion = findViewById(R.id.txtDescripcionHelper);
        cajaCantidad = findViewById(R.id.txtCantidadHelper);
        cajaPrecio = findViewById(R.id.txtprecioHelper);
        botonGuardar = findViewById(R.id.btnGuardarHelper);
        botonCargar = findViewById(R.id.btnBuscarHelper);
        botonEliminar = findViewById(R.id.btnEliminarHelper);
        botonEliminarTo = findViewById(R.id.btnEliminarTodosHelper);
        botonListar = findViewById(R.id.btnListarHelper);
        botonModificar = findViewById(R.id.btnModificarHelper);
        botonGuardar.setOnClickListener(this);
        botonCargar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonEliminarTo.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardarHelper:
                try {
                    helperProducto = new HelperProducto(this, "name", null, 1);
                    SQLiteDatabase liteDatabase = helperProducto.getWritableDatabase();
                    Producto producto = new Producto();
                    int codigo = Integer.parseInt(cajaCodigo.getText().toString());
                    String descripcion = cajaDescripcion.getText().toString();
                    int cantidad = Integer.parseInt(cajaCantidad.getText().toString());
                    double precio = Double.parseDouble(cajaPrecio.getText().toString());
                    producto.setCodigo(codigo);
                    producto.setDescripcion(descripcion);
                    producto.setCantidad(cantidad);
                    producto.setPrecio(precio);
                    helperProducto.insertar(producto);
                    liteDatabase.close();
                    Toast.makeText(this, "Guardado Exitoso", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("Error al guardar", String.valueOf(helperProducto));
                }
                break;
            case R.id.btnBuscarHelper:
                try {
                    helperProducto = new HelperProducto(this, "name", null, 1);
                    SQLiteDatabase liteDatabase = helperProducto.getReadableDatabase();
                    productoList = helperProducto.getAll();
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new ProductoAdapter(productoList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnclickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cargarDialogo(v);
                        }
                    });
                    liteDatabase.close();
                    Toast.makeText(this, "Show", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Log.e("error al cargar ", String.valueOf(productoList));
                }
                break;
            case R.id.btnModificarHelper:
                try {
                    helperProducto = new HelperProducto(this, "name", null, 1);
                    SQLiteDatabase liteDatabase = helperProducto.getWritableDatabase();
                    Producto producto = new Producto();
                    int codigo = Integer.parseInt(cajaCodigo.getText().toString());
                    String descripcion = cajaDescripcion.getText().toString();
                    int cantidad = Integer.parseInt(cajaCantidad.getText().toString());
                    double precio = Double.parseDouble(cajaPrecio.getText().toString());
                    producto.setCodigo(codigo);
                    producto.setDescripcion(descripcion);
                    producto.setCantidad(cantidad);
                    producto.setPrecio(precio);
                    helperProducto.modifican(producto);
                    liteDatabase.close();
                    Toast.makeText(this, "Se modifico Exitosomente", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("ERROR ", "MODIFICAR" + ex.getMessage());
                }
                break;
            case R.id.btnEliminarHelper:
                try {
                    helperProducto = new HelperProducto(this, "name", null, 1);
                    SQLiteDatabase liteDatabase = helperProducto.getWritableDatabase();
                    Producto producto = new Producto();
                    int codigo = Integer.parseInt(cajaCodigo.getText().toString());
                    producto.setCodigo(codigo);
                    helperProducto.Elminar(producto);
                    liteDatabase.close();
                    Toast.makeText(this, "Se elimino Exitosomente", Toast.LENGTH_SHORT).show();

                } catch (Exception es) {
                    Log.e("error ", "NO se  borro exitosamente" + es.getMessage());
                }
                break;
            case R.id.btnEliminarTodosHelper:
                try {
                    helperProducto = new HelperProducto(this, "name", null, 1);
                    SQLiteDatabase liteDatabase = helperProducto.getWritableDatabase();
                     helperProducto.ElminarTodos();
                    liteDatabase.close();
                    Toast.makeText(this, "Se elimino todo Exitosomente", Toast.LENGTH_SHORT).show();

                } catch (Exception es) {
                    Log.e("error ", "NO se  borro exitosamente" + es.getMessage());
                }
                break;
            case R.id.btnListarHelper:
                try {
                    helperProducto = new HelperProducto(this, "name", null, 1);
                    SQLiteDatabase liteDatabase = helperProducto.getWritableDatabase();
                    final Dialog dlgproduc = new Dialog(ActividadProductoHelper.this);
                    dlgproduc.setContentView(R.layout.dlg_buscarprodructo);
                    Button botonBuscarCode = dlgproduc.findViewById(R.id.btnBuscarProHelper);
                    final EditText cajaN12 = dlgproduc.findViewById(R.id.txtBuscarProHelper);
                    botonBuscarCode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            codigo= Integer.parseInt(cajaN12.getText().toString());
                            dlgproduc.hide();
                        }
                    });
                    productoList = helperProducto.getProducByCode(codigo);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter = new ProductoAdapter(productoList);
                    recyclerView.setAdapter(adapter);


                    dlgproduc.show();
                    liteDatabase.close();
                    Toast.makeText(this, "Se listo  Exitosomente", Toast.LENGTH_SHORT).show();

                } catch (Exception es) {
                    Log.e("error ", "NO se en listo  exitosamente" + es.getMessage());
                }
                break;

        }
    }

    public void cargarDialogo(View view) {
        cajaCodigo.setText("" + productoList.get(recyclerView.getChildAdapterPosition(view)).getCodigo());
        cajaDescripcion.setText( productoList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
        cajaPrecio.setText( "" +productoList.get(recyclerView.getChildAdapterPosition(view)).getPrecio());
        cajaCantidad.setText( "" +productoList.get(recyclerView.getChildAdapterPosition(view)).getCantidad());

    }
}
