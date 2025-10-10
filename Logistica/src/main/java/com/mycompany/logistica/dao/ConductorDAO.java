package com.mycompany.logistica.dao;

import com.mycompany.logistica.modelo.Conductor;
import com.mycompany.logistica.modelo.Vehiculos;

import com.mycompany.logistica.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO {

    //Gestion de conductores 
    public void insertar(Conductor c) {
        String sql = "INSERT INTO conductor VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            ps.setDouble(5, c.getSalario());
            ps.setString(6, c.getMunicipioResidencia());

            ps.executeUpdate();
            System.out.println("✅ Conductor insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar: " + e.getMessage());
        }
    }

    public List<Conductor> listar() {
        List<Conductor> lista = new ArrayList<>();
        String sql = "SELECT * FROM conductor";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Conductor c = new Conductor(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getDouble("salario"),
                        rs.getString("municipioResidencia")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public void actualizar(Conductor c) {
        String sql = "UPDATE conductor SET nombre=?, telefono=?, direccion=?, salario=?, municipioResidencia=? WHERE dni=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setDouble(4, c.getSalario());
            ps.setString(5, c.getMunicipioResidencia());
            ps.setString(6, c.getDni());

            ps.executeUpdate();
            System.out.println("✅ Conductor actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminar(String dni) {
        String sql = "DELETE FROM conductor WHERE dni=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.executeUpdate();
            System.out.println("✅ Conductor eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar: " + e.getMessage());
        }
    }
    
   //Fin gestion de conductores 

    
    // Gestion de vehiculos 
    
    
    public void insertarVehiculos(Vehiculos v) {
        String sql = "INSERT INTO vehiculo VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getMatricula());
            ps.setString(2, v.getModelo());
            ps.setString(3, v.getPotencia());
            ps.setString(3, v.getTipo());

          

            ps.executeUpdate();
            System.out.println("✅ Vehiculo insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar: " + e.getMessage());
        }
    }
    
     public List<Vehiculos> listarVehiculos() {
        List<Vehiculos> listaVehiculo = new ArrayList<>();
        String sql = "SELECT * FROM conductor";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Vehiculos v = new Vehiculos(
                        rs.getString("matricula"),
                        rs.getString("modelo"),
                        rs.getString("potencia"),
                        rs.getString("tipo")

                       
                );
                listaVehiculo.add(v);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar: " + e.getMessage());
        }
        return listaVehiculo;
    }
     
    
    // Fin de gestion de vehiculos 
    
}
