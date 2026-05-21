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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecuperarControlador {

    @FXML
    private Button btnVerificarcodigo;
    @FXML
    private Button btnVerificaremail;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtEmail;

    private int codigoEnviado = 0;
    private String emailIngresado = "";

    @FXML
    void handleVerificarEmail(ActionEvent event) {
        emailIngresado = txtEmail.getText().trim();

        if (emailIngresado.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Ingresa un correo.");
            return;
        }

        try {
            UserController uc = new UserController();
            codigoEnviado = uc.recuperacion(emailIngresado);

            if (codigoEnviado == 0) {
                mostrarAlerta(Alert.AlertType.ERROR, "El correo no existe o hubo un error al enviar.");
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Código enviado a " + emailIngresado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleVerificarCodigo(ActionEvent event) {
        if (codigoEnviado == 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Primero verifica tu correo.");
            return;
        }

        try {
            int codigoIngresado = Integer.parseInt(txtCode.getText().trim());

            if (codigoIngresado == codigoEnviado) {
                // Ruta corregida: /Vista/ActualizarContrasena.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/ActualizarContrasena.fxml"));
                Parent root = loader.load();
                ActualizarContrasenaControlador controlador = loader.getController();
                controlador.setEmail(emailIngresado);
                Stage stage = (Stage) btnVerificarcodigo.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Código incorrecto.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Ingresa un código numérico válido.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
