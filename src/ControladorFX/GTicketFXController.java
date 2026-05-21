/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorFX;

/**
 *
 * @author ezayr
 */
import Controlador.GTicketController;
import Modelo.Partido;
import Modelo.Ticket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Conexion.CreateConnection;

public class GTicketFXController implements Initializable {

    @FXML
    private ComboBox<Partido> cboPartido;
    @FXML
    private TextField txtAsiento;
    @FXML
    private ComboBox<String> cboSeccion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ComboBox<String> cboEstado;

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;

    @FXML
    private TableView<Ticket> tablaTicket;
    @FXML
    private TableColumn<Ticket, Integer> colId;
    @FXML
    private TableColumn<Ticket, String> colPartido;
    @FXML
    private TableColumn<Ticket, String> colAsiento;
    @FXML
    private TableColumn<Ticket, String> colSeccion;
    @FXML
    private TableColumn<Ticket, Double> colPrecio;
    @FXML
    private TableColumn<Ticket, String> colEstado;

    private final CreateConnection neon = new CreateConnection();
    private Connection cn;
    private final GTicketController logicController = new GTicketController();
    private int idSeleccionado = 0;
    private final ObservableList<Ticket> listaTabla = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtPrecio.setEditable(false);
        cn = neon.getConnection();

        cboSeccion.setItems(FXCollections.observableArrayList("VIP", "Preferencial", "General"));
        cboSeccion.getSelectionModel().selectFirst();

        cboEstado.setItems(FXCollections.observableArrayList("DISPONIBLE", "VENDIDO", "RESERVADO"));
        cboEstado.getSelectionModel().selectFirst();

        cboSeccion.setOnAction(e -> actualizarPrecioSegunSeccion());

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartido.setCellValueFactory(new PropertyValueFactory<>("nombrePartido"));
        colAsiento.setCellValueFactory(new PropertyValueFactory<>("numeroAsiento"));
        colSeccion.setCellValueFactory(new PropertyValueFactory<>("seccion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablaTicket.setItems(listaTabla);

        tablaTicket.setOnMouseClicked(e -> {
            Ticket t = tablaTicket.getSelectionModel().getSelectedItem();
            if (t != null) {
                idSeleccionado = t.getId();
                txtAsiento.setText(t.getNumeroAsiento());
                cboSeccion.setValue(t.getSeccion());
                txtPrecio.setText(String.valueOf(t.getPrecio()));
                cboEstado.setValue(t.getEstado());
                for (Partido p : cboPartido.getItems()) {
                    if (p.getId() == t.getPartidoId()) {
                        cboPartido.setValue(p);
                        break;
                    }
                }
            }
        });

        cargarPartidosEnCombo();
        cargarTabla();
        actualizarPrecioSegunSeccion();
    }

    private void actualizarPrecioSegunSeccion() {
        String seccion = cboSeccion.getValue();
        if (seccion == null) {
            return;
        }
        txtPrecio.setText(String.valueOf(calcularPrecio(seccion)));
    }

    private double calcularPrecio(String seccion) {
        if (seccion == null) {
            return 150.00;
        }
        switch (seccion) {
            case "VIP":
                return 500.00;
            case "Preferencial":
                return 300.00;
            default:
                return 150.00;
        }
    }

    private void cargarPartidosEnCombo() {
        cboPartido.getItems().clear();
        String qry = "SELECT * FROM partido WHERE estado = 'DISPONIBLE' ORDER BY fecha";
        try (PreparedStatement ps = cn.prepareStatement(qry); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Partido p = new Partido();
                p.setId(rs.getInt("id"));
                p.setEquipoLocal(rs.getString("equipo_local"));
                p.setEquipoVisitante(rs.getString("equipo_visitante"));
                p.setFecha(rs.getTimestamp("fecha"));
                p.setEstadio(rs.getString("estadio"));
                p.setCiudad(rs.getString("ciudad"));
                p.setCapacidad(rs.getInt("capacidad"));
                p.setEstado(rs.getString("estado"));
                cboPartido.getItems().add(p);
            }
            if (!cboPartido.getItems().isEmpty()) {
                cboPartido.getSelectionModel().selectFirst();
            }
        } catch (Exception ex) {
            mostrarAlerta("Error", "Error al cargar partidos: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnGuardarAction() {
        if (txtAsiento.getText().trim().isEmpty() || cboPartido.getValue() == null) {
            mostrarAlerta("Aviso", "Asiento y partido son obligatorios.", Alert.AlertType.WARNING);
            return;
        }
        String seccion = cboSeccion.getValue();
        Partido p = cboPartido.getValue();
        boolean exito = logicController.registrarTicket(
                p.getId(), txtAsiento.getText().trim(),
                seccion, String.valueOf(calcularPrecio(seccion)), cboEstado.getValue());
        if (exito) {
            mostrarAlerta("Éxito", "Ticket guardado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            cargarTabla();
        } else {
            mostrarAlerta("Error", "No se pudo registrar el ticket.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnModificarAction() {
        if (idSeleccionado == 0 || cboPartido.getValue() == null) {
            mostrarAlerta("Aviso", "Seleccione un ticket de la tabla.", Alert.AlertType.WARNING);
            return;
        }
        String seccion = cboSeccion.getValue();
        Partido p = cboPartido.getValue();
        boolean exito = logicController.modificarTicket(
                idSeleccionado, p.getId(), txtAsiento.getText().trim(),
                seccion, String.valueOf(calcularPrecio(seccion)), cboEstado.getValue());
        if (exito) {
            mostrarAlerta("Éxito", "Ticket modificado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            cargarTabla();
        } else {
            mostrarAlerta("Error", "No se pudo actualizar el ticket.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnEliminarAction() {
        if (idSeleccionado == 0) {
            mostrarAlerta("Aviso", "Seleccione un ticket para eliminar.", Alert.AlertType.WARNING);
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Deseas eliminar permanentemente este ticket?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.YES) {
                if (logicController.eliminarTicket(idSeleccionado)) {
                    mostrarAlerta("Éxito", "Ticket eliminado.", Alert.AlertType.INFORMATION);
                    limpiarCampos();
                    cargarTabla();
                } else {
                    mostrarAlerta("Error", "No se pudo eliminar el ticket.", Alert.AlertType.ERROR);
                }
            }
        });
    }

    @FXML
    private void btnLimpiarAction() {
        limpiarCampos();
    }

    private void cargarTabla() {
        listaTabla.clear();
        List<Ticket> resultado = logicController.obtenerTickets();
        if (resultado != null) {
            listaTabla.addAll(resultado);
        }
    }

    private void limpiarCampos() {
        txtAsiento.clear();
        if (!cboPartido.getItems().isEmpty()) {
            cboPartido.getSelectionModel().selectFirst();
        }
        cboSeccion.getSelectionModel().selectFirst();
        cboEstado.getSelectionModel().selectFirst();
        actualizarPrecioSegunSeccion();
        idSeleccionado = 0;
        tablaTicket.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
