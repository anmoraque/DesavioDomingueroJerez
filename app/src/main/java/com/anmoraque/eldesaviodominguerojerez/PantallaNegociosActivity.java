package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.anmoraque.eldesaviodominguerojerez.adapter.AdapterListaNegocios;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;

import java.util.ArrayList;
import java.util.List;

public class PantallaNegociosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNegocios;
    private AdapterListaNegocios adapterListaNegocios;
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
        Negocios negocio1 = new Negocios(0, 0, R.drawable.cero, "Bazar y alimentación Fang", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 24:00", "Avenida de Méjico, 10", "https://g.page/bazarfang?share");
        Negocios negocio2 = new Negocios(1, 0, R.drawable.uno, "Alimentación", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Calle Fernando Viola, 7", "https://goo.gl/maps/ALckMg7aJrUoiEL49");
        Negocios negocio3 = new Negocios(2, 0, R.drawable.dos, "Super Market", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 23:00", "Calle Santo Domingo, 13B", "https://goo.gl/maps/WT1AWwVz9eNzxGZQ7");
        Negocios negocio4 = new Negocios(3, 0, R.drawable.prueba, "El Capricho Gourmet", "Tienda típica con alimentación y bebidas.","9:30 – 14:30", "Callejón de los Bolos, 24", "https://goo.gl/maps/FUGTvKmKbMswVX3j9");
        Negocios negocio5 = new Negocios(4, 0, R.drawable.cuatro, "Alimentación Jerez - Centro", "Tienda típica con alimentación y bebidas.","9:30 – 14:30", "Calle Santo Domingo", "https://goo.gl/maps/3hLo1bzTiEPvAT6N6");
        Negocios negocio6 = new Negocios(5, 0, R.drawable.cinco, "Alimentación y Bazar", "Tienda típica china con casi de todo. Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.","9:30 – 22:30", "Urbanización, Calle Sevilla, 24", "https://goo.gl/maps/yvTLnoM2NrckKpMe8");
        Negocios negocio7 = new Negocios(6, 0, R.drawable.seis, "Kiosco Juan Carlos", "Tienda típica con alimentación, chuches y bebidas.","9:30 – 15:00", "Calle Contadora, 3c", "https://goo.gl/maps/2xjrwnT7PNP29fE37");
        Negocios negocio8 = new Negocios(7, 0, R.drawable.siete, "Alimentación confitería Gabriela", "Tienda típica con alimentación, chuches y bebidas.","9:30 – 14:00", "Calle Contadora, 5Z", "https://goo.gl/maps/1JZTc8xrmR8e2iSa8");
        Negocios negocio9 = new Negocios(8, 0, R.drawable.ocho, "Bazar y Confitería El Almendral", "Tienda típica con alimentación, chuches y bebidas.","9:30 – 14:45", "Avenida De La Comedia Urb, Local 20", "https://g.page/bazarconfiteriaelalmendral?share");
        Negocios negocio10 = new Negocios(9, 0, R.drawable.nueve, "Alimentación San Joaquin", "Tienda típica con alimentación, chuches y bebidas.","9:30 – 14:00", "Avenida de Sudamérica, 9", "https://goo.gl/maps/XTratVwkajtf9xjWA");
        Negocios negocio11 = new Negocios(10, 0, R.drawable.prueba, "La Esquinita", "Tienda típica con alimentación, chuches y bebidas.","9:30 – 15:00", "Calle Santo Domingo", "https://goo.gl/maps/yXfENsDpx8gy2dvYA");
        Negocios negocio12 = new Negocios(11, 0, R.drawable.once, "Coviran", "Supermercado de Alimentación.","7:30 – 15:00", "Calle de Montevideo, 30c", "https://goo.gl/maps/nh17LRVuFqEimqeD8");

        lista_negocios.add(negocio1);
        lista_negocios.add(negocio2);
        lista_negocios.add(negocio3);
        lista_negocios.add(negocio4);
        lista_negocios.add(negocio5);
        lista_negocios.add(negocio6);
        lista_negocios.add(negocio7);
        lista_negocios.add(negocio8);
        lista_negocios.add(negocio9);
        lista_negocios.add(negocio10);
        lista_negocios.add(negocio11);
        lista_negocios.add(negocio12);

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