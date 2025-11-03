package com.anmoraque.eldesaviodominguerojerez;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.anmoraque.eldesaviodominguerojerez.databinding.ActivityMapsBinding;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private ActivityMapsBinding binding;
    private GoogleMap mMap;
    private List<Negocios> listaNegocios;

    private LocationManager locationManager;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar SupportMapFragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Solicitar datos de negocios desde GitHub
        new ObtenerDatos(this).execute();

        // Centrar el mapa en Jerez
        LatLng centroJerez = new LatLng(36.694896, -6.125561);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroJerez, 13));

        // Permisos de ubicación
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 535);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void mostrarResultados(List<Negocios> listaNegociosBase) {
        this.listaNegocios = listaNegociosBase;

        for (int i = 0; i < listaNegocios.size(); i++) {
            Negocios negocio = listaNegocios.get(i);
            LatLng position = new LatLng(negocio.getLatitude(), negocio.getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(negocio.getNombre())
                    .snippet("Click aquí para más detalles")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.tienda)));
            marker.setTag(i);
        }

        // Listener para info window click
        mMap.setOnInfoWindowClickListener(marker -> {
            int index = (int) marker.getTag();
            Negocios negocio = listaNegocios.get(index);

            binding.nombreNegocioMap.setText(negocio.getNombre());
            binding.informacionNegocioMap.setText(negocio.getInformacion());
            binding.horarioNegocioMap.setText(negocio.getHorario());
            binding.direccionNegocioMap.setText(negocio.getDireccion());
            Picasso.get().load(negocio.getFoto()).into(binding.imagenNegocioMap);

            binding.cardViewMap.setVisibility(View.VISIBLE);
            binding.cardViewMap.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(negocio.getEnlaceMaps()));
                startActivity(intent);
                binding.cardViewMap.setVisibility(View.GONE);
            });
        });
    }

    // Permisos GPS
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (gpsActivado()) accederAlaUbicacionGPS();
            else solicitarActivacionGPS();
        } else {
            Toast.makeText(this, "Sin acceso a la ubicación", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (gpsActivado()) accederAlaUbicacionGPS();
        else Toast.makeText(this, "Sin acceso a la ubicación", Toast.LENGTH_LONG).show();
    }

    private boolean gpsActivado() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void accederAlaUbicacionGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult != null) {
                    Location location = locationResult.getLastLocation();
                    mostrarUbicacionObtenida(location);
                    fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    private void mostrarUbicacionObtenida(Location location) {
        LatLng ubicacionActual = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(ubicacionActual)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.posicion)));
    }

    private void solicitarActivacionGPS() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 77);
    }
}
