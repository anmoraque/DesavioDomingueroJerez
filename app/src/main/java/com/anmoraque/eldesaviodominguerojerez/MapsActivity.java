package com.anmoraque.eldesaviodominguerojerez;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.anmoraque.eldesaviodominguerojerez.model.Negocios;
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Todo Traer la lista negocio de la base de datos
        //Creo una lista nueva de Negocios por cada Distrito, que solo lleva negocios del distrito seleccionado
        List<Negocios> lista_negocios = new ArrayList<>();
        Negocios negocios1 = new Negocios(0, 0, "https://i.postimg.cc/W12LLF80/cero.jpg", "Bazar y alimentación Fang", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.", "9:30 – 24:00", "Avenida de Méjico, 10", "https://g.page/bazarfang?share", 36.69304034794886, -6.128592426981506);
        Negocios negocios2 = new Negocios(1, 0, "https://i.postimg.cc/W12LLF80/cero.jpg", "Alimentación", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.", "9:30 – 24:00", "Avenida de Méjico, 10", "https://goo.gl/maps/ALckMg7aJrUoiEL49", 36.691556145354966, -6.129519653372688);
        Negocios negocios3 = new Negocios(2, 0, "https://i.postimg.cc/W12LLF80/cero.jpg", "Super Market", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.", "9:30 – 24:00", "Avenida de Méjico, 10", "https://goo.gl/maps/WT1AWwVz9eNzxGZQ7", 36.691246241669866, -6.13201195337269);
        Negocios negocios4 = new Negocios(3, 0, "https://i.postimg.cc/W12LLF80/cero.jpg", "El Capricho Gourmet", "Tienda típica china con casi de todo.Alimentación, bebidas, droguería, ferretería, papelería, juguetes, lencería, etc.", "9:30 – 24:00", "Avenida de Méjico, 10", "https://goo.gl/maps/FUGTvKmKbMswVX3j9", 36.68957179020865, -6.135919282209103);
        lista_negocios.add(negocios1);
        lista_negocios.add(negocios2);
        lista_negocios.add(negocios3);
        lista_negocios.add(negocios4);


        // Con este for each obtengo todos los negocios de la lista para añadirlos a cada marcador
        for (Negocios negocios: lista_negocios) {
            if (lista_negocios.indexOf(negocios) == negocios.getId()) {
                //Añado latitud y longitud al marcador
                LatLng n = new LatLng(negocios.getLatitude(), negocios.getLongitude());
                //Añado el titulo, descripcion
                mMap.addMarker(new MarkerOptions().position(n).title(negocios.getNombre()).snippet(negocios.getDireccion()));
                //Hago zoom para ver mas cerca el negocio
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(n, 13));

                //Escucho el marcador tocado Todo (No funciona bien) me enlaza al enlace del ultimo negocio
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        //Mediante un Intent mando el enlace a Google maps del marcador tocado
                        String url_maps = negocios.getEnlace_maps();
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url_maps));
                        startActivity(intent);
                    }
                });

            }
        }
    }
}