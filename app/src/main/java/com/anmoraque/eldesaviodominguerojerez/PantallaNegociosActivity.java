package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.anmoraque.eldesaviodominguerojerez.adapter.AdapterListaNegocios;
import com.anmoraque.eldesaviodominguerojerez.model.BaseDatos;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;

import java.util.ArrayList;
import java.util.List;

public class PantallaNegociosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNegocios;
    private AdapterListaNegocios adapterListaNegocios;
    private BaseDatos baseDatos;
    private Integer[] ids_distritos = {1, 2, 3, 4, 5, 6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_negocios);
        //Boton de ir atras
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int distrito_seleccionado = getIntent().getIntExtra("distrito", 0);
        //DE FORMA IDEAL, EN LA BASE DE DATOS, DEBERÍAS TENER UN MÉTODO
        //List<Negocios> obtenerNegociosDeDistrito (distrito_seleccionado)
        //esto sería parecido al obtenerCochesDePersona del ejemplo que hicimos



        //Creo la lista Negocios
        List<Negocios> lista_negocios = new ArrayList<>();

                                        //int id, int distrito, int foto, String nombre, String informacion, String horario, String direccion
        Negocios negocio1 = new Negocios(0, 0, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");
        Negocios negocio2 = new Negocios(1, 0, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");
        Negocios negocio3 = new Negocios(2, 0, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");
        Negocios negocio4 = new Negocios(3, 0, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");

        /** No funciona
         baseDatos.insertarNegocios(negocio1);
         baseDatos.insertarNegocios(negocio2);
         baseDatos.insertarNegocios(negocio3);
         baseDatos.insertarNegocios(negocio4);
         */


        lista_negocios.add(negocio1);
        lista_negocios.add(negocio2);
        lista_negocios.add(negocio3);
        lista_negocios.add(negocio4);

        //Creo la lista Negocios por cada Distrito, que solo lleva negocios del distrito seleccionado
        List<Negocios> lista_negocios_distrito = new ArrayList<Negocios>();

        // Con este for obtengo todos los negocios que pertenecen al distrito seleccionado
        for (Negocios n : lista_negocios)
        {
            if (n.getDistro() == distrito_seleccionado)
            {
                lista_negocios_distrito.add(n);
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

    // Metodo para la accion del boton de ir atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return true;
    }
}