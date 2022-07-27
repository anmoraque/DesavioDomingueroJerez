package com.anmoraque.eldesaviodominguerojerez;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.anmoraque.eldesaviodominguerojerez.model.Negocios;
import com.anmoraque.eldesaviodominguerojerez.model.ObtenerDatos;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.anmoraque.eldesaviodominguerojerez.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<Negocios> lista_negocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void mostrarResultados (List<Negocios> lista_negocios_base_datos)
    {
        this.lista_negocios = lista_negocios_base_datos;
        // Con este for each obtengo todos los negocios de la lista para añadirlos a cada marcador
        int numero_total_negocios = this.lista_negocios.size();
        Negocios negocios = null;
        for (int num_negocio = 0; num_negocio < numero_total_negocios;num_negocio++)

            {
                negocios = this.lista_negocios.get(num_negocio);
                //Añado latitud y longitud al marcador
                LatLng n = new LatLng(negocios.getLatitude(), negocios.getLongitude());
                //Añado el titulo, descripcion
                Marker marcador = mMap.addMarker(new MarkerOptions().position(n).title(negocios.getNombre()).snippet(negocios.getDireccion()));
                marcador.setTag(num_negocio);
                //Hago zoom para ver mas cerca el negocio
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(n, 13));

                //Escucho el marcador tocado Todo (No funciona bien) me enlaza al enlace del ultimo negocio

            }


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Mediante un Intent mando el enlace a Google maps del marcador tocado
                int num_negocio_tocado = (int)marker.getTag();
                Negocios negocio_tocado = MapsActivity.this.lista_negocios.get(num_negocio_tocado);
                String url_maps = negocio_tocado.getEnlace_maps();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url_maps));
                startActivity(intent);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new ObtenerDatos(this).execute();

    }

    /**
     * SOLUCIÓN MÁS GENERAL PARA EL POST PROCESO DE UNA ASYNCTASK
     *
     * public interface RemoteActivity {
     *     void mostrarResultados(int codigo_respuesta, Object objeto_respuesta);
     * }
     *
     * MapsActivity implement RemoteActivity
     * PantallaNegociosActivity implement RemoteActivity
     *
     * si hacemos esto, el método mostrarResultados, debería estar en las dos clases
     * @Override
     * protected void onPostExecute(Integer codigo_respuesta) {
     *     Log.d(Constantes.TAG_APP, "en onPostExecute respuesta = " + codigo_respuesta);
     *
     *  ((RemoteActivity)this.actividad_llamante)
     * .mostrarResultados (codigo_respuesta, null);
     * }
     */
}