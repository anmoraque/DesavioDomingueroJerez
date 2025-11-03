package com.anmoraque.eldesaviodominguerojerez;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;

import com.anmoraque.eldesaviodominguerojerez.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private boolean menuVisible;
    // MODIFICADO: RUTA_TIENDA es una constante, no un string de UI, pero puede ser bueno externalizarlo
    // Si la ruta no cambia, se puede dejar aquí. Si es necesaria la traducción/variación, externalizarla en strings.xml
    private static final String RUTA_TIENDA = "https://play.google.com/store/apps/details?id=com.anmoraque.eldesaviodominguerojerez";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Compatibilidad con WindowInsets (Android 14+)
        getWindow().setDecorFitsSystemWindows(true);

        // Forzar modo claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Configurar Toolbar como ActionBar
        setSupportActionBar(binding.toolbar);

        // Configurar icono del Drawer
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        binding.toolbar.setNavigationOnClickListener(v -> toggleDrawer());

        // Configurar Navigation Drawer
        binding.navview.setNavigationItemSelectedListener(this);
    }

    /** Alterna apertura/cierre del Drawer */
    private void toggleDrawer() {
        if (!hayConexionInternet()) {
            // MODIFICADO: Usar string resource para el Toast
            Toast.makeText(this, R.string.toast_no_internet, Toast.LENGTH_LONG).show();
            return;
        }

        if (menuVisible) {
            binding.drawer.closeDrawer(GravityCompat.START);
        } else {
            binding.drawer.openDrawer(GravityCompat.START);
        }
        menuVisible = !menuVisible;
    }

    /** Maneja selección de items del menú lateral usando IDs modernos */
    @Override
    public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_maps:
                saltaActividad(MapsActivity.class);
                break;
            case R.id.nav_distritos:
                saltaActividad(PantallaDistritosActivity.class);
                break;
            case R.id.nav_agregar_negocio:
                saltaActividad(AdjuntarNuevoNegocioUsuarioActivity.class);
                break;
            case R.id.nav_ayuda:
                saltaActividad(PantallaAyudaActivity.class);
                break;
            case R.id.nav_compartir:
                compartirApp();
                break;
            case R.id.nav_creditos:
                saltaActividad(PantallaCreditosActivity.class);
                break;
        }

        binding.drawer.closeDrawer(GravityCompat.START);
        menuVisible = false;
        return true;
    }

    /** Método global para iniciar otra actividad */
    private void saltaActividad(Class<?> actividadDestino) {
        startActivity(new Intent(this, actividadDestino));
    }

    /** Compartir app vía WhatsApp */
    private void compartirApp() {
        try {
            // MODIFICADO: Usar string resource para el texto a compartir
            String textoCompartir = getString(R.string.share_app_text) + " " + RUTA_TIENDA;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, textoCompartir);
            intent.setType("text/plain");
            intent.setPackage("com.whatsapp");
            startActivity(intent);
        } catch (Exception e) {
            // MODIFICADO: Usar string resource para el Toast de error
            Toast.makeText(this, R.string.toast_whatsapp_not_installed, Toast.LENGTH_LONG).show();
        }
    }

    /** Botón directo a Distritos */
    public void IrADistritos(android.view.View view) {
        if (hayConexionInternet()) saltaActividad(PantallaDistritosActivity.class);
            // MODIFICADO: Usar string resource para el Toast
        else Toast.makeText(this, R.string.toast_no_internet, Toast.LENGTH_LONG).show();
    }

    /** Botón directo a Mapas */
    public void IrAMapas(android.view.View view) {
        if (hayConexionInternet()) saltaActividad(MapsActivity.class);
            // MODIFICADO: Usar string resource para el Toast
        else Toast.makeText(this, R.string.toast_no_internet, Toast.LENGTH_LONG).show();
    }

    /** Verifica si hay conexión a Internet */
    private boolean hayConexionInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}