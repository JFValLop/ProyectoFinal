/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorFX; // Tu paquete específico para controladores visuales

import Controlador.PartidoController; // Controlador Backend
import Modelo.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class PartidoFXController implements Initializable {

    @FXML private TextField txtId;
    @FXML private TextField txtEquipoLocal;
    @FXML private TextField txtEquipoVisitante;
    @FXML private DatePicker dpFecha;
    @FXML private Spinner<Integer> spHora;
    @FXML private Spinner<Integer> spMinuto;
    @FXML private TextField txtEstadio;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtCapacidad;
    @FXML private ComboBox<String> cmbEstado;
    
    @FXML private Label lblMensaje;
    @FXML private TableView<Partido> tblPartidos;
    @FXML private TableColumn<Partido, Integer> colId;
    @FXML private TableColumn<Partido, String> colLocal;
    @FXML private TableColumn<Partido, String> colVisitante;
    @FXML private TableColumn<Partido, Timestamp> colFecha;
    @FXML private TableColumn<Partido, String> colEstadio;
    @FXML private TableColumn<Partido, String> colCiudad;
    @FXML private TableColumn<Partido, Integer> colCapacidad;
    @FXML private TableColumn<Partido, String> colEstado;

    private PartidoController partidoController;
    private ObservableList<Partido> listaObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partidoController = new PartidoController();
        listaObservable = FXCollections.observableArrayList();
        
        // Configurar las opciones preestablecidas del ComboBox (Garantiza consistencia con el CHECK de Postgres)
        cmbEstado.setItems(FXCollections.observableArrayList("DISPONIBLE", "FINALIZADO", "CANCELADO"));
        cmbEstado.setValue("DISPONIBLE");

        // Vincular columnas de la tabla con los getters de la clase Modelo.Partido
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLocal.setCellValueFactory(new PropertyValueFactory<>("equipoLocal"));
        colVisitante.setCellValueFactory(new PropertyValueFactory<>("equipoVisitante"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstadio.setCellValueFactory(new PropertyValueFactory<>("estadio"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        listaObservable.clear();
        List<Partido> partidosDB = partidoController.obtainPartidos(); // Usa obtainPartidos() o obtenerPartidos()
        listaObservable.addAll(partidosDB);
        tblPartidos.setItems(listaObservable);
    }

    @FXML
    private void handleGuardar() {
        if (!validarFormulario()) return;

        // Construir el Timestamp uniendo el DatePicker y los Spinners de hora
        LocalDate fecha = dpFecha.getValue();
        LocalTime hora = LocalTime.of(spHora.getValue(), spMinuto.getValue());
        Timestamp timestampDefinitivo = Timestamp.valueOf(LocalDateTime.of(fecha, hora));

        boolean exito = partidoController.registrarPartido(
                txtEquipoLocal.getText().trim(),
                txtEquipoVisitante.getText().trim(),
                timestampDefinitivo,
                txtEstadio.getText().trim(),
                txtCiudad.getText().trim(),
                Integer.parseInt(txtCapacidad.getText().trim()),
                cmbEstado.getValue()
        );

        if (exito) {
            mostrarMensaje("Partido registrado correctamente.", "#2ecc71");
            cargarDatosTabla();
            limpiarFormulario();
        } else {
            mostrarMensaje("Error al registrar en la base de datos.", "#e74c3c");
        }
    }

    @FXML
    private void handleModificar() {
        if (txtId.getText().isEmpty()) {
            mostrarMensaje("Seleccione un partido de la tabla.", "#e74c3c");
            return;
        }
        if (!validarFormulario()) return;

        int id = Integer.parseInt(txtId.getText());
        LocalDate fecha = dpFecha.getValue();
        LocalTime hora = LocalTime.of(spHora.getValue(), spMinuto.getValue());
        Timestamp timestampDefinitivo = Timestamp.valueOf(LocalDateTime.of(fecha, hora));

        boolean exito = partidoController.modificarPartido(
                id,
                txtEquipoLocal.getText().trim(),
                txtEquipoVisitante.getText().trim(),
                timestampDefinitivo,
                txtEstadio.getText().trim(),
                txtCiudad.getText().trim(),
                Integer.parseInt(txtCapacidad.getText().trim()),
                cmbEstado.getValue()
        );

        if (exito) {
            mostrarMensaje("Partido modificado con éxito.", "#2ecc71");
            cargarDatosTabla();
            limpiarFormulario();
        } else {
            mostrarMensaje("Error al modificar el partido.", "#e74c3c");
        }
    }

    @FXML
    private void handleEliminar() {
        if (txtId.getText().isEmpty()) {
            mostrarMensaje("Seleccione un partido para eliminar.", "#e74c3c");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar este partido definitivamente?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            int id = Integer.parseInt(txtId.getText());
            boolean exito = partidoController.eliminarPartido(id);
            if (exito) {
                mostrarMensaje("Partido eliminado del sistema.", "#e74c3c");
                cargarDatosTabla();
                limpiarFormulario();
            } else {
                mostrarMensaje("No se pudo eliminar el partido.", "#e74c3c");
            }
        }
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        Partido p = tblPartidos.getSelectionModel().getSelectedItem();
        if (p != null) {
            txtId.setText(String.valueOf(p.getId()));
            txtEquipoLocal.setText(p.getEquipoLocal());
            txtEquipoVisitante.setText(p.getEquipoVisitante());
            txtEstadio.setText(p.getEstadio());
            txtCiudad.setText(p.getCiudad());
            txtCapacidad.setText(String.valueOf(p.getCapacidad()));
            cmbEstado.setValue(p.getEstado());

            // Descomponer el Timestamp devuelto para rellenar el DatePicker y Spinners
            LocalDateTime ldt = p.getFecha().toLocalDateTime();
            dpFecha.setValue(ldt.toLocalDate());
            spHora.getValueFactory().setValue(ldt.getHour());
            spMinuto.getValueFactory().setValue(ldt.getMinute());
            
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
        txtEquipoLocal.clear();
        txtEquipoVisitante.clear();
        dpFecha.setValue(null);
        spHora.getValueFactory().setValue(15);
        spMinuto.getValueFactory().setValue(0);
        txtEstadio.clear();
        txtCiudad.clear();
        txtCapacidad.clear();
        cmbEstado.setValue("DISPONIBLE");
        tblPartidos.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String texto, String colorHex) {
        lblMensaje.setText(texto);
        lblMensaje.setStyle("-fx-text-fill: " + colorHex + ";");
    }

    // VALIDACIONES DE SEGURIDAD CONTRA ERRORES
    private boolean validarFormulario() {
        if (txtEquipoLocal.getText().trim().isEmpty() || txtEquipoVisitante.getText().trim().isEmpty()) {
            mostrarMensaje("Los nombres de los equipos son obligatorios.", "#e74c3c");
            return false;
        }
        
        if (txtEquipoLocal.getText().trim().equalsIgnoreCase(txtEquipoVisitante.getText().trim())) {
            mostrarMensaje("Un equipo no puede jugar contra sí mismo.", "#e74c3c");
            return false;
        }

        if (dpFecha.getValue() == null) {
            mostrarMensaje("Debe seleccionar una fecha para el partido.", "#e74c3c");
            return false;
        }

        String capRaw = txtCapacidad.getText().trim();
        if (capRaw.isEmpty() || !capRaw.matches("\\d+")) {
            mostrarMensaje("La capacidad debe ser un número entero válido.", "#e74c3c");
            return false;
        }

        if (Integer.parseInt(capRaw) <= 0) {
            mostrarMensaje("La capacidad del estadio debe ser mayor a 0.", "#e74c3c");
            return false;
        }

        return true;
    }
}