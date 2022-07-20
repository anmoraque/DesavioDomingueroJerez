package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.anmoraque.eldesaviodominguerojerez.adapter.AdapterListaDistritos;
import com.anmoraque.eldesaviodominguerojerez.adapter.AdapterListaNegocios;
import com.anmoraque.eldesaviodominguerojerez.model.BaseDatos;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;

import java.util.ArrayList;
import java.util.List;

public class PantallaDistritosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDistritos;
    private AdapterListaDistritos adapterListaDistritos;
    private BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_distritos);
        //Boton de ir atras
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creo la lista Distritos
        List<Distritos> lista_distritos = new ArrayList<>();

                                            //int id, String nombre, String informacion
        Distritos distrito1 = new Distritos(0, "Norte", "Plaza del Caballo, Álvaro Domecq, San Joaquín, Torres de Córdoba, El Almendral, Montealto, El Altillo, Las Flores, El Bosque, Parque González Hontoria, Zona Hipercor, Jacaranda.");
        Distritos distrito2 = new Distritos(1, "Oeste", "Picadueña, San Valentín, Las Torres, Sagrada Familia, La Plata, La Coronación, Icovesa, Juan XXIII, Los Naranjos, El Calvario, Polígono San Benito,  Avd. de la Cruz Roja, La Unión.");
        Distritos distrito3 = new Distritos(2, "Centro", "San Mateo, San Dionisio, Catedral, Divina Pastora, La Constancia, San Pedro, Pío XII, Vallesequillo, Calle Porvenir, Plaza de las Angustias, Calle Corredera, Madre de Dios, Santiago.");
        Distritos distrito4 = new Distritos(3, "Sur", "Estancia Barrera, Vista Alegre, Torresoto, Hijuela de las Coles, Federico Mayo, San Telmo, Liberación, El Portal, Blas Infante, Santo Tomás de Aquino, Puertas del Sur, Torres del Sur.");
        Distritos distrito5 = new Distritos(4, "Noroeste", "El Pelirón, Torresblancas, San José Obrero, La Granja, San Enrique, Los Pinos, Chapín, La Marquesa, Jerez Norte, La Cañada, Avd. Lola Flores, Santa Teresa, Avd. Europa.");
        Distritos distrito6 = new Distritos(5, "Este", "La Vid, Zafer, La Asunción, Olivar de Rivero, El Retiro, Princijerez, Nueva Andalucía, Montealegre, Pago San José, Parque Atlántico, La Canaleja, La Pita, La Milagrosa, Villa del Este.");

        /** No funciona
        baseDatos.insertarDistritos(distrito1);
        baseDatos.insertarDistritos(distrito2);
        baseDatos.insertarDistritos(distrito3);
        baseDatos.insertarDistritos(distrito4);
        baseDatos.insertarDistritos(distrito5);
        baseDatos.insertarDistritos(distrito6);
         */

        lista_distritos.add(distrito1);
        lista_distritos.add(distrito2);
        lista_distritos.add(distrito3);
        lista_distritos.add(distrito4);
        lista_distritos.add(distrito5);
        lista_distritos.add(distrito6);


        //Añado la lista al adapter que a su vez lo coloca en el recycler
        this.recyclerViewDistritos = findViewById(R.id.recicler_distritos);
        this.adapterListaDistritos = new AdapterListaDistritos(lista_distritos);
        this.recyclerViewDistritos.setAdapter(this.adapterListaDistritos);

        //Defino el estilo de la lista / la distribución
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.recyclerViewDistritos.setLayoutManager(layoutManager);

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
