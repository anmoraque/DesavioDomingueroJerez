package com.anmoraque.eldesaviodominguerojerez.model;

import androidx.annotation.NonNull;

public class Distritos {

    private final int id;
    @NonNull
    private String nombre;
    @NonNull
    private String informacion;

    public Distritos(int id, @NonNull String nombre, @NonNull String informacion) {
        this.id = id;
        this.nombre = nombre;
        this.informacion = informacion;
    }

    // Getters
    public int getId() {
        return id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    @NonNull
    public String getInformacion() {
        return informacion;
    }

    // Setters para campos mutables
    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public void setInformacion(@NonNull String informacion) {
        this.informacion = informacion;
    }

    @NonNull
    @Override
    public String toString() {
        return "Distritos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", informacion='" + informacion + '\'' +
                '}';
    }
}
