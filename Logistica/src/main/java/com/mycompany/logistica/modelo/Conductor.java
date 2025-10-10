
package com.mycompany.logistica.modelo;

public class Conductor {
    private String dni;
    private String nombre;
    private String telefono;
    private String direccion;
    private double salario;
    private String municipioResidencia;

    public Conductor(String dni, String nombre, String telefono, String direccion, double salario, String municipioResidencia) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.salario = salario;
        this.municipioResidencia = municipioResidencia;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public String getMunicipioResidencia() { return municipioResidencia; }
    public void setMunicipioResidencia(String municipioResidencia) { this.municipioResidencia = municipioResidencia; }

    @Override
    public String toString() {
        return dni + " - " + nombre + " (" + municipioResidencia + ")";
    }
}
