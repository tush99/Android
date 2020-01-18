package com.example.myapplication.Vista.adacter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Modelo.Producto;
import com.example.myapplication.R;

import java.util.List;

public class ProductoAdapter extends  RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> implements View.OnClickListener{
    List<Producto> productoList;
    private View.OnClickListener clickProduc;
    public ProductoAdapter(List<Producto>productos){
        this.productoList = productos;
    }
    @NonNull
    @Override
    public ProductoAdapter.ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,null);
        view.setOnClickListener(this);
        return new ProductoAdapter.ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolderProducto viewHolderProducto, int position) {
        viewHolderProducto.datoCodigo.setText(""+productoList.get(position).getCodigo());
        viewHolderProducto.datoDescripcion.setText(productoList.get(position).getDescripcion());
        viewHolderProducto.datoCantidad.setText(""+productoList.get(position).getCantidad());
        viewHolderProducto.datoPrecio.setText(""+ productoList.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }
    public void setOnclickListener(View.OnClickListener onclickListener){
        this.clickProduc= onclickListener;
    }
    @Override
    public void onClick(View view) {
        if(clickProduc != null){
            clickProduc.onClick(view);
        }
    }

    public static class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView datoCodigo,datoDescripcion;
        TextView datoCantidad,datoPrecio;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            datoCodigo = itemView.findViewById(R.id.lblCodigoHelper);
            datoDescripcion = itemView.findViewById(R.id.lblDescripcionHelper);
            datoCantidad = itemView.findViewById(R.id.lblCantidadHelper);
            datoPrecio = itemView.findViewById(R.id.lblbPrecioHelper);
        }
    }
}
