/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ezayr
 */
import DAO.PartidoDAO;
import Modelo.Partido;
import java.sql.Timestamp;
import java.util.List;

public class PartidoController {

    private final PartidoDAO dao;

    public PartidoController() {
        dao = new PartidoDAO();
    }

    public boolean registrarPartido(
            String equipoLocal,
            String equipoVisitante,
            Timestamp fecha,
            String estadio,
            String ciudad,
            int capacidad,
            String estado
    ) {

        if(equipoLocal.isEmpty() ||
           equipoVisitante.isEmpty()) {

            System.out.println(
                    "Los equipos son obligatorios"
            );

            return false;
        }

        if(capacidad <= 0) {

            System.out.println(
                    "Capacidad invalida"
            );

            return false;
        }

        Partido partido = new Partido();

        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setFecha(fecha);
        partido.setEstadio(estadio);
        partido.setCiudad(ciudad);
        partido.setCapacidad(capacidad);
        partido.setEstado(estado);

        return dao.insertar(partido);
    }

    public List<Partido> obtenerPartidos() {
        return dao.listar();
    }

    public boolean modificarPartido(
            int id,
            String equipoLocal,
            String equipoVisitante,
            Timestamp fecha,
            String estadio,
            String ciudad,
            int capacidad,
            String estado
    ) {

        Partido partido = new Partido();

        partido.setId(id);
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setFecha(fecha);
        partido.setEstadio(estadio);
        partido.setCiudad(ciudad);
        partido.setCapacidad(capacidad);
        partido.setEstado(estado);

        return dao.modificar(partido);
    }

    public boolean eliminarPartido(int id) {
        return dao.eliminar(id);
    }

    public List<Partido> obtainPartidos() {
    return dao.listar(); 
}
}