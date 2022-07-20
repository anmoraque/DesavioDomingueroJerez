package com.anmoraque.eldesaviodominguerojerez.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anmoraque.eldesaviodominguerojerez.R;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;

public class DistritosViewHolder extends RecyclerView.ViewHolder{


    private TextView textView_nombre_distrito;
    private TextView textView_informacion_distrito;

    /**
     * Llamo a todos los views del xml fila_distrito
     * @param linearPadreFila es la fila_distrito
     */
    public DistritosViewHolder(@NonNull View linearPadreFila) {
        super(linearPadreFila);
        this.textView_nombre_distrito = linearPadreFila.findViewById(R.id.nombreDistrito);
        this.textView_informacion_distrito = linearPadreFila.findViewById(R.id.informacionDistrito);

    }

    /**
     * "RELLENAR EL HOLDER" con la información de una fila visible
     * @param distrito es la información que uso para rellenar
     */
    public void cargarDistritosEnViewHolder(Distritos distrito)
    {
        this.textView_nombre_distrito.setText(distrito.getNombre());
        this.textView_informacion_distrito.setText(distrito.getInformacion());

    }
}
