package com.anmoraque.eldesaviodominguerojerez;


import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


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

import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<Negocios> lista_negocios;

    //Cargar el mapa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflo el mapa
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtengo el SupportMapFragment y get notified cuando el mapa esta listo para usar..
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Muestro la lista_negocios_base_datos
    public void mostrarResultados (List<Negocios> lista_negocios_base_datos)
    {
        //Añado la lista_negocios_base_datos a lista_negocios
        this.lista_negocios = lista_negocios_base_datos;
        //Numero total de negocios es igual al numero de elementos de la lista
        int numero_total_negocios = this.lista_negocios.size();
        //Negocios
        Negocios negocios = null;
        // Con este for obtengo todos los negocios de la lista para añadirlos a cada marcador
        for (int num_negocio = 0; num_negocio < numero_total_negocios; num_negocio++)
            {
                //Añado cada negocio de la lista
                negocios = this.lista_negocios.get(num_negocio);
                //Añado latitud y longitud al marcador
                LatLng n = new LatLng (negocios.getLatitude(), negocios.getLongitude());
                //Añado el titulo, descripcion al marcador
                Marker marcador = mMap.addMarker(new MarkerOptions().position(n).title(negocios.getNombre()).snippet(negocios.getDireccion()));
                //Pongo el num_negocio al Tag del marcador
                marcador.setTag(num_negocio);
                //Hago zoom para ver mas cerca el marcador centro_jerez
                LatLng centro_jerez = new LatLng (36.69489595157947, -6.125560997547282);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro_jerez,  13));

            }

        //Escucha el marcador tocado
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Mediante un Intent mando el enlace a Google maps del negocio_tocado
                //Si num_negocio_tocado es el mismo que el tag del marcador
                int num_negocio_tocado = (int) marker.getTag();
                //Negocio_tocado es igual al negocio de la lista_negocios
                Negocios negocio_tocado = MapsActivity.this.lista_negocios.get(num_negocio_tocado);
                //Cojo el enlace a maps del Negocio_tocado
                String url_maps = negocio_tocado.getEnlace_maps();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url_maps));
                startActivity(intent);
            }
        });
    }
    //Se invoca cuando se ha terminado de cargar el mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Obtengo los datos de la base (GitHub)
        new ObtenerDatos(this).execute();

    }

}