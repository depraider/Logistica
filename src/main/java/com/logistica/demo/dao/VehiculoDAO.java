package com.logistica.demo.dao;

import com.logistica.demo.modelo.Vehiculo;
import com.logistica.demo.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class VehiculoDAO {

    @PostMapping("/insertarVehiculo")
    public void insertar(@RequestBody Vehiculo v) {
        String sql = "INSERT INTO vehiculo (matricula, modelo, potencia, tipo) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getMatricula());
            ps.setString(2, v.getModelo());
            ps.setDouble(3, v.getPotencia());
            ps.setString(4, v.getTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/listarVehiculos")
    public List<Vehiculo> listar() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        try (Connection con = ConexionDB.conectar();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("modelo"),
                        rs.getDouble("potencia"),
                        rs.getString("tipo")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @PutMapping("/actualizarVehiculo/{matricula}")
    public void actualizarVehiculo(@PathVariable String matricula, @RequestBody Vehiculo v) {
        String sql = "UPDATE vehiculo SET modelo=?, potencia=?, tipo=? WHERE matricula=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getModelo());
            ps.setDouble(2, v.getPotencia());
            ps.setString(3, v.getTipo());
            ps.setString(4, matricula);
            ps.executeUpdate();
            System.out.println("Vehículo actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar vehículo: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminarVehiculo/{matricula}")
    public void eliminar(@PathVariable String matricula) {

        String sql = "DELETE FROM vehiculo WHERE matricula=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
