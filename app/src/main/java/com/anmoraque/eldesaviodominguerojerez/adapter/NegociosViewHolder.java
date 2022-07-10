package com.anmoraque.eldesaviodominguerojerez.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anmoraque.eldesaviodominguerojerez.R;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;


/**
 * Esta clase es una vista invisible que contiene a cada fila visible
 * en un momento dado
 */
public class NegociosViewHolder extends RecyclerView.ViewHolder {

    private ImageView imagen_negocio;
    private TextView textView_nombre_negocio;
    private TextView textView_informacion_negocio;
    private TextView textView_horario_negocio;
    private TextView textView_direccion_negocio;

    /**
     * Llamo a todos los views del xml fila_negocios
     * @param linearPadreFila es la fila_negocios
     */
    public NegociosViewHolder(@NonNull View linearPadreFila) {
        super(linearPadreFila);
        this.imagen_negocio = linearPadreFila.findViewById(R.id.imagen_negocio);
        this.textView_nombre_negocio = linearPadreFila.findViewById(R.id.nombre_negocio);
        this.textView_informacion_negocio = linearPadreFila.findViewById(R.id.informacion_negocio);
        this.textView_horario_negocio = linearPadreFila.findViewById(R.id.horario_negocio);
        this.textView_direccion_negocio = linearPadreFila.findViewById(R.id.direccion_negocio);

        //Escucho la linea tocada
        linearPadreFila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ETIQUETA_LOG", "Negocio tocado número: " + getAdapterPosition());
            }
        });

    }

    /**
     * "RELLENAR EL HOLDER" con la información de una fila visible
     * @param negociosDistrito es la información que uso para rellenar
     */
    public void cargarNegocioEnViewHolder(Negocios negociosDistrito)
    {
        this.imagen_negocio.setImageResource(negociosDistrito.getFoto());
        this.textView_nombre_negocio.setText(negociosDistrito.getNombre());
        this.textView_informacion_negocio.setText(negociosDistrito.getInformacion());
        this.textView_horario_negocio.setText(negociosDistrito.getHorario());
        this.textView_direccion_negocio.setText(negociosDistrito.getDireccion());

    }
}
