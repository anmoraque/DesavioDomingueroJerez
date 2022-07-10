package com.anmoraque.eldesaviodominguerojerez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean menu_visible;

    /**
     * Crea la actividad principal
     * @param savedInstanceState inicia menú lateral
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Con esta instrucción personalizo el icono del menú que abre
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        //Envoltorio menú "animado"
        this.drawerLayout = findViewById(R.id.drawer);
        //El propio menú lateral
        this.navigationView = findViewById(R.id.navview);

        this.navigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * Metodo global para cambiar de actividad
     * @param actividad_destino me manda a la actividad de destino
     */
    private void saltaActividad(Class actividad_destino) {
        Intent intent = new Intent(this, actividad_destino);
        startActivity(intent);
    }

    /**
     *
     * @param item Cuando tocas el icono del menú lo abres
     * @return
     */
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

    /**
     *
     * @param item Cuando tocas una opción del menú recibes el callback
     * @return item tocado
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getOrder())
        {
            case 0:
                saltaActividad(PantallaMapaUbicacionActivity.class);
                break;
            case 1:
                saltaActividad(PantallaDistritosActivity.class);
                break;
            case 2:
                saltaActividad(AdjuntarNuevoNegocioUsuarioActivity.class);
                break;
            case 3:
                saltaActividad(PantallaAyudaActivity.class);
                break;
            case 4:
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
        saltaActividad(PantallaMapaUbicacionActivity.class);
    }
}