package com.anmoraque.eldesaviodominguerojerez.model;

public class Negocios {

    private int id;
    private int distro;
    private String foto;
    private String nombre;
    private String informacion;
    private String horario;
    private String direccion;
    private String enlace_maps;
    private float latitude;
    private float longitude;

    public Negocios(int id, int distro, String foto, String nombre, String informacion, String horario, String direccion, String enlace_maps, float latitude, float longitude) {
        this.id = id;
        this.distro = distro;
        this.foto = foto;
        this.nombre = nombre;
        this.informacion = informacion;
        this.horario = horario;
        this.direccion = direccion;
        this.enlace_maps = enlace_maps;
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
                ", enlace_maps='" + enlace_maps + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistro() {
        return distro;
    }

    public void setDistro(int distro) {
        this.distro = distro;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEnlace_maps() {
        return enlace_maps;
    }

    public void setEnlace_maps(String enlace_maps) {
        this.enlace_maps = enlace_maps;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
