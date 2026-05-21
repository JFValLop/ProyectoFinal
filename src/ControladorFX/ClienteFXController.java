/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorFX; // Ajustado a tu paquete específico

import Controlador.ClienteController; // Importamos el controlador del backend
import Modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteFXController implements Initializable {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private TextField txtDireccion;
    
    @FXML private Button btnGuardar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Label lblMensaje;

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colApellido;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colDireccion;

    private ClienteController clienteController;
    private ObservableList<Cliente> listaObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clienteController = new ClienteController();
        listaObservable = FXCollections.observableArrayList();
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        listaObservable.clear();
        List<Cliente> listaBaseDatos = clienteController.obtenerClientes();
        listaObservable.addAll(listaBaseDatos);
        tblClientes.setItems(listaObservable);
    }

    @FXML
    private void handleGuardar() {
        if (!validarCampos()) return;

        boolean exito = clienteController.registrarCliente(
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtTelefono.getText().trim(),
                txtEmail.getText().trim(),
                txtDireccion.getText().trim()
        );

        if (exito) {
            mostrarMensaje("Cliente registrado exitosamente", "#2ecc71");
            cargarDatosTabla();
            limpiarFormulario();
        } else {
            mostrarMensaje("Error al guardar en la base de datos.", "#e74c3c");
        }
    }

    @FXML
    private void handleModificar() {
        if (txtId.getText().isEmpty()) {
            mostrarMensaje("Seleccione un cliente de la tabla para modificar", "#e74c3c");
            return;
        }
        if (!validarCampos()) return;

        int id = Integer.parseInt(txtId.getText());
        boolean exito = clienteController.modificarCliente(
                id,
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtTelefono.getText().trim(),
                txtEmail.getText().trim(),
                txtDireccion.getText().trim()
        );

        if (exito) {
            mostrarMensaje("Cliente modificado con éxito", "#2ecc71");
            cargarDatosTabla();
            limpiarFormulario();
        } else {
            mostrarMensaje("Error al actualizar el cliente.", "#e74c3c");
        }
    }

    @FXML
    private void handleEliminar() {
        if (txtId.getText().isEmpty()) {
            mostrarMensaje("Seleccione un cliente de la tabla para eliminar", "#e74c3c");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar este cliente?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            int id = Integer.parseInt(txtId.getText());
            boolean exito = clienteController.eliminarCliente(id);
            if (exito) {
                mostrarMensaje("Cliente eliminado", "#e74c3c");
                cargarDatosTabla();
                limpiarFormulario();
            } else {
                mostrarMensaje("No se pudo eliminar el cliente.", "#e74c3c");
            }
        }
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtId.setText(String.valueOf(seleccionado.getId()));
            txtNombre.setText(seleccionado.getNombre());
            txtApellido.setText(seleccionado.getApellido());
            txtTelefono.setText(seleccionado.getTelefono());
            txtEmail.setText(seleccionado.getEmail());
            txtDireccion.setText(seleccionado.getDireccion());
            lblMensaje.setText("");
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarFormulario();
        lblMensaje.setText("");
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtDireccion.clear();
        tblClientes.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String texto, String colorHex) {
        lblMensaje.setText(texto);
        lblMensaje.setStyle("-fx-text-fill: " + colorHex + ";");
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()) {
            mostrarMensaje("Nombre y Apellido son requeridos.", "#e74c3c");
            return false;
        }

        String email = txtEmail.getText().trim();
        if (!email.isEmpty() && (!email.contains("@") || !email.contains("."))) {
            mostrarMensaje("Estructura de Email inválida.", "#e74c3c");
            return false;
        }

        if (txtNombre.getText().length() > 100 || txtApellido.getText().length() > 100) {
            mostrarMensaje("Los nombres/apellidos no deben exceder 100 letras.", "#e74c3c");
            return false;
        }

        String telefono = txtTelefono.getText().trim();
        if (!telefono.isEmpty() && !telefono.matches("\\d+")) {
            mostrarMensaje("El teléfono debe contener únicamente números.", "#e74c3c");
            return false;
        }

        return true;
    }
}