package com.mycompany.logistica.dao;

import com.mycompany.logistica.modelo.Municipio;
import com.mycompany.logistica.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {
    public void insertar(Municipio m) {
        String sql = "INSERT INTO municipio (nombre, provincia) VALUES (?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getProvincia());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Municipio> listar() {
        List<Municipio> lista = new ArrayList<>();
        String sql = "SELECT * FROM municipio";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Municipio(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("provincia")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Municipio m) {
        String sql = "UPDATE municipio SET nombre=?, provincia=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getProvincia());
            ps.setInt(3, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM municipio WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
