package com.anmoraque.eldesaviodominguerojerez.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anmoraque.eldesaviodominguerojerez.databinding.FilaNegociosBinding;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;
import com.squareup.picasso.Picasso;

public class NegociosViewHolder extends RecyclerView.ViewHolder {

    private final FilaNegociosBinding binding;

    public NegociosViewHolder(@NonNull FilaNegociosBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void cargarNegocioEnViewHolder(Negocios negocio) {
        Picasso.get().load(negocio.getFoto()).into(binding.imagenNegocio);
        binding.nombreNegocio.setText(negocio.getNombre());
        binding.informacionNegocio.setText(negocio.getInformacion());
        binding.horarioNegocio.setText(negocio.getHorario());
        binding.direccionNegocio.setText(negocio.getDireccion());
    }
}

