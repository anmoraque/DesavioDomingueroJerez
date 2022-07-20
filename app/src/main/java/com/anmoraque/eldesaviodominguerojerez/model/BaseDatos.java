package com.anmoraque.eldesaviodominguerojerez.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {

    private static final String SQL_CREAR_TABLA_DISTRITOS = "CREATE TABLE DISTRITOS (id INTEGER PRIMARY KEY, nombre TEXT)";
    private static final String SQL_CREAR_TABLA_NEGOCIOS = "CREATE TABLE NEGOCIOS (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, iddistritos INTEGER, FOREIGN KEY (iddistritos) REFERENCES DISTRITOS (id))";


    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //incluir las instrucciones para crear las tablas
        //IMPORTANTE EL ORDEN
        sqLiteDatabase.execSQL(SQL_CREAR_TABLA_DISTRITOS);
        sqLiteDatabase.execSQL(SQL_CREAR_TABLA_NEGOCIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //1 leer los datos de las tablas
        //2 crear las nuevas tablas
        //3 escribir los datos del paso 1
    }

    //insertar Distritos
    public void insertarDistritos (Distritos distritos)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); //INSERT INTO DISTRITOS (id, nombre, etc)
        //importante: AL INTRODUCIR EL VALOR DE UNA COLUMNA TIPO TEXT, (STRING), TIENE QUE IR ENTRE COMILLAS SIMPLES
        String instruccion_sql_inertar_distritos = "INSERT INTO DISTRITOS (id, nombre, informacion) VALUES ("+distritos.getId()+", '"+distritos.getNombre()+"', '"+distritos.getInformacion()+"')";
        Log.d("ETIQUETA_LOG", "INSERTANDO DISTRITOS = " + instruccion_sql_inertar_distritos);
        sqLiteDatabase.execSQL(instruccion_sql_inertar_distritos);
        cerrarBaseDatos(sqLiteDatabase);
    }

    //insertar Negocios
    public void insertarNegocios (Negocios negocios)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String instruccion_sql_insertar_negocios = "INSERT INTO NEGOCIOS (id, distro, foto, nombre, informacion, horario, direccion, enlace_maps) VALUES ("+negocios.getId()+", "+negocios.getDistro()+", "+negocios.getFoto()+", '"+negocios.getNombre()+"', '"+negocios.getInformacion()+"', '"+negocios.getHorario()+"', '"+negocios.getDireccion()+"', '"+negocios.getEnlace_maps()+"')";
        Log.d("ETIQUETA_LOG", "INSERTANDO NEGOCIOS = " + instruccion_sql_insertar_negocios);
        sqLiteDatabase.execSQL(instruccion_sql_insertar_negocios);
        cerrarBaseDatos(sqLiteDatabase);
    }

    //cerrarBaseDatos
    private void cerrarBaseDatos (SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.close();
    }

    //obtener Negocios del Distrito
    public List<Negocios> obtenerNegociosDistrito (Distritos distrito)
    {
        List<Negocios> lista_negocios = null;
        SQLiteDatabase sqLiteDatabase = null;
        String instruccion_consulta = "SELECT modelo  FROM NEGOCIO WHERE iddistritos = " + distrito.getId();
        Cursor cursor = null;//objeto que me permite recorrer los resultados de una consulta
        int distro_aux = 0;
        Negocios negocio_aux = null;

        sqLiteDatabase = this.getReadableDatabase();//obtengo la Base de datos en modo lectura
        cursor = sqLiteDatabase.rawQuery(instruccion_consulta, null);
        if (cursor !=null && cursor.getCount()>0) //si la consulta ha recuperado algún registro
        {
            Log.d("ETIQUETA_LOG", "La consulta ha recuperado datos");
            cursor.moveToFirst();
            lista_negocios = new ArrayList<>(cursor.getCount());
            //tengo que ir leyendo los negocios
            //bucle de 1 a N
            do {
                distro_aux = cursor.getInt(1);//1 es la segunda columna de la consulta -distro en nuestro caso-
                negocio_aux = new Negocios(distro_aux);
                lista_negocios.add(negocio_aux);

            }while (cursor.moveToNext());

            cursor.close();
        }
        cerrarBaseDatos(sqLiteDatabase);

        return lista_negocios;
    }

}
