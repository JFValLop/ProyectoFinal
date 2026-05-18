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
import Modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final Connection con;

    public ClienteDAO() {
        CreateConnection ConnectionC = new CreateConnection();
        this.con = ConnectionC.getConnection();
    }

    public boolean insertar(Cliente c) {

        String sql = "INSERT INTO cliente (nombre, apellido, telefono, email, direccion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps
                = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getDireccion());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error insertar cliente: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                c.setDireccion(rs.getString("direccion"));

                lista.add(c);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar clientes: "
                    + e.getMessage()
            );
        }

        return lista;
    }

    public boolean modificar(Cliente c) {

        String sql = "UPDATE cliente SET "
                + "nombre=?, "
                + "apellido=?, "
                + "telefono=?, "
                + "email=?, "
                + "direccion=? "
                + "WHERE id=?";

        try (PreparedStatement ps
                = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getDireccion());
            ps.setInt(6, c.getId());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error modificar cliente: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM cliente WHERE id=?";

        try (PreparedStatement ps
                = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error eliminar cliente: "
                    + e.getMessage()
            );

            return false;
        }
    }
}
