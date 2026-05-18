/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author ezayr
 */
import Conexion.CreateConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
//import modelo.Partido;
import modelo.Ticket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GTicketController implements Initializable {

    @FXML
    private ComboBox<Partido> cboPartido;
    @FXML
    private TextField txtAsiento;
    @FXML
    private ComboBox cboSeccion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ComboBox cboEstado;

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

    //LAS VARIABLES PARA LA CNEXION
    CreateConnection neon = new CreateConnection();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    String qry = "";

    int idSeleccionado = 0;
    ObservableList<Ticket> listaTabla = FXCollections.observableArrayList();

    @Override
    //llenar combos
    public void initialize(URL url, ResourceBundle rb) {
        txtPrecio.setEditable(False);//para que nadie edite el precio
        cn = neon.getConnection();
        //para llenar el apartado de seccion
        cboSeccion.setItems(FXCollections.observableArrayList("VIP", "Preferencial", "general"));
        cboSeccion.getSelectionModel().selectFirst();
        //para llenar el apartado de Estado
        cboEstado.setItems(FXCollections.observableArrayList("DISPONIBLE", "VENDIDO", "RESERVADO"));
        cboEstado.getSelectionModel().selectFirst(); // Selecciona "DISPONIBLE" por defecto por el momento se queda así
        
        //configuracion de las columnas de la tabla
        

    }



}
