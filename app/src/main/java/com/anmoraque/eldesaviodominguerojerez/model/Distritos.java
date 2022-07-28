package com.anmoraque.eldesaviodominguerojerez.model;

public class Distritos {

    private int id;
    private String nombre;
    private String informacion;

    public Distritos(int id, String nombre, String informacion) {

        this.id = id;
        this.nombre = nombre;
        this.informacion = informacion;
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
