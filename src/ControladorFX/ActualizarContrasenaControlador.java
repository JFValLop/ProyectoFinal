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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ActualizarContrasenaControlador {

    @FXML
    private PasswordField pfNueva;
    @FXML
    private PasswordField pfConfirmar;
    @FXML
    private Button btnActualizar;

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void handleActualizar(ActionEvent event) {
        String nueva = pfNueva.getText();
        String confirmar = pfConfirmar.getText();

        if (nueva.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Completa ambos campos.");
            return;
        }

        if (!nueva.equals(confirmar)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Las contraseñas no coinciden.");
            return;
        }

        try {
            UserController uc = new UserController();
            if (uc.cambiarPassword(email, nueva)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Contraseña actualizada correctamente.");
                Stage stage = (Stage) btnActualizar.getScene().getWindow();
                stage.close();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al actualizar la contraseña.");
            }
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
