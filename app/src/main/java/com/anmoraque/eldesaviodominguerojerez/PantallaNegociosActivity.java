package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.anmoraque.eldesaviodominguerojerez.adapter.AdapterListaNegocios;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;
import com.anmoraque.eldesaviodominguerojerez.model.ObtenerDatos;

import java.util.ArrayList;
import java.util.List;

public class PantallaNegociosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNegocios;
    private AdapterListaNegocios adapterListaNegocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_negocios);
        //Boton de ir atras
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Obtengo los datos de la base (GitHub)
        new ObtenerDatos(this).execute();
    }

    //Metodo para la accion del boton de ir atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return true;
    }

    //Muestro la Lista de Negocios
    public void mostrarResultados (List<Negocios> lista_negocios_base_datos)
    {
        Log.d("ETIQUETA_LOG", "Lista de Negocios de la base de datos = " + lista_negocios_base_datos);

        //Recuperamos el Intent con el numero de distrito tocado
        int distrito_seleccionado = getIntent().getIntExtra("distrito", 0);

        //Creo una lista nueva de Negocios por cada Distrito, que solo lleva negocios del distrito seleccionado
        List<Negocios> lista_negocios_distrito = new ArrayList<>();

        // Con este for each obtengo todos los negocios que pertenecen al distrito seleccionado
       for (Negocios negocios : lista_negocios_base_datos)
        {
          if (negocios.getDistro() == distrito_seleccionado)
            {
               lista_negocios_distrito.add(negocios);
            }
        }


        //Añado la lista_negocios al adapter que a su vez lo coloca en el recycler
        this.recyclerViewNegocios = findViewById(R.id.recicler_negocios);
        this.adapterListaNegocios = new AdapterListaNegocios(lista_negocios_distrito);
        this.recyclerViewNegocios.setAdapter(this.adapterListaNegocios);

        //Defino el estilo de la lista / la distribución
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.recyclerViewNegocios.setLayoutManager(layoutManager);

    }
}