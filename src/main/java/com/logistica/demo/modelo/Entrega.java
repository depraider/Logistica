
package com.logistica.demo.modelo;

import java.time.LocalDate;

public class Entrega {

    private int idEntrega;
    private String codigoPaquete;
    private String dniConductor;
    private String matriculaVehiculo;
    private LocalDate fechaEntrega;
    private String estado;

    public Entrega(int idEntrega, String codigoPaquete, String dniConductor, String matriculaVehiculo,
            LocalDate fechaEntrega, String estado) {
        this.idEntrega = idEntrega;
        this.codigoPaquete = codigoPaquete;
        this.dniConductor = dniConductor;
        this.matriculaVehiculo = matriculaVehiculo;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    public Entrega(String codigoPaquete, String dniConductor, String matriculaVehiculo, LocalDate fechaEntrega,
            String estado) {
        this.codigoPaquete = codigoPaquete;
        this.dniConductor = dniConductor;
        this.matriculaVehiculo = matriculaVehiculo;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    public String getDniConductor() {
        return dniConductor;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public void setDniConductor(String dniConductor) {
        this.dniConductor = dniConductor;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Entrega #" + idEntrega + " - " + codigoPaquete + " (" + estado + ")";
    }
}
