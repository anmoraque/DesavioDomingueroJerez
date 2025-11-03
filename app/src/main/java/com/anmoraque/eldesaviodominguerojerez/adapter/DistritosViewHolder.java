package com.anmoraque.eldesaviodominguerojerez.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anmoraque.eldesaviodominguerojerez.databinding.FilaDistritosBinding;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;

public class DistritosViewHolder extends RecyclerView.ViewHolder {

    private final FilaDistritosBinding binding;

    public DistritosViewHolder(@NonNull FilaDistritosBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void cargarDistritosEnViewHolder(Distritos distrito) {
        binding.nombreDistrito.setText(distrito.getNombre());
        binding.informacionDistrito.setText(distrito.getInformacion());
        // Aquí puedes cargar imágenes si agregas un campo imagen en Distritos
    }
}

