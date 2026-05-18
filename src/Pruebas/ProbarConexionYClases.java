//CODIGO PARA COMPROBAR SI EL CODIGO FUNCIONA PARA LLENAR LA TABLA (POSTGRESQL)

/*package Controlador; // Cambia a minúscula 'controlador' si tu carpeta está en minúsculas

import java.sql.Timestamp;
import java.util.List;
import Modelo.Partido; // Cambia a 'modelo.Partido' si tus carpetas están en minúsculas

public class ProbarConexionYClases {
    public static void main(String[] args) {
        
        System.out.println("=== INICIANDO PRUEBA DEL SISTEMA ===");
        
        // 1. Instanciamos el controlador que hizo tu compañero
        PartidoController contro = new PartidoController();
        
        // 2. Simulamos que escribimos en Scene Builder y guardamos un partido
        System.out.println("\n1. Intentando registrar un partido de prueba...");
        
        String local = "Guatemala";
        String visitante = "Costa Rica";
        Timestamp fechaHora = new Timestamp(System.currentTimeMillis()); // Fecha y hora de ahorita
        String estadio = "Estadio Mateo Flores";
        String ciudad = "Guatemala";
        int capacidad = 26000;
        String estado = "DISPONIBLE";
        
        // Ejecutamos el método de tu compañero
        boolean seGuardo = contro.registrarPartido(local, visitante, fechaHora, estadio, ciudad, capacidad, estado);
        
        // 3. Evaluamos la respuesta en la consola
        if (seGuardo) {
            System.out.println("[ÉXITO] ¡El código funcionó! Pasó por el Controlador, llegó al DAO y se conectó.");
        } else {
            System.out.println("[ERROR] El código no dio error en NetBeans, pero la base de datos rechazó el registro.");
        }
        
        // 4. Simulamos que queremos ver la tabla llena
        System.out.println("\n2. Intentando leer los partidos de la base de datos...");
        List<Partido> listaDefinitiva = contro.obtainPartidos(); // O obtenerPartidos(), como se llame tu método
        
        if(listaDefinitiva.isEmpty()) {
            System.out.println("[AVISO] La base de datos se conectó, pero la tabla 'partido' está vacía.");
        } else {
            System.out.println("[OK] Se leyeron los datos con éxito. Lista de partidos actuales:");
            for (Partido p : listaDefinitiva) {
                System.out.println("-> ID: " + p.getId() + " | " + p.getEquipoLocal() + " vs " + p.getEquipoVisitante() + " | Estado: " + p.getEstado());
            }
        }
        
        System.out.println("\n=== FIN DE LA PRUEBA ===");
    }
}*/