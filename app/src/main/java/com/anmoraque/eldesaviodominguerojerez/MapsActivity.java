package com.anmoraque.eldesaviodominguerojerez;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.anmoraque.eldesaviodominguerojerez.model.Negocios;
import com.anmoraque.eldesaviodominguerojerez.model.ObtenerDatos;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.anmoraque.eldesaviodominguerojerez.databinding.ActivityMapsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    //Constante para el mapa, la m dice que es un atributo de la clase "variable miembro"
    private GoogleMap mMap;
    //Constante para crear el mapa
    private ActivityMapsBinding binding;
    //Lista de nagocios
    private List<Negocios> lista_negocios;
    //Nos permite comprobar si el GPS está activo (u otro proveedor de ubicación)
    private LocationManager locationManager;
    //Este obtiene la ubicación
    private FusedLocationProviderClient fusedLocationProviderClient;
    //La precisión y la frencuencia, la determina éste
    private LocationRequest locationRequest;
    //Cuando el fused, tiene el dato, llama a éste, que es el callback
    private LocationCallback locationCallback;

    private ImageView imagen_negocio;
    private TextView textView_nombre_negocio;
    private TextView textView_informacion_negocio;
    private TextView textView_horario_negocio;
    private TextView textView_direccion_negocio;
    private CardView cardView;

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

        imagen_negocio = findViewById(R.id.imagen_negocio_map);
        textView_nombre_negocio = findViewById(R.id.nombre_negocio_map);
        textView_informacion_negocio = findViewById(R.id.informacion_negocio_map);
        textView_horario_negocio = findViewById(R.id.horario_negocio_map);
        textView_direccion_negocio = findViewById(R.id.direccion_negocio_map);
        cardView = findViewById(R.id.card_view_map);

        //Escucho el GPS para saber ubicacion
        this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    //Muestro la lista_negocios_base_datos
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void mostrarResultados (List<Negocios> lista_negocios_base_datos)
    {
        //Añado la lista_negocios_base_datos a lista_negocios
        this.lista_negocios = lista_negocios_base_datos;
        //Numero total de negocios es igual al numero de elementos de la lista
        int numero_total_negocios = this.lista_negocios.size();
        //Negocios
        Negocios negocios = null;
        //Con este accedo a la ubicacion del dispositivo (hay que ponerlo donde se quiera acceder)
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 535);

        // Con este for obtengo todos los negocios de la lista para añadirlos a cada marcador
        for (int num_negocio = 0; num_negocio < numero_total_negocios; num_negocio++)
            {
                //Añado cada negocio de la lista
                negocios = this.lista_negocios.get(num_negocio);
                //Añado latitud y longitud al marcador
                LatLng n = new LatLng (negocios.getLatitude(), negocios.getLongitude());
                //Añado el titulo, descripcion e icono al marcador
                Marker marcador = mMap.addMarker(new MarkerOptions().position(n).title(negocios.getNombre())
                        .snippet(negocios.getDireccion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.tienda)));
                //Pongo el num_negocio al Tag del marcador
                marcador.setTag(num_negocio);
                //Hago zoom para ver mas cerca el marcador centro_jerez
                LatLng centro_jerez = new LatLng (36.69489595157947, -6.125560997547282);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centro_jerez,  13));
                //Este metodo escucha el cambio de marcadores en el mapa
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        Log.d("ETIQUETA_LOG", "Bandera de marcador tocado");
                        //Pongo GONE la cardView
                        cardView.setVisibility(View.GONE);
                        return false;
                    }
                });

            }

        //Escucha el marcador tocado
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                //Si num_negocio_tocado es el mismo que el tag del marcador
                int num_negocio_tocado = (int) marker.getTag();
                //Negocio_tocado es igual al negocio de la lista_negocios
                Negocios negocio_tocado = MapsActivity.this.lista_negocios.get(num_negocio_tocado);

                //Añado toda la informacion del negocio_tocado a la cardView
                textView_nombre_negocio.setText(negocio_tocado.getNombre());
                textView_informacion_negocio.setText(negocio_tocado.getInformacion());
                textView_horario_negocio.setText(negocio_tocado.getHorario());
                textView_direccion_negocio.setText(negocio_tocado.getDireccion());
                //Necesito cargar la imagen del negocio URL y uso libreria Picasso
                Picasso.get().load(negocio_tocado.getFoto()).into(imagen_negocio);

                //Pongo visible la cardView
                cardView.setVisibility(View.VISIBLE);

                //Escucho el cardView
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Cojo el enlace a maps del Negocio_tocado
                        String url_maps = negocio_tocado.getEnlace_maps();
                        //Mediante un Intent mando el enlace a Google maps del cardView tocado
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url_maps));
                        startActivity(intent);
                        //Pongo GONE la cardView
                        cardView.setVisibility(View.GONE);
                    }
                });
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
    //Metodo para saber si esta el GPS activado
    private boolean gpsActivado ()
    {
        boolean gps_activo = false;

        gps_activo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return  gps_activo;
    }
    //Muestro la ubicacion
    private void mostrarUbicacionObtenida (Location location)
    {
        Log.d("ETIQUETA_LOG", "Mostrando la ubicación obtenida");
        LatLng ubicacion_actual =  new LatLng(location.getLatitude(),location.getLongitude());
        //Añado icono al marcador de ubicacion obtenida
        Marker ubicacion = mMap.addMarker(new MarkerOptions().position(ubicacion_actual)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.posicion)));

    }
    //Accedo a la ubicacion GPS
    private void accederAlaUbicacionGPS ()
    {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        //Quiero precisión alta para ubicacion
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //Cada 5 segundos, recibire una actualización de ubicacion
        locationRequest.setInterval(5000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {

                if (locationResult != null) {
                    //Obtengo la última ubicación
                    Location location = locationResult.getLastLocation();
                    mostrarUbicacionObtenida(location);
                    //Una vez obtenida la ubicación, desactivo el bucle, la frecuencia de pedirla
                    MapsActivity.this.fusedLocationProviderClient.removeLocationUpdates(MapsActivity.this.locationCallback);
                }
            }
        };
        //Android studio nos obliga antes de llamar a mostrarUbicacionObtenida, comprobar de nuevo que el acceso por gps (permiso peligroso, está concedido)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }

    }
    //Solicito que active el GPS
    private void solicitarActivicacionGPS()
    {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        //Metodo deprecado pero funciona bien
        startActivityForResult(intent, 77);
    }
    //Compruebo si hay acceso a la ubicacion y si no lo mando a que active el GPS
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("ETIQUETA_LOG", "Permiso ubicación concedido");
            if (gpsActivado())
            {
                accederAlaUbicacionGPS ();
            } else {
                solicitarActivicacionGPS();
            }
        } else {
            Log.d("ETIQUETA_LOG", "Permiso uso GPS denegado");
            Toast.makeText(this, "Sin acceso a la ubicación", Toast.LENGTH_LONG).show();
        }
    }
    //Compruebo si hay acceso a la ubicacion despues de mandarlo la primera vez a activar GPS
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (gpsActivado())
        {
            accederAlaUbicacionGPS();
        } else {
            Log.d("ETIQUETA_LOG", "Permiso uso GPS sigue denegado");
            Toast.makeText(this, "Sin acceso a la ubicación", Toast.LENGTH_LONG).show();
        }
    }

}