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
    private String coordenadas;

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
                ", coordenadas='" + coordenadas + '\'' +
                '}';
    }

    public Negocios(int id, int distrito, String foto, String nombre, String informacion, String horario, String direccion, String enlace_maps, String coordenadas) {
        //this aparece en el constructor
        //this representa al nuevo Negocio que se está creando en este momento
        this.id = id;
        this.distro = distrito;
        this.foto = foto;
        this.nombre = nombre;
        this.informacion = informacion;
        this.direccion = direccion;
        this.horario = horario;
        this.enlace_maps = enlace_maps;
        this.coordenadas = coordenadas;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistro() {
        return this.distro;
    }

    public void setDistro(int distro) {
        this.distro = distro;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() { return this.nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion() {
        return this.informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getDireccion() { return this.direccion; }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() { return this.horario; }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEnlace_maps() { return this.enlace_maps; }

    public void setEnlace_maps(String enlace_maps) {
        this.enlace_maps = enlace_maps;
    }

    public String getCoordenadas() { return this.coordenadas; }

    public void setCoordenadas(String enlace_maps) {
        this.coordenadas = coordenadas;
    }
}
