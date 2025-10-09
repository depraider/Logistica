package com.mycompany.logistica;

import com.mycompany.logistica.dao.ConductorDAO;
import com.mycompany.logistica.modelo.Conductor;

import java.util.List;
import java.util.Scanner;

public class Logistica {

    public static void main(String[] args) {
        ConductorDAO dao = new ConductorDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- CRUD CONDUCTORES ---");
            System.out.println("1. Insertar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = sc.nextLine();
                    System.out.print("Dirección: ");
                    String dir = sc.nextLine();
                    System.out.print("Salario: ");
                    double sal = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Municipio: ");
                    String muni = sc.nextLine();

                    dao.insertar(new Conductor(dni, nombre, tel, dir, sal, muni));
                }
                case 2 -> {
                    List<Conductor> lista = dao.listar();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("DNI a actualizar: ");
                    String dni = sc.nextLine();

                    // Recuperamos el conductor actual
                    List<Conductor> lista = dao.listar();
                    Conductor actual = lista.stream()
                            .filter(c -> c.getDni().equalsIgnoreCase(dni))
                            .findFirst()
                            .orElse(null);

                    if (actual == null) {
                        System.out.println("❌ No se encontró ningún conductor con ese DNI.");
                        break;
                    }

                    System.out.println("Deja en blanco los campos que no quieras modificar.");

                    System.out.print("Nuevo nombre (" + actual.getNombre() + "): ");
                    String nombre = sc.nextLine();
                    if (nombre.isBlank()) {
                        nombre = actual.getNombre();
                    }

                    System.out.print("Nuevo teléfono (" + actual.getTelefono() + "): ");
                    String tel = sc.nextLine();
                    if (tel.isBlank()) {
                        tel = actual.getTelefono();
                    }

                    System.out.print("Nueva dirección (" + actual.getDireccion() + "): ");
                    String dir = sc.nextLine();
                    if (dir.isBlank()) {
                        dir = actual.getDireccion();
                    }

                    System.out.print("Nuevo salario (" + actual.getSalario() + "): ");
                    String salInput = sc.nextLine();
                    double sal;
                    if (salInput.isBlank()) {
                        sal = actual.getSalario();
                    } else {
                        sal = Double.parseDouble(salInput);
                    }

                    System.out.print("Nuevo municipio (" + actual.getMunicipioResidencia() + "): ");
                    String muni = sc.nextLine();
                    if (muni.isBlank()) {
                        muni = actual.getMunicipioResidencia();
                    }

                    dao.actualizar(new Conductor(dni, nombre, tel, dir, sal, muni));
                }
                case 4 -> {
                    System.out.print("DNI a eliminar: ");
                    String dni = sc.nextLine();
                    dao.eliminar(dni);
                }
            }
        } while (opcion != 0);

        sc.close();
    }
}
