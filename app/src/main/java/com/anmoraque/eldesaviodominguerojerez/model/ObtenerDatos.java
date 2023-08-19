package com.anmoraque.eldesaviodominguerojerez.model;


//AsyncTask
//En el interior de esta clase, se va a producir la comunicación
//HTTP con el servidor

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Type;

import com.anmoraque.eldesaviodominguerojerez.MapsActivity;
import com.google.gson.reflect.TypeToken;
import com.anmoraque.eldesaviodominguerojerez.PantallaNegociosActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//VOID - paramétro de entrada
//VOID - no voy a contabilizar el avance no uso ningún número
//List<Negocios>> tipo de dato devuelto
public class ObtenerDatos extends AsyncTask<Void, Void, List<Negocios>> {
    //Web del servidor de la lista en este caso en Github
    private static final String URL_NEGOCIOS = "https://anmoraque.github.io/Data/datos.json";
    //PantallaActivity donde necesito obtenerDatos
    private Context actividad_llamante;
    //Metodo para obtener los datos
    public ObtenerDatos(Context context)
    {
        this.actividad_llamante = context;
    }

    //En este método, se lleva a cabo la comunicación HTTP
    @Override
    protected List<Negocios> doInBackground(Void... vacio) {
        //Lista vacia
        List<Negocios> lista_negocios = null;
        //Aquí vamos a poner la ruta
        URL url = null;
        //Esta clase representa el mensajes / la comunicación http
        HttpURLConnection httpURLConnection = null;
        //Este objeto me ayuda a (des)serializar JSON a JAVA
        Gson gson = null;
        //Leo el cuerpo del mensaje
        InputStreamReader inputStreamReader = null;
        try {
            //El proceso de comunicación es susceptible de lanzar una execepción
            //por eso, vamos a agruparlo en un try-catch
            url = new URL(URL_NEGOCIOS);
            Log.d("ETIQUETA_LOG", "URL búsqueda url = " + url);
            //Porque sé que el tipo de conexión es HTTP
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //Consultar, obtener info del servidor, no envío nada (el cuerpo de la petición ,va vacío)
            httpURLConnection.setRequestMethod("GET");
            //HTTP_OK es 200 conexion bien
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                Log.d("ETIQUETA_LOG", "La conexión ha ido bien! - Respuesta OK");
                //Accedo al cuerpo de la respuesta httpURLConnection.getInputStream()
                //Uso la clase InputStream para leer ese cuerpo
                Log.d("ETIQUETA_LOG", "Obtenidos " +httpURLConnection.getContentLength() + " bytes" );
                Log.d("ETIQUETA_LOG", "TIPO MIME " +httpURLConnection.getContentType() );
                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                //Para pasar el cuerpo de JSON a la lista de negocios
                //Obtenemos el JSON global
                gson = new Gson();
                //Pasar el tipo lista a Negocios
                Type listType = new TypeToken<ArrayList<Negocios>>(){}.getType();
                lista_negocios = gson.fromJson(inputStreamReader, listType);

            }
        //Si va mal
        } catch (Exception e) {
            Log.e("ETIQUETA_LOG", "Algo ha salido mal", e);
        //Vaya bien o mal finalizo recursos
        } finally {
            //Liberar recursos
            try {
                inputStreamReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();

        }

        return lista_negocios;
    }

    //Este otro método, es invocado, al finalizar la conexión
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onPostExecute(List<Negocios> resultadoListaNegocios)
    {
        Log.d("ETIQUETA_LOG", "en onPostExecute ... comunicación terminada");
        //¿Cómo le aviso a la Activity que he acabado?
        //Segun necesite PantallaNegociosActivity o MapsActivity llevo el resultado mediante if
        if (this.actividad_llamante instanceof PantallaNegociosActivity)
        {
            PantallaNegociosActivity actividad_negocios = ((PantallaNegociosActivity) this.actividad_llamante);
            actividad_negocios.mostrarResultados(resultadoListaNegocios);

        } if (this.actividad_llamante instanceof MapsActivity)
            {
                MapsActivity actividad_negocios_mapa = ((MapsActivity) this.actividad_llamante);
                actividad_negocios_mapa.mostrarResultados(resultadoListaNegocios);
            }

    }
}
