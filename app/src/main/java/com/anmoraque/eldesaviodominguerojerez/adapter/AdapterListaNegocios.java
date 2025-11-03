package com.anmoraque.eldesaviodominguerojerez.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.anmoraque.eldesaviodominguerojerez.databinding.FilaNegociosBinding;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;

import java.util.List;

public class AdapterListaNegocios extends ListAdapter<Negocios, NegociosViewHolder> {

    // Constructor sin argumentos (opcional)
    public AdapterListaNegocios() {
        super(DIFF_CALLBACK);
    }

    // Constructor que recibe lista inicial
    public AdapterListaNegocios(List<Negocios> listaInicial) {
        super(DIFF_CALLBACK);
        submitList(listaInicial);
    }

    private static final DiffUtil.ItemCallback<Negocios> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Negocios>() {
                @Override
                public boolean areItemsTheSame(@NonNull Negocios oldItem, @NonNull Negocios newItem) {
                    return oldItem.getId() == newItem.getId(); // Necesitas un ID único en Negocios
                }

                @Override
                public boolean areContentsTheSame(@NonNull Negocios oldItem, @NonNull Negocios newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public NegociosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FilaNegociosBinding binding = FilaNegociosBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new NegociosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NegociosViewHolder holder, int position) {
        Negocios negocio = getItem(position);
        holder.cargarNegocioEnViewHolder(negocio);

        // Click listener para abrir Google Maps
        holder.itemView.setOnClickListener(v -> {
            String url_maps = negocio.getEnlaceMaps(); // Asegúrate de que exista este método en Negocios
            Log.d("ETIQUETA_LOG", "Negocio tocado: " + negocio.getNombre());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_maps));
            v.getContext().startActivity(intent);
        });
    }
}

