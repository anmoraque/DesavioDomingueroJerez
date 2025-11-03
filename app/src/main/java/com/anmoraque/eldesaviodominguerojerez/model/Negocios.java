package com.anmoraque.eldesaviodominguerojerez.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Negocios {

    private final int id;       // Inmutable
    private int distro;

    @NonNull
    private String foto;

    @NonNull
    private String nombre;

    @NonNull
    private String informacion;

    @NonNull
    private String horario;

    @NonNull
    private String direccion;

    @SerializedName("enlace_maps")
    @NonNull
    private String enlaceMaps;

    private double latitude;
    private double longitude;

    public Negocios(int id, int distro, @NonNull String foto, @NonNull String nombre,
                    @NonNull String informacion, @NonNull String horario,
                    @NonNull String direccion, @NonNull String enlaceMaps,
                    double latitude, double longitude) {
        this.id = id;
        this.distro = distro;
        this.foto = foto;
        this.nombre = nombre;
        this.informacion = informacion;
        this.horario = horario;
        this.direccion = direccion;
        this.enlaceMaps = enlaceMaps;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Negocios{" +
                "id=" + id +
                ", distro=" + distro +
                ", foto='" + foto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", informacion='" + informacion + '\'' +
                ", horario='" + horario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", enlaceMaps='" + enlaceMaps + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getDistro() {
        return distro;
    }

    public void setDistro(int distro) {
        this.distro = distro;
    }

    @NonNull
    public String getFoto() {
        return foto;
    }

    public void setFoto(@NonNull String foto) {
        this.foto = foto;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(@NonNull String informacion) {
        this.informacion = informacion;
    }

    @NonNull
    public String getHorario() {
        return horario;
    }

    public void setHorario(@NonNull String horario) {
        this.horario = horario;
    }

    @NonNull
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NonNull String direccion) {
        this.direccion = direccion;
    }

    @NonNull
    public String getEnlaceMaps() {
        return enlaceMaps;
    }

    public void setEnlaceMaps(@NonNull String enlaceMaps) {
        this.enlaceMaps = enlaceMaps;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
