package com.anmoraque.eldesaviodominguerojerez.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.anmoraque.eldesaviodominguerojerez.MapsActivity;
import com.anmoraque.eldesaviodominguerojerez.PantallaNegociosActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ObtenerDatos extends AsyncTask<Void, Void, List<Negocios>> {

    private static final String URL_NEGOCIOS = "https://anmoraque.github.io/Data/datos.json";
    private final Context actividadLlamante;

    public ObtenerDatos(Context context) {
        this.actividadLlamante = context;
    }

    @Override
    protected List<Negocios> doInBackground(Void... voids) {
        List<Negocios> listaNegocios = new ArrayList<>();
        HttpURLConnection connection = null;
        InputStreamReader reader = null;

        try {
            URL url = new URL(URL_NEGOCIOS);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new InputStreamReader(connection.getInputStream());
                Type listType = new TypeToken<ArrayList<Negocios>>() {}.getType();
                listaNegocios = new Gson().fromJson(reader, listType);
                Log.d("ObtenerDatos", "Datos obtenidos: " + listaNegocios.size() + " negocios");
            } else {
                Log.e("ObtenerDatos", "Error HTTP: " + connection.getResponseCode());
            }

        } catch (Exception e) {
            Log.e("ObtenerDatos", "Error al obtener datos", e);
        } finally {
            if (reader != null) {
                try { reader.close(); } catch (IOException ignored) {}
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return listaNegocios;
    }

    @Override
    protected void onPostExecute(@Nullable List<Negocios> resultado) {
        Log.d("ObtenerDatos", "onPostExecute: comunicación terminada");
        if (actividadLlamante instanceof PantallaNegociosActivity) {
            ((PantallaNegociosActivity) actividadLlamante).mostrarResultados(resultado);
        } else if (actividadLlamante instanceof MapsActivity) {
            ((MapsActivity) actividadLlamante).mostrarResultados(resultado);
        }
    }
}
