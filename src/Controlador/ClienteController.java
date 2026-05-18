/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ezayr
 */
import DAO.ClienteDAO;
import Modelo.Cliente;
import java.util.List;

public class ClienteController {

    private final ClienteDAO dao;

    public ClienteController() {
        dao = new ClienteDAO();
    }
    
    public boolean registrarCliente(
            String nombre,
            String apellido,
            String telefono,
            String email,
            String direccion
    ) {

        if(nombre.isEmpty() || apellido.isEmpty()) {

            System.out.println(
                    "Nombre y apellido son obligatorios"
            );

            return false;
        }

        if(!email.contains("@")) {

            System.out.println(
                    "Email invalido"
            );

            return false;
        }

        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);

        return dao.insertar(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return dao.listar();
    }

    public boolean modificarCliente(
            int id,
            String nombre,
            String apellido,
            String telefono,
            String email,
            String direccion
    ) {

        Cliente cliente = new Cliente();

        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);

        return dao.modificar(cliente);
    }

    public boolean eliminarCliente(int id) {
        return dao.eliminar(id);
    }
}