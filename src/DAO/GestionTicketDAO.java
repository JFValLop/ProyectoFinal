/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author ezayr
 */
import Conexion.CreateConnection;
import Modelo.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionTicketDAO {

    private final Connection con;

    public GestionTicketDAO() {
        CreateConnection ConnectionGT = new CreateConnection();
        this.con = ConnectionGT.getConnection();
    }

    public boolean insertar(Ticket t) {
        String sql = "INSERT INTO ticket " + "(partido_id, numero_asiento, seccion, precio, estado) " + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getPartidoId());
            ps.setString(2, t.getNumeroAsiento());
            ps.setString(3, t.getSeccion());
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getEstado());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar ticket: " + e.getMessage());
            return false;
        }

    }

    public List<Ticket> listar() {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT t.id, t.partido_id, t.numero_asiento, "
                + "t.seccion, t.precio, t.estado, "
                + "p.equipo_local || ' vs ' || p.equipo_visitante AS nombre_partido "
                + "FROM ticket t "
                + "JOIN partido p ON t.partido_id = p.id "
                + "ORDER BY t.id";
        try (Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error en listar tickets: " + e.getMessage());
        }
        return lista;
    }
    public List<Ticket> listarDisponiblesPorPartido(int partidoId) {

        List<Ticket> lista = new ArrayList<>();

        String sql = "SELECT * FROM ticket "
                   + "WHERE partido_id = ? "
                   + "AND estado = 'DISPONIBLE' "
                   + "ORDER BY numero_asiento";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, partidoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getInt("id"));
                t.setPartidoId(rs.getInt("partido_id"));
                t.setNumeroAsiento(rs.getString("numero_asiento"));
                t.setSeccion(rs.getString("seccion"));
                t.setPrecio(rs.getDouble("precio"));
                t.setEstado(rs.getString("estado"));
                lista.add(t);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Error listar tickets disponibles: " + e.getMessage());
        }

        return lista;
    }

    public boolean modificar(Ticket t) {

        String sql = "UPDATE ticket SET "
                   + "partido_id = ?, "
                   + "numero_asiento = ?, "
                   + "seccion = ?, "
                   + "precio = ?, "
                   + "estado = ? "
                   + "WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getPartidoId());
            ps.setString(2, t.getNumeroAsiento());
            ps.setString(3, t.getSeccion());
            ps.setDouble(4, t.getPrecio());
            ps.setString(5, t.getEstado());
            ps.setInt(6, t.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error modificar ticket: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM ticket WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error eliminar ticket: " + e.getMessage());
            return false;
        }
    }

    public boolean cambiarEstado(int ticketId, String nuevoEstado) {

        String sql = "UPDATE ticket SET estado = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, ticketId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error cambiar estado ticket: " + e.getMessage());
            return false;
        }
    }

    private Ticket mapear(ResultSet rs) throws SQLException {
        Ticket t = new Ticket();
        t.setId(rs.getInt("id"));
        t.setPartidoId(rs.getInt("partido_id"));
        t.setNumeroAsiento(rs.getString("numero_asiento"));
        t.setSeccion(rs.getString("seccion"));
        t.setPrecio(rs.getDouble("precio"));
        t.setEstado(rs.getString("estado"));
        t.setNombrePartido(rs.getString("nombre_partido"));
        return t;
    }

}
