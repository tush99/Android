package com.example.myapplication.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myapplication.Modelo.Producto;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class HelperProducto extends SQLiteOpenHelper {

    public HelperProducto(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE producto(codigo int not Null , descripcion text(50) not Null, precio double(2) not Null,cantidad int(2) Not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    //primer metodo
    public void insertar(Producto producto) {
        ContentValues values = new ContentValues();
        values.put("codigo",producto.getCodigo());
        values.put("descripcion", producto.getDescripcion());
        values.put("precio", producto.getPrecio());
        values.put("cantidad", producto.getCantidad());
        this.getWritableDatabase().insert("producto", null, values); //me de vuelve un objeto de tipo sqllite

    }
    public List<Producto> getAll(){
        List<Producto> lista = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM producto",null);
        if (cursor.moveToFirst()){
            do {
                Producto producto = new Producto();
                producto.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                producto.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                producto.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                producto.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                lista.add(producto);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  lista;
    }
    public void modifican(Producto producto){
        ContentValues values = new ContentValues();
        values.put("descripcion", producto.getDescripcion());
        values.put("precio", producto.getPrecio());
        values.put("cantidad", producto.getCantidad());
        this.getWritableDatabase().update("producto",values,"codigo = " + producto.getCodigo() ,null);
    }
    public void Elminar(Producto producto){
        this.getWritableDatabase().delete("producto","codigo = " + producto.getCodigo(),null);


    }
    public void ElminarTodos(){
        this.getWritableDatabase().delete("producto",null,null);
    }

    public List<Producto> getProducByCode(int code){
        List<Producto> productos = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM producto WHERE codigo="+code,null);
        if (cursor.moveToFirst()){
            do {
                Producto producto = new Producto();
                producto.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                producto.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                producto.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                producto.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                productos.add(producto);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return productos;
    }

}
