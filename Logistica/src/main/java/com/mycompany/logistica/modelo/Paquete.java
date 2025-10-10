package com.mycompany.logistica.modelo;
 
public class Paquete {

    private String codigo;
    private String descripcion;
    private String destinatario;
    private String direccionEntrega;
    private int idMunicipioDestino;
 
    public Paquete(String codigo, String descripcion, String destinatario, String direccionEntrega, int idMunicipioDestino) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.destinatario = destinatario;
        this.direccionEntrega = direccionEntrega;
        this.idMunicipioDestino = idMunicipioDestino;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public int getIdMunicipioDestino() {
        return idMunicipioDestino;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setIdMunicipioDestino(int idMunicipioDestino) {
        this.idMunicipioDestino = idMunicipioDestino;
    }

    @Override
    public String toString() {
        return codigo + " - " + destinatario;
    }
}
