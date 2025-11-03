package com.anmoraque.eldesaviodominguerojerez.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.anmoraque.eldesaviodominguerojerez.PantallaNegociosActivity;
import com.anmoraque.eldesaviodominguerojerez.databinding.FilaDistritosBinding;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;

import java.util.List;

public class AdapterListaDistritos extends ListAdapter<Distritos, DistritosViewHolder> {

    // Constructor sin argumentos
    public AdapterListaDistritos() {
        super(DIFF_CALLBACK);
    }

    // Constructor que recibe lista inicial
    public AdapterListaDistritos(List<Distritos> listaInicial) {
        super(DIFF_CALLBACK);
        submitList(listaInicial);
    }

    private static final DiffUtil.ItemCallback<Distritos> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Distritos>() {
                @Override
                public boolean areItemsTheSame(@NonNull Distritos oldItem, @NonNull Distritos newItem) {
                    return oldItem.getId() == newItem.getId(); // Necesitas un ID único en Distritos
                }

                @Override
                public boolean areContentsTheSame(@NonNull Distritos oldItem, @NonNull Distritos newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public DistritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FilaDistritosBinding binding = FilaDistritosBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new DistritosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DistritosViewHolder holder, int position) {
        Distritos distrito = getItem(position);
        holder.cargarDistritosEnViewHolder(distrito);

        // Click listener para abrir PantallaNegociosActivity
        holder.itemView.setOnClickListener(v -> {
            int distritoId = distrito.getId();
            Log.d("ETIQUETA_LOG", "Distrito tocado número: " + distritoId);
            Intent intent = new Intent(v.getContext(), PantallaNegociosActivity.class);
            intent.putExtra("distrito", distritoId);
            v.getContext().startActivity(intent);
        });
    }
}
