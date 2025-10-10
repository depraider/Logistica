package com.mycompany.logistica.modelo;
 
public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private int potencia;
    private String dniConductor;

    public Vehiculo(String matricula, String marca, String modelo, int potencia, String dniConductor) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.dniConductor = dniConductor;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getPotencia() {
        return potencia;
    }

    public String getDniConductor() {
        return dniConductor;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setDniConductor(String dniConductor) {
        this.dniConductor = dniConductor;
    }

    @Override
    public String toString() {
        return matricula + " (" + marca + " " + modelo + ")";
    }
}
