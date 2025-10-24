package com.logistica.demo.dao;

import com.logistica.demo.modelo.Conductor;
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
public class ConductorDAO {

    @PostMapping("/insertarConductor")
    public void insertar(@RequestBody Conductor c) {
        String sql = "INSERT INTO conductor VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            ps.setDouble(5, c.getSalario());
            ps.setString(6, c.getMunicipioResidencia());

            ps.executeUpdate();
            System.out.println("Conductor insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    @GetMapping("/listarConductores")
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
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @PutMapping("/actualizarConductor/{dni}")
    public void actualizar(@PathVariable String dni, @RequestBody Conductor c) {
        String sql = "UPDATE conductor SET nombre=?, telefono=?, direccion=?, salario=?, municipioResidencia=? WHERE dni=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setDouble(4, c.getSalario());
            ps.setString(5, c.getMunicipioResidencia());
            ps.setString(6, dni);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Conductor actualizado correctamente: " + dni);
            } else {
                System.out.println("No se encontró conductor con DNI: " + dni);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminarConductor/{dni}")
    public void eliminar(@PathVariable String dni) {
        String sql = "DELETE FROM conductor WHERE dni=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.executeUpdate();
            System.out.println("Conductor eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}
