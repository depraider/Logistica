package com.logistica.demo.modelo;

public class Paquete {

    private String codigoPaquete;
    private String descripcion;
    private String destinatario;
    private String direccionEntrega;
    private String municipio; 

    public Paquete(String codigoPaquete, String descripcion, String destinatario, String direccionEntrega,
            String municipio) {
        this.codigoPaquete = codigoPaquete;
        this.descripcion = descripcion;
        this.destinatario = destinatario;
        this.direccionEntrega = direccionEntrega;
        this.municipio = municipio;
    }

    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return codigoPaquete + " - " + destinatario;
    }
}
