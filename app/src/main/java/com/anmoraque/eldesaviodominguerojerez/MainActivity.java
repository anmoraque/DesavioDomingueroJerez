package com.anmoraque.eldesaviodominguerojerez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean menu_visible;
    private final static String RUTA_TIENDA = "https://play.google.com/store/apps/details?id=com.anmoraque.eldesaviodominguerojerez";

    //Crea la actividad principal (savedInstanceState inicia menú lateral)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Forzar a no usar el tema night
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Boton de el menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Con esta instrucción personalizo el icono del menú que abre
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        //Envoltorio menú "animado"
        this.drawerLayout = findViewById(R.id.drawer);
        //El propio menú lateral
        this.navigationView = findViewById(R.id.navview);
        //Escuchando el menu
        this.navigationView.setNavigationItemSelectedListener(this);

    }

    //Metodo global para cambiar de actividad mediante Intent
    private void saltaActividad(Class actividad_destino) {
        Intent intent = new Intent(this, actividad_destino);
        startActivity(intent);
    }

    //Cuando tocas el icono del menú lo abres
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toca el icono del menú
        switch (item.getItemId())
        {
            case android.R.id.home :
                Log.d("ETIQUETA_LOG", "Hamburguesa tocada");
                if (menu_visible)
                {
                    //Ocultar el menu
                    drawerLayout.closeDrawers();
                    menu_visible =false;
                } else
                {
                    //Mostrar el menú
                    drawerLayout.openDrawer(GravityCompat.START);//se abre de derecha izquieras
                    menu_visible =true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Cuando tocas una opción del menú recibes el callback
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getOrder())
        {
            //Buscador por mapa
            case 0:
                saltaActividad(MapsActivity.class);
                break;
            //Buscador por distritos
            case 1:
                saltaActividad(PantallaDistritosActivity.class);
                break;
            //Adjuntar negocio nuevo
            case 2:
                saltaActividad(AdjuntarNuevoNegocioUsuarioActivity.class);
                break;
            //Ayuda sobre la App
            case 3:
                saltaActividad(PantallaAyudaActivity.class);
                break;
            //Compartir
            case 4:
                //Comparto por whatsapp
                try {
                    String texto_compartir = "Descárgate la APP";
                    texto_compartir = texto_compartir + " " + RUTA_TIENDA;
                    Intent intent_compartir = new Intent();
                    intent_compartir.setAction(Intent.ACTION_SEND);
                    intent_compartir.putExtra(Intent.EXTRA_TEXT, texto_compartir);
                    intent_compartir.setType("text/plain");
                    intent_compartir.setPackage("com.whatsapp");
                    startActivity(intent_compartir);
                } catch (Exception e){
                    //Si no tiene Whatsapp
                    Toast.makeText(this, "No tienes Whatsapp instalado", Toast.LENGTH_LONG).show();
                    }
                break;
            //Creditos
            case 5:
                saltaActividad(PantallaCreditosActivity.class);
                break;
        }
        return false;
    }
    //Linear para ir directo a Distritos
    public void IrADistritos(View view) {
        saltaActividad(PantallaDistritosActivity.class);
    }
    //Linear para ir directamente a Mapas
    public void IrAMapas(View view) {
        saltaActividad(MapsActivity.class);
    }
}