/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;

public class ProbarVistaTicket extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL rutaFxml = null;
        
        // Buscamos el archivo secuencialmente en las rutas donde ha estado guardado
        File opcion1 = new File("src/ControladorFX/GTicket.fxml");
        File opcion2 = new File("src/Vista/GTicket.fxml");
        
        if (opcion1.exists()) {
            rutaFxml = opcion1.toURI().toURL();
            System.out.println("Cargando FXML desde: ControladorFX");
        } else if (opcion2.exists()) {
            rutaFxml = opcion2.toURI().toURL();
            System.out.println("Cargando FXML desde: Vista");
        } else {
            // Intento alternativo por recurso clásico
            rutaFxml = getClass().getResource("/ControladorFX/GTicket.fxml");
        }

        if (rutaFxml == null) {
            throw new java.io.FileNotFoundException("¡Error Crítico! No se encontró el archivo GTicket.fxml en ninguna carpeta del proyecto.");
        }

        FXMLLoader loader = new FXMLLoader(rutaFxml);
        Parent root = loader.load();
        
        primaryStage.setTitle("MundialFX - Gestión de Tickets de Entrada");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}