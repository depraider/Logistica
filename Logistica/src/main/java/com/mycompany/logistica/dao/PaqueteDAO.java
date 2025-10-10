package com.mycompany.logistica.dao;

import com.mycompany.logistica.modelo.Paquete;
import com.mycompany.logistica.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaqueteDAO {

    public void insertar(Paquete p) {
        String sql = "INSERT INTO paquete (codigo, descripcion, destinatario, direccionEntrega, idMunicipioDestino) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getDescripcion());
            ps.setString(3, p.getDestinatario());
            ps.setString(4, p.getDireccionEntrega());
            ps.setInt(5, p.getIdMunicipioDestino());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paquete> listar() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquete";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Paquete(
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getString("destinatario"),
                        rs.getString("direccionEntrega"),
                        rs.getInt("idMunicipioDestino")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Paquete p) {
        String sql = "UPDATE paquete SET descripcion=?, destinatario=?, direccionEntrega=?, idMunicipioDestino=? WHERE codigo=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionEntrega());
            ps.setInt(4, p.getIdMunicipioDestino());
            ps.setString(5, p.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(String codigo) {
        String sql = "DELETE FROM paquete WHERE codigo=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
