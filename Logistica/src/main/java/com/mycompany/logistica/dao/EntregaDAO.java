package com.mycompany.logistica.dao;

import com.mycompany.logistica.modelo.Entrega;
import com.mycompany.logistica.util.ConexionDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

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

    public void eliminar(int idEntrega) {
        String sql = "DELETE FROM entrega WHERE idEntrega=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEntrega);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
