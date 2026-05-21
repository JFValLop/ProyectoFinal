/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorFX;

/**
 *
 * @author ezayr
 */


import Controlador.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginControlador {

    @FXML private Button btnLogin;
    @FXML private Label lblForget;
    @FXML private PasswordField pfContrasena;
    @FXML private TextField txtUsuario;

    @FXML
    void handleLogin(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contrasena = pfContrasena.getText();

        try {
            UserController userController = new UserController();
            if (userController.login(usuario, contrasena)) {
                // Ruta corregida: /Vista/MenuView.fxml
                Parent root = FXMLLoader.load(getClass().getResource("/Vista/MenuView.fxml"));
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Mundial System 2026");
                stage.show();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLabelClick(MouseEvent event) {
        try {
            // Ruta corregida: /Vista/Recuperar.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Recuperar.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Recuperar contraseña");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLabelEntrar(MouseEvent event) {
        lblForget.setStyle("-fx-font-weight: bold; -fx-underline: true;");
    }

    @FXML
    void handleLabelSalir(MouseEvent event) {
        lblForget.setStyle("");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}