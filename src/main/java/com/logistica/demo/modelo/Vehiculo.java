package com.logistica.demo.modelo;

public class Vehiculo {

    private String matricula;
    private String modelo;
    private double potencia;
    private String tipo; // coche o moto

    public Vehiculo(String matricula, String modelo, double potencia, String tipo) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.potencia = potencia;
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPotencia() {
        return potencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return matricula + " (" + modelo + ", " + tipo + ")";
    }
}
