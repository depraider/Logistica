package com.mycompany.logistica.vista;

import com.mycompany.logistica.dao.ConductorDAO;
import com.mycompany.logistica.modelo.Conductor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaConductores extends JFrame {

    private JTextField txtDni, txtNombre, txtTelefono, txtDireccion, txtSalario, txtMunicipio;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private ConductorDAO dao;

    public VentanaConductores() {
        super("Gestión de Conductores");
        dao = new ConductorDAO();
        configurarVentana();
        inicializarComponentes();
        cargarTabla();
    }

    private void configurarVentana() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 5, 5));

        panelForm.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        panelForm.add(txtDni);

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);

        panelForm.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelForm.add(txtDireccion);

        panelForm.add(new JLabel("Salario:"));
        txtSalario = new JTextField();
        panelForm.add(txtSalario);

        panelForm.add(new JLabel("Municipio:"));
        txtMunicipio = new JTextField();
        panelForm.add(txtMunicipio);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        modeloTabla = new DefaultTableModel(
                new String[]{"DNI", "Nombre", "Teléfono", "Dirección", "Salario", "Municipio"}, 0
        );
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // --- ACCIONES DE BOTONES ---
        btnAgregar.addActionListener(e -> agregarConductor());
        btnActualizar.addActionListener(e -> actualizarConductor());
        btnEliminar.addActionListener(e -> eliminarConductor());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                txtDni.setText(modeloTabla.getValueAt(fila, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtTelefono.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtDireccion.setText(modeloTabla.getValueAt(fila, 3).toString());
                txtSalario.setText(modeloTabla.getValueAt(fila, 4).toString());
                txtMunicipio.setText(modeloTabla.getValueAt(fila, 5).toString());
                txtDni.setEnabled(false); // No modificar la clave
            }
        });
    }

    private void agregarConductor() {
        try {
            String dni = txtDni.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String direccion = txtDireccion.getText();
            double salario = Double.parseDouble(txtSalario.getText());
            String municipio = txtMunicipio.getText();

            if (dni.isBlank() || nombre.isBlank()) {
                JOptionPane.showMessageDialog(this, "DNI y nombre son obligatorios.");
                return;
            }

            dao.insertar(new Conductor(dni, nombre, telefono, direccion, salario, municipio));
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void actualizarConductor() {
        try {
            String dni = txtDni.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String direccion = txtDireccion.getText();
            double salario = Double.parseDouble(txtSalario.getText());
            String municipio = txtMunicipio.getText();

            dao.actualizar(new Conductor(dni, nombre, telefono, direccion, salario, municipio));
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarConductor() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un conductor de la tabla.");
            return;
        }

        String dni = modeloTabla.getValueAt(fila, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar conductor con DNI " + dni + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.eliminar(dni);
            cargarTabla();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtSalario.setText("");
        txtMunicipio.setText("");
        txtDni.setEnabled(true);
        tabla.clearSelection();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Conductor> lista = dao.listar();
        for (Conductor c : lista) {
            modeloTabla.addRow(new Object[]{
                    c.getDni(),
                    c.getNombre(),
                    c.getTelefono(),
                    c.getDireccion(),
                    c.getSalario(),
                    c.getMunicipioResidencia()
            });
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new VentanaConductores().setVisible(true));
//    }
}
