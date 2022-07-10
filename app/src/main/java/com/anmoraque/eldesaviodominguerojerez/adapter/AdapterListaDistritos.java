package com.anmoraque.eldesaviodominguerojerez.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.anmoraque.eldesaviodominguerojerez.R;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;


import java.util.List;

/**
 * Creo el adapter que rellenará el holder
 */
public class AdapterListaDistritos extends RecyclerView.Adapter<DistritosViewHolder>{

    private List<Distritos> lista_distritos;

    public AdapterListaDistritos(List<Distritos> list_distritos)
    {
        this.lista_distritos = list_distritos;
    }

    /**
     *Este método "infla el holder"
     */
    @NonNull
    @Override
    public DistritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DistritosViewHolder distritosViewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View fila_distrito = layoutInflater.inflate(R.layout.fila_distritos, parent, false);
        distritosViewHolder = new DistritosViewHolder(fila_distrito);

        return distritosViewHolder;
    }

    /**
     *Este método "rellena un holder" - lo recicla
     */
    @Override
    public void onBindViewHolder(@NonNull DistritosViewHolder holder, int position) {

        Distritos list_distritos = lista_distritos.get(position);
        holder.cargarDistritosEnViewHolder(list_distritos);
    }
    /**
     * Va a rellenar el holder hasta el final de la lista
     * @return
     */
    @Override
    public int getItemCount() {
        return lista_distritos.size();
    }

}
