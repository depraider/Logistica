package com.mycompany.logistica.modelo;

public class Municipio {

    private int id;
    private String nombre;
    private String provincia;

    public Municipio() {
    }

    public Municipio(int id, String nombre, String provincia) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public Municipio(String nombre, String provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return nombre + " (" + provincia + ")";
    }
}
