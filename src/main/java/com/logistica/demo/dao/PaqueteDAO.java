package com.logistica.demo.dao;

import com.logistica.demo.modelo.Paquete;
import com.logistica.demo.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PaqueteDAO {

    @PostMapping("/insertarPaquete")
    public void insertar(@RequestBody Paquete p) {
        String sql = "INSERT INTO paquete (codigoPaquete, descripcion, destinatario, direccionEntrega, municipio) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getCodigoPaquete());
            ps.setString(2, p.getDescripcion());
            ps.setString(3, p.getDestinatario());
            ps.setString(4, p.getDireccionEntrega());
            ps.setString(5, p.getMunicipio());
            ps.executeUpdate();
            System.out.println("Paquete insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar paquete: " + e.getMessage());
        }
    }

    @GetMapping("/listarPaquetes")
    public List<Paquete> listar() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquete";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Paquete(
                        rs.getString("codigoPaquete"),
                        rs.getString("descripcion"),
                        rs.getString("destinatario"),
                        rs.getString("direccionEntrega"),
                        rs.getString("municipio")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar paquetes: " + e.getMessage());
        }
        return lista;
    }

    @PutMapping("/actualizarPaquete/{codigoPaquete}")
    public void actualizar(@RequestBody Paquete p, @PathVariable String codigoPaquete) {
        String sql = "UPDATE paquete SET descripcion=?, destinatario=?, direccionEntrega=?, municipio=? WHERE codigoPaquete=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionEntrega());
            ps.setString(4, p.getMunicipio());
            ps.setString(5, codigoPaquete);
            ps.executeUpdate();
            System.out.println("Paquete actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar paquete: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminarPaquete/{codigoPaquete}")
    public void eliminar(@PathVariable String codigoPaquete) {
        String sql = "DELETE FROM paquete WHERE codigoPaquete=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoPaquete);
            ps.executeUpdate();
            System.out.println("Paquete eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar paquete: " + e.getMessage());
        }
    }
}
