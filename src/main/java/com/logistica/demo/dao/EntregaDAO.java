package com.logistica.demo.dao;

import com.logistica.demo.modelo.Entrega;
import com.logistica.demo.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntregaDAO {

    @PostMapping("/insertarEntrega")
    public void insertar(Entrega e) {
        String sql = "INSERT INTO entrega (codigoPaquete, dniConductor, matriculaVehiculo, fechaEntrega, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getCodigoPaquete());
            ps.setString(2, e.getDniConductor());
            ps.setString(3, e.getMatriculaVehiculo());
            ps.setDate(4, Date.valueOf(e.getFechaEntrega()));
            ps.setString(5, e.getEstado());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("/listarEntregas")
    public List<Entrega> listar() {
        List<Entrega> lista = new ArrayList<>();
        String sql = "SELECT * FROM entrega";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Entrega(
                        rs.getInt("idEntrega"),
                        rs.getString("codigoPaquete"),
                        rs.getString("dniConductor"),
                        rs.getString("matriculaVehiculo"),
                        rs.getDate("fechaEntrega").toLocalDate(),
                        rs.getString("estado")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Entrega e) {
        String sql = "UPDATE entrega SET codigoPaquete=?, dniConductor=?, matriculaVehiculo=?, fechaEntrega=?, estado=? WHERE idEntrega=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getCodigoPaquete());
            ps.setString(2, e.getDniConductor());
            ps.setString(3, e.getMatriculaVehiculo());
            ps.setDate(4, Date.valueOf(e.getFechaEntrega()));
            ps.setString(5, e.getEstado());
            ps.setInt(6, e.getIdEntrega());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @DeleteMapping("/eliminarEntrega/{dni}")
    public void eliminar(@PathVariable int idEntrega) {
        String sql = "DELETE FROM entrega WHERE idEntrega=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
