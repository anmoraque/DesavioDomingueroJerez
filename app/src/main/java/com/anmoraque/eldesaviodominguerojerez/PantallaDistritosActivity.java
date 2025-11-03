package com.anmoraque.eldesaviodominguerojerez;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anmoraque.eldesaviodominguerojerez.adapter.AdapterListaDistritos;
import com.anmoraque.eldesaviodominguerojerez.databinding.ActivityPantallaDistritosBinding;
import com.anmoraque.eldesaviodominguerojerez.model.Distritos;

import java.util.ArrayList;
import java.util.List;

public class PantallaDistritosActivity extends AppCompatActivity {

    private ActivityPantallaDistritosBinding binding;
    private AdapterListaDistritos adapterListaDistritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewBinding
        binding = ActivityPantallaDistritosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Toolbar
        Toolbar toolbar = new Toolbar(this);
        // MODIFICADO: Usar string resource para el título
        toolbar.setTitle(R.string.toolbar_title_districts);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Crear lista de Distritos
        List<Distritos> listaDistritos = new ArrayList<>();
        // MODIFICADO: Usar string resources para nombres y descripciones de distritos
        listaDistritos.add(new Distritos(0, getString(R.string.district_name_north), getString(R.string.district_desc_north)));
        listaDistritos.add(new Distritos(1, getString(R.string.district_name_west), getString(R.string.district_desc_west)));
        listaDistritos.add(new Distritos(2, getString(R.string.district_name_center), getString(R.string.district_desc_center)));
        listaDistritos.add(new Distritos(3, getString(R.string.district_name_south), getString(R.string.district_desc_south)));
        listaDistritos.add(new Distritos(4, getString(R.string.district_name_northwest), getString(R.string.district_desc_northwest)));
        listaDistritos.add(new Distritos(5, getString(R.string.district_name_east), getString(R.string.district_desc_east)));

        // Configurar Adapter y RecyclerView
        adapterListaDistritos = new AdapterListaDistritos(listaDistritos);
        binding.reciclerDistritos.setAdapter(adapterListaDistritos);
        binding.reciclerDistritos.setLayoutManager(new LinearLayoutManager(this));
    }

    // Acción del botón de ir atrás
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
