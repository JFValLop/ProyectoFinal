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
import Modelo.Partido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAO {

    private final Connection con;

    public PartidoDAO() {
        //SE CORRIGIO LA RUTA DE CONEXION POR LA MIA (ELENA) [ .properties]
        CreateConnection ConnectionP = new CreateConnection();
        this.con = ConnectionP.getConnection();
    }

    public boolean insertar(Partido p) {

        String sql = "INSERT INTO partido "
                + "(equipo_local, equipo_visitante, "
                + "fecha, estadio, ciudad, "
                + "capacidad, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps
                = con.prepareStatement(sql)) {

            ps.setString(1, p.getEquipoLocal());
            ps.setString(2, p.getEquipoVisitante());
            ps.setTimestamp(3, p.getFecha());
            ps.setString(4, p.getEstadio());
            ps.setString(5, p.getCiudad());
            ps.setInt(6, p.getCapacidad());
            ps.setString(7, p.getEstado());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error insertar partido: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public List<Partido> listar() {

        List<Partido> lista = new ArrayList<>();

        String sql = "SELECT * FROM partido";

        try (Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql)){

            while (rs.next()) {

                Partido p = new Partido();

                p.setId(rs.getInt("id"));
                p.setEquipoLocal(
                        rs.getString("equipo_local")
                );

                p.setEquipoVisitante(
                        rs.getString("equipo_visitante")
                );

                p.setFecha(
                        rs.getTimestamp("fecha")
                );

                p.setEstadio(
                        rs.getString("estadio")
                );

                p.setCiudad(
                        rs.getString("ciudad")
                );

                p.setCapacidad(
                        rs.getInt("capacidad")
                );

                p.setEstado(
                        rs.getString("estado")
                );

                lista.add(p);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar partidos: "
                    + e.getMessage()
            );
        }

        return lista;
    }

    public boolean modificar(Partido p) {

        String sql = "UPDATE partido SET "
                + "equipo_local=?, "
                + "equipo_visitante=?, "
                + "fecha=?, "
                + "estadio=?, "
                + "ciudad=?, "
                + "capacidad=?, "
                + "estado=? "
                + "WHERE id=?";

        try {

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(1, p.getEquipoLocal());
            ps.setString(2, p.getEquipoVisitante());
            ps.setTimestamp(3, p.getFecha());
            ps.setString(4, p.getEstadio());
            ps.setString(5, p.getCiudad());
            ps.setInt(6, p.getCapacidad());
            ps.setString(7, p.getEstado());
            ps.setInt(8, p.getId());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error modificar partido: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM partido WHERE id=?";

        try {

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error eliminar partido: "
                    + e.getMessage()
            );

            return false;
        }
    }
}
