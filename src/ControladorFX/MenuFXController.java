/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MenuFXController {

    @FXML private AnchorPane contenedorPrincipal;

    @FXML
    private void cargarClientes() {
        cambiarVista("/Vista/ClienteView.fxml");
    }

    @FXML
    private void cargarPartidos() {
        cambiarVista("/Vista/PartidoView.fxml");
    }

    @FXML
    private void cargarTickets() {
        cambiarVista("/Vista/GTicket.fxml");
    }

    private void cambiarVista(String rutaFXML) {
        try {
            contenedorPrincipal.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent vista = loader.load();
            AnchorPane.setTopAnchor(vista, 0.0);
            AnchorPane.setBottomAnchor(vista, 0.0);
            AnchorPane.setLeftAnchor(vista, 0.0);
            AnchorPane.setRightAnchor(vista, 0.0);
            contenedorPrincipal.getChildren().add(vista);
        } catch (IOException e) {
            System.err.println("Error al cargar modulo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}