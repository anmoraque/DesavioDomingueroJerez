package com.anmoraque.eldesaviodominguerojerez.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.anmoraque.eldesaviodominguerojerez.PantallaNegociosActivity;
import com.anmoraque.eldesaviodominguerojerez.R;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;


import java.util.List;


 //Creo el adapter que rellenará el holder
public class AdapterListaDistritos extends RecyclerView.Adapter<DistritosViewHolder>{

    private List<Distritos> lista_distritos;

    public AdapterListaDistritos(List<Distritos> list_distritos)
    {
        this.lista_distritos = list_distritos;
    }

     //Este método "infla el holder"
    @NonNull
    @Override
    public DistritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DistritosViewHolder distritosViewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View fila_distrito = layoutInflater.inflate(R.layout.fila_distritos, parent, false);
        distritosViewHolder = new DistritosViewHolder(fila_distrito);

         //Escucho la linea tocada
        distritosViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int distrito = (int) v.getTag(); //v es la fila del distrito
                Log.d("ETIQUETA_LOG", "Distrito tocado número: " + distrito);
                //Intent con el numero de distrito tocado
                Intent intent = new Intent(v.getContext(), PantallaNegociosActivity.class);
                intent.putExtra("distrito", distrito);
                v.getContext().startActivity(intent);
            }
        });

        return distritosViewHolder;
    }

     //Este método "rellena un holder" - lo recicla
    @Override
    public void onBindViewHolder(@NonNull DistritosViewHolder holder, int position) {

        Distritos list_distritos = lista_distritos.get(position);
        holder.cargarDistritosEnViewHolder(list_distritos);
        //Cojo la posicion del holder
        holder.itemView.setTag(position);
    }

     //Va a rellenar el holder hasta el final de la lista
    @Override
    public int getItemCount() {
        return lista_distritos.size();
    }

}
