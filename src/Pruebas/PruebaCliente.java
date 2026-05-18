/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas; // ✅ Declaramos que pertenece al paquete de pruebas

// IMPORTACIONES CRÍTICAS: Le avisamos a Java dónde buscar el controlador y el modelo


/*import Controlador.ClienteController;
import Modelo.Cliente;
import java.util.List;
import java.util.Scanner;

public class PruebaCliente {

    public static void main(String[] args) {
        ClienteController controller = new ClienteController();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("====== SISTEMA DE PRUEBA CRUD (PAQUETE PRUEBAS) ======");

        do {
            System.out.println("\n--- MENÚ DE OPCIONES ---");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Modificar un cliente");
            System.out.println("4. Eliminar un cliente");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea del buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n-- REGISTRAR CLIENTE --");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email (debe incluir @): ");
                    String email = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();

                    boolean insertado = controller.registrarCliente(nombre, apellido, telefono, email, direccion);
                    if (insertado) {
                        System.out.println("✅ ¡Cliente guardado con éxito en Postgres!");
                    } else {
                        System.out.println("❌ No se pudo registrar el cliente. Verifica las validaciones.");
                    }
                    break;

                case 2:
                    System.out.println("\n-- LISTA DE CLIENTES EN LA BASE DE DATOS --");
                    List<Cliente> clientes = controller.obtenerClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("ℹ️ La tabla está vacía o no hay registros.");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println("ID: " + c.getId() + " | " + c.toString() + " | Tel: " + c.getTelefono() + " | Email: " + c.getEmail());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n-- MODIFICAR CLIENTE --");
                    System.out.print("Ingresa el ID del cliente a modificar: ");
                    int idMod = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Nuevo Nombre: ");
                    String nNombre = scanner.nextLine();
                    System.out.print("Nuevo Apellido: ");
                    String nApellido = scanner.nextLine();
                    System.out.print("Nuevo Teléfono: ");
                    String nTelefono = scanner.nextLine();
                    System.out.print("Nuevo Email: ");
                    String nEmail = scanner.nextLine();
                    System.out.print("Nueva Dirección: ");
                    String nDireccion = scanner.nextLine();

                    boolean modificado = controller.modificarCliente(idMod, nNombre, nApellido, nTelefono, nEmail, nDireccion);
                    if (modificado) {
                        System.out.println("✅ ¡Cliente actualizado correctamente!");
                    } else {
                        System.out.println("❌ Falló la actualización.");
                    }
                    break;

                case 4:
                    System.out.println("\n-- ELIMINAR CLIENTE --");
                    System.out.print("Ingresa el ID del cliente que deseas eliminar: ");
                    int idElim = scanner.nextInt();

                    boolean eliminado = controller.eliminarCliente(idElim);
                    if (eliminado) {
                        System.out.println("🗑️ ¡Cliente eliminado de la base de datos!");
                    } else {
                        System.out.println("❌ No se pudo eliminar el cliente.");
                    }
                    break;

                case 5:
                    System.out.println("👋 Saliendo del entorno de pruebas. Estructura OK.");
                    break;

                default:
                    System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}*/