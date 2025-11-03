package com.anmoraque.eldesaviodominguerojerez;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anmoraque.eldesaviodominguerojerez.adapter.AdapterListaNegocios;
import com.anmoraque.eldesaviodominguerojerez.databinding.ActivityPantallaNegociosBinding;
import com.anmoraque.eldesaviodominguerojerez.model.Negocios;
import com.anmoraque.eldesaviodominguerojerez.model.ObtenerDatos;

import java.util.ArrayList;
import java.util.List;

public class PantallaNegociosActivity extends AppCompatActivity {

    private ActivityPantallaNegociosBinding binding;
    private AdapterListaNegocios adapterListaNegocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewBinding
        binding = ActivityPantallaNegociosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            // MODIFICADO: Usar string resource para el título
            getSupportActionBar().setTitle(R.string.negocio);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Obtener datos desde GitHub
        new ObtenerDatos(this).execute();
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

    // Método llamado desde ObtenerDatos al terminar la carga
    public void mostrarResultados(List<Negocios> listaNegociosBase) {
        Log.d("ETIQUETA_LOG", "Lista de negocios obtenida: " + listaNegociosBase);

        int distritoSeleccionado = getIntent().getIntExtra("distrito", 0);

        List<Negocios> listaNegociosDistrito = new ArrayList<>();
        for (Negocios negocio : listaNegociosBase) {
            if (negocio.getDistro() == distritoSeleccionado) {
                listaNegociosDistrito.add(negocio);
            }
        }

        adapterListaNegocios = new AdapterListaNegocios(listaNegociosDistrito);
        binding.reciclerNegocios.setAdapter(adapterListaNegocios);
        binding.reciclerNegocios.setLayoutManager(new LinearLayoutManager(this));
    }
}