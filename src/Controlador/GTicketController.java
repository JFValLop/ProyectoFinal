/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ezayr
 */

import DAO.GestionTicketDAO;
import Modelo.Ticket;
import java.util.List;

/**
 * Controlador de lógica de negocio para Tickets.
 * Solo maneja reglas de negocio y delega al DAO.
 * NO contiene ninguna referencia a JavaFX.
 */
public class GTicketController {

    private final GestionTicketDAO dao = new GestionTicketDAO();

    public boolean registrarTicket(int partidoId, String asiento,
                                   String seccion, String precioStr, String estado) {
        try {
            if (asiento == null || asiento.trim().isEmpty()) {
                System.out.println("El número de asiento no puede estar vacío.");
                return false;
            }

            double precio = Double.parseDouble(precioStr);

            Ticket t = new Ticket();
            t.setPartidoId(partidoId);
            t.setNumeroAsiento(asiento.trim());
            t.setSeccion(seccion);
            t.setPrecio(precio);
            t.setEstado(estado);

            return dao.insertar(t);

        } catch (NumberFormatException e) {
            System.out.println("Precio inválido: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarTicket(int id, int partidoId, String asiento,
                                   String seccion, String precioStr, String estado) {
        try {
            if (asiento == null || asiento.trim().isEmpty()) {
                System.out.println("El número de asiento no puede estar vacío.");
                return false;
            }

            double precio = Double.parseDouble(precioStr);

            Ticket t = new Ticket();
            t.setId(id);
            t.setPartidoId(partidoId);
            t.setNumeroAsiento(asiento.trim());
            t.setSeccion(seccion);
            t.setPrecio(precio);
            t.setEstado(estado);

            return dao.modificar(t);

        } catch (NumberFormatException e) {
            System.out.println("Precio inválido: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarTicket(int id) {
        return dao.eliminar(id);
    }

    public List<Ticket> obtenerTickets() {
        return dao.listar();
    }
}