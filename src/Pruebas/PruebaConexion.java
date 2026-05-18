/*package Conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO TEST ---");
        
        // Instancia de tu clase de conexión
        CreateConnection generador = new CreateConnection();
        
        // Intentar conectar
        Connection cn = generador.getConnection();
        
        try {
            if (cn != null && !cn.isClosed()) {
                System.out.println("------------------------------------------");
                System.out.println(" ¡LOGRADO! Estás conectado a Postgres");
                System.out.println("Base de datos activa: " + cn.getCatalog());
                System.out.println("------------------------------------------");
            } else {
                System.out.println("❌ Error: La conexión regresó nula.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Fallo en el test: " + e.getMessage());
        } finally {
            // Cerramos la conexión de prueba
            try {
                if (cn != null) {
                    cn.close();
                    System.out.println("Conexión de prueba cerrada.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}*/

/*
public static void main(String[] args) {
    // IMPORTANTE El código se queda aquí guardado pero no se puede ejecutar.
SE QUEDÓ EN FORMA DE COMENTARIO PARA PODER HABILITARLO CUANDO SEA NECESARIO
NI IDEA COMO LO SACÓ ESTE CODIGO EL CHATGPT, SÓLO COPIEN Y PEGUEN.

SÓLO SE PODRÁ EJECUTAR DANDOLE RUN FILE AL ARCHIVO EN SÍ.
}
*/