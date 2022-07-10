package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
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

        //Creo la lista Negocios
        List<Negocios> lista_negocios = new ArrayList<>();

                                        //int id, int distrito, int foto, String nombre, String informacion, String horario, String direccion
        Negocios negocio1 = new Negocios(1, 1, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");
        Negocios negocio2 = new Negocios(2, 1, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");
        Negocios negocio3 = new Negocios(3, 1, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");
        Negocios negocio4 = new Negocios(4, 1, R.drawable.alimentacion, "Alimentación y Bazar", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");


        lista_negocios.add(negocio1);
        lista_negocios.add(negocio2);
        lista_negocios.add(negocio3);
        lista_negocios.add(negocio4);

        //Añado la lista al adapter que a su vez lo coloca en el recycler
        this.recyclerViewNegocios = findViewById(R.id.recicler_negocios);
        this.adapterListaNegocios = new AdapterListaNegocios(lista_negocios);
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