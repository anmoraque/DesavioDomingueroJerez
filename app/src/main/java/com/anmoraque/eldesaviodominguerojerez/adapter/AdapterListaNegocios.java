package com.anmoraque.eldesaviodominguerojerez.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.anmoraque.eldesaviodominguerojerez.PantallaNegociosActivity;
import com.anmoraque.eldesaviodominguerojerez.R;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;

/**
 * Creo el adapter que rellenará el holder
 */
public class AdapterListaNegocios extends RecyclerView.Adapter<NegociosViewHolder> {

    private List<Negocios> lista_negocios_distrito;

    public AdapterListaNegocios(List<Negocios> list_negocios)
    {
        this.lista_negocios_distrito = list_negocios;
    }

    /**
     *Este método "infla el holder"
     */
    @NonNull
    @Override
    public NegociosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NegociosViewHolder negociosViewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View fila_negocio = layoutInflater.inflate(R.layout.fila_negocios, parent, false);
        negociosViewHolder = new NegociosViewHolder(fila_negocio);


        /**
         *Escucho la linea tocada
        negociosViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int negocio_tocado = (int) v.getTag(); //v es la fila del distrito
                Log.d("ETIQUETA_LOG", "Negocio tocado número: " + negocio_tocado);



                Negocios negocios = new Negocios(negocio_tocado);

                String url_maps = negocios.getEnlace_maps();
                Intent intent_abrir_web = new Intent();
                intent_abrir_web.setAction(Intent.ACTION_VIEW);
                intent_abrir_web.setData(Uri.parse(url_maps));
                v.getContext().startActivity(intent_abrir_web);

            }
        });
         */

        return negociosViewHolder;
    }

    /**
     *Este método "rellena un holder" - lo recicla
     */
    @Override
    public void onBindViewHolder(@NonNull NegociosViewHolder holder, int position) {
        Negocios negociosDistrito = lista_negocios_distrito.get(position);
        holder.cargarNegocioEnViewHolder(negociosDistrito);
    }

    /**
     * Va a rellenar el holder hasta el final de la lista
     * @return
     */
    @Override
    public int getItemCount() {
        return lista_negocios_distrito.size();
    }
}
