package com.logistica.demo.dao;

import com.logistica.demo.modelo.Municipio;
import com.logistica.demo.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MunicipioDAO {

    @PostMapping("/insertarMunicipio")
    public void insertar(@RequestBody Municipio m) {
        String sql = "INSERT INTO municipio (codigoMunicipio, nombre, provincia) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getCodigoMunicipio());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getProvincia());
            ps.executeUpdate();

            System.out.println("Municipio insertado: " + m);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar municipio: " + e.toString());
        }
    }

    @GetMapping("/listarMunicipios")
    public List<Municipio> listar() {
        List<Municipio> lista = new ArrayList<>();
        String sql = "SELECT * FROM municipio";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Municipio(
                        rs.getString("codigoMunicipio"),
                        rs.getString("nombre"),
                        rs.getString("provincia")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @PutMapping("/actualizarMunicipio/{codigo}")
    public void actualizar(@PathVariable String codigo, @RequestBody Municipio m) {
        String sql = "UPDATE municipio SET nombre=?, provincia=? WHERE codigoMunicipio=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getProvincia());
            ps.setString(3, codigo);
            int filas = ps.executeUpdate();
            System.out.println("Municipio actualizado. Filas afectadas: " + filas);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar municipio: " + e.toString());
        }
    }

    @DeleteMapping("/eliminarMunicipio/{codigo}")
    public void eliminar(@PathVariable String codigo) {
        String sql = "DELETE FROM municipio WHERE codigoMunicipio=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);
            int filas = ps.executeUpdate();
            System.out.println("Municipio eliminado. Filas afectadas: " + filas);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar municipio: " + e.toString());
        }
    }
}
