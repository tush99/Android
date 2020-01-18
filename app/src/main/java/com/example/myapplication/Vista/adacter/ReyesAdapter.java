package com.example.myapplication.Vista.adacter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Modelo.Reyes;
import com.example.myapplication.R;

import java.util.List;

public class ReyesAdapter extends RecyclerView.Adapter<ReyesAdapter.ViewHolderReyes> {
    List<Reyes> reyesList;

    public ReyesAdapter(List<Reyes> reyes) {
        this.reyesList = reyes;
    }

    @NonNull
    @Override
    public ReyesAdapter.ViewHolderReyes onCreateViewHolder(@NonNull ViewGroup viewGroupo, int viewType) {
        View view = LayoutInflater.from(viewGroupo.getContext()).inflate(R.layout.item_reyes,null);
        return new ViewHolderReyes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReyesAdapter.ViewHolderReyes viewHolderReyes, int position) {
            viewHolderReyes.datoNombres.setText(reyesList.get(position).getNombre());
            viewHolderReyes.datosPeriodo.setText(reyesList.get(position).getPeriodo());
    }

    public int getItemCount() {
        return reyesList.size();
    }


    public static class ViewHolderReyes extends RecyclerView.ViewHolder {
        TextView datoNombres;
        TextView datosPeriodo;

        public ViewHolderReyes(@NonNull View itemView) {
            super(itemView);
            datoNombres = itemView.findViewById(R.id.txtNombrReyes);
            datosPeriodo = itemView.findViewById(R.id.txtPeriodo);
        }
    }
}
