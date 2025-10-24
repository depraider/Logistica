package com.logistica.demo.modelo;

public class Municipio {
    private String codigoMunicipio;
    private String nombre;
    private String provincia;

    public Municipio(String codigoMunicipio, String nombre, String provincia) {
        this.codigoMunicipio = codigoMunicipio;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return codigoMunicipio + " - " + nombre + " (" + provincia + ")";
    }
}
