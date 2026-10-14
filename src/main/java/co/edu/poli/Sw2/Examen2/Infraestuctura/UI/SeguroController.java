package co.edu.poli.Sw2.Examen2.Infraestuctura.UI;

import co.edu.poli.Sw2.Examen2.Aplicacion.Servicios.BuscarListaSegurosServicio;
import co.edu.poli.Sw2.Examen2.Aplicacion.Servicios.BuscarSeguroServicio;
import co.edu.poli.Sw2.Examen2.Aplicacion.Servicios.CrearSeguroServicio;
import co.edu.poli.Sw2.Examen2.Aplicacion.Servicios.EditarSeguroServicio;
import co.edu.poli.Sw2.Examen2.Aplicacion.Servicios.EliminarSeguroServicio;
import co.edu.poli.Sw2.Examen2.Dominio.Asegurado;
import co.edu.poli.Sw2.Examen2.Dominio.Seguro;
import co.edu.poli.Sw2.Examen2.Dominio.SeguroVehiculo;
import co.edu.poli.Sw2.Examen2.Dominio.SeguroVida;
import co.edu.poli.Sw2.Examen2.Infraestuctura.Persistencia.MySqlSeguroRepository;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador principal de la vista de seguros.
 * <p>
 * Conecta la interfaz JavaFX con los servicios de aplicación y realiza la
 * gestión del CRUD para seguros de vida y vehículos.
 * </p>
 *
 * @author Jeisson Romero
 * @version 1.0
 */
public class SeguroController implements Initializable {

    /** Tabla donde se muestran los seguros. */
    @FXML
    private TableView<Seguro> tblSeguros;

    /** Columna con el número del seguro. */
    @FXML
    private TableColumn<Seguro, String> colNumero;

    /** Columna con la fecha de expedición. */
    @FXML
    private TableColumn<Seguro, LocalDate> colFechaExpedicion;

    /** Columna con el valor. */
    @FXML
    private TableColumn<Seguro, Double> colValor;

    /** Columna con el estado. */
    @FXML
    private TableColumn<Seguro, String> colEstado;

    /** Columna con el asegurado asociado. */
    @FXML
    private TableColumn<Seguro, String> colAsegurado;

    /** Columna con el tipo de seguro. */
    @FXML
    private TableColumn<Seguro, String> colTipo;

    /** Campo para el número del seguro. */
    @FXML
    private TextField txtNumero;

    /** Campo para el valor del seguro. */
    @FXML
    private TextField txtValor;

    /** Campo para el estado del seguro. */
    @FXML
    private TextField txtEstado;

    /** Selector de fecha de expedición. */
    @FXML
    private DatePicker dpFechaExpedicion;

    /** Selector del tipo de seguro. */
    @FXML
    private ComboBox<String> cbTipoSeguro;

    /** Selector del asegurado asociado. */
    @FXML
    private ComboBox<Asegurado> cbAsegurado;

    /** Campo para el beneficiario de seguro de vida. */
    @FXML
    private TextField txtBeneficiario;

    /** Campo para el parentesco del beneficiario. */
    @FXML
    private TextField txtParentesco;

    /** Campo para la marca del vehículo. */
    @FXML
    private TextField txtMarca;

    /** Botón para guardar. */
    @FXML
    private Button btnGuardar;

    /** Botón para editar. */
    @FXML
    private Button btnEditar;

    /** Botón para eliminar. */
    @FXML
    private Button btnEliminar;

    /** Botón para limpiar. */
    @FXML
    private Button btnLimpiar;

    /** Campo para buscar un seguro por número. */
    @FXML
    private TextField txtBuscar;

    /** Botón para buscar un seguro. */
    @FXML
    private Button btnBuscar;

    /** Servicio de creación. */
    private final CrearSeguroServicio crearSeguroServicio;

    /** Servicio de consulta por número. */
    private final BuscarSeguroServicio buscarSeguroServicio;

    /** Servicio de edición. */
    private final EditarSeguroServicio editarSeguroServicio;

    /** Servicio de eliminación. */
    private final EliminarSeguroServicio eliminarSeguroServicio;

    /** Servicio de listado. */
    private final BuscarListaSegurosServicio buscarListaSegurosServicio;

    /** Lista observable para la tabla. */
    private final ObservableList<Seguro> listaSeguros = FXCollections.observableArrayList();

    /**
     * Construye el controlador con los servicios de aplicación.
     */
    public SeguroController() {
        MySqlSeguroRepository repository = new MySqlSeguroRepository();
        this.crearSeguroServicio = new CrearSeguroServicio(repository);
        this.buscarSeguroServicio = new BuscarSeguroServicio(repository);
        this.editarSeguroServicio = new EditarSeguroServicio(repository);
        this.eliminarSeguroServicio = new EliminarSeguroServicio(repository);
        this.buscarListaSegurosServicio = new BuscarListaSegurosServicio(repository);
    }

    /**
     * Inicializa los componentes de la vista y carga los datos iniciales.
     *
     * @param url ubicación del recurso
     * @param resourceBundle recursos del locale
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        cargarTiposSeguro();
        cargarAsegurados();
        cargarSeguros();
        registrarEventos();
    }

    /**
     * Configura columnas y propiedades de la tabla.
     */
    private void configurarColumnas() {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colFechaExpedicion.setCellValueFactory(new PropertyValueFactory<>("fechaExpedicion"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colAsegurado.setCellValueFactory(cellData -> {
            Seguro seguro = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(
                    seguro != null && seguro.getAsegurado() != null ? seguro.getAsegurado().getNombre() : ""
            );
        });
        colTipo.setCellValueFactory(cellData -> {
            Seguro seguro = cellData.getValue();
            String tipo = "";
            if (seguro instanceof SeguroVida) {
                tipo = "Vida";
            } else if (seguro instanceof SeguroVehiculo) {
                tipo = "Vehículo";
            }
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });
        tblSeguros.setItems(listaSeguros);
    }

    /**
     * Carga los tipos soportados por el sistema.
     */
    private void cargarTiposSeguro() {
        cbTipoSeguro.setItems(FXCollections.observableArrayList("Vida", "Vehículo"));
    }

    /**
     * Carga los asegurados predeterminados desde el dominio.
     */
    private void cargarAsegurados() {
        Asegurado carlos = new Asegurado("1010555", "Carlos Pérez", "carlos.perez@email.com");
        Asegurado ana = new Asegurado("2020666", "Ana Gómez", "ana.gomez@email.com");
        Asegurado juan = new Asegurado("3030777", "Juan Torres", "juan.torres@email.com");
        cbAsegurado.setItems(FXCollections.observableArrayList(carlos, ana, juan));
    }

    /**
     * Carga todos los seguros y los presenta en la tabla.
     */
    private void cargarSeguros() {
        listaSeguros.clear();
        try {
            List<Seguro> seguros = buscarListaSegurosServicio.listarSeguros();
            if (seguros != null) {
                listaSeguros.addAll(seguros);
            }
        } catch (Exception e) {
            mostrarError("No se pudo conectar a la base de datos. Verifica MySQL y la configuración en .env.\n" + e.getMessage());
        }
    }

    /**
     * Registra los eventos principales de la vista.
     */
    private void registrarEventos() {
        btnGuardar.setOnAction(event -> guardarSeguro());
        btnEditar.setOnAction(event -> editarSeguro());
        btnEliminar.setOnAction(event -> eliminarSeguro());
        btnLimpiar.setOnAction(event -> limpiarFormulario());
        btnBuscar.setOnAction(event -> buscarSeguro());

        tblSeguros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, selected) -> {
            if (selected != null) {
                cargarSeleccionEnFormulario(selected);
            }
        });
    }

    /**
     * Guarda un nuevo seguro según el tipo seleccionado.
     */
    private void guardarSeguro() {
        try {
            if (txtNumero.getText() == null || txtNumero.getText().isBlank()) {
                mostrarError("Debe ingresar el número del seguro.");
                return;
            }
            if (cbAsegurado.getValue() == null) {
                mostrarError("Debe seleccionar un asegurado.");
                return;
            }
            if (dpFechaExpedicion.getValue() == null) {
                mostrarError("Debe seleccionar una fecha de expedición.");
                return;
            }

            Seguro seguro;
            String tipo = cbTipoSeguro.getValue();
            Asegurado asegurado = cbAsegurado.getValue();

            if ("Vida".equalsIgnoreCase(tipo)) {
                seguro = new SeguroVida(
                        txtNumero.getText(),
                        dpFechaExpedicion.getValue(),
                        Double.parseDouble(txtValor.getText().trim()),
                        txtEstado.getText(),
                        asegurado,
                        txtBeneficiario.getText(),
                        txtParentesco.getText()
                );
            } else if ("Vehículo".equalsIgnoreCase(tipo) || "Vehiculo".equalsIgnoreCase(tipo)) {
                seguro = new SeguroVehiculo(
                        txtNumero.getText(),
                        dpFechaExpedicion.getValue(),
                        Double.parseDouble(txtValor.getText().trim()),
                        txtEstado.getText(),
                        asegurado,
                        txtMarca.getText()
                );
            } else {
                mostrarError("Debe seleccionar un tipo de seguro válido.");
                return;
            }

            crearSeguroServicio.crearSeguro(seguro);
            cargarSeguros();
            limpiarFormulario();
            mostrarInfo("Seguro creado correctamente.");
        } catch (Exception e) {
            mostrarError("No se pudo crear el seguro: " + e.getMessage());
        }
    }

    /**
     * Edita el seguro seleccionado.
     */
    private void editarSeguro() {
        Seguro seguroSeleccionado = tblSeguros.getSelectionModel().getSelectedItem();
        if (seguroSeleccionado == null) {
            mostrarError("Debe seleccionar un seguro en la tabla.");
            return;
        }

        try {
            seguroSeleccionado.setNumero(txtNumero.getText());
            seguroSeleccionado.setFechaExpedicion(dpFechaExpedicion.getValue());
            seguroSeleccionado.setValor(Double.parseDouble(txtValor.getText()));
            seguroSeleccionado.setEstado(txtEstado.getText());
            seguroSeleccionado.setAsegurado(cbAsegurado.getValue());

            if (seguroSeleccionado instanceof SeguroVida) {
                ((SeguroVida) seguroSeleccionado).setNombreBeneficiario(txtBeneficiario.getText());
                ((SeguroVida) seguroSeleccionado).setParentesco(txtParentesco.getText());
            } else if (seguroSeleccionado instanceof SeguroVehiculo) {
                ((SeguroVehiculo) seguroSeleccionado).setMarca(txtMarca.getText());
            }

            editarSeguroServicio.editarSeguro(seguroSeleccionado);
            cargarSeguros();
            limpiarFormulario();
            mostrarInfo("Seguro actualizado correctamente.");
        } catch (Exception e) {
            mostrarError("No se pudo actualizar el seguro: " + e.getMessage());
        }
    }

    /**
     * Elimina el seguro actualmente seleccionado.
     */
    private void eliminarSeguro() {
        Seguro seguroSeleccionado = tblSeguros.getSelectionModel().getSelectedItem();
        if (seguroSeleccionado == null) {
            mostrarError("Debe seleccionar un seguro para eliminar.");
            return;
        }

        try {
            eliminarSeguroServicio.eliminarSeguro(seguroSeleccionado.getNumero());
            cargarSeguros();
            limpiarFormulario();
            mostrarInfo("Seguro eliminado correctamente.");
        } catch (Exception e) {
            mostrarError("No se pudo eliminar el seguro: " + e.getMessage());
        }
    }

    /**
     * Busca un seguro por número y lo muestra en el formulario.
     */
    private void buscarSeguro() {
        String numero = txtBuscar.getText();
        if (numero == null || numero.isBlank()) {
            mostrarError("Debe ingresar el número del seguro a buscar.");
            return;
        }

        try {
            Seguro seguro = buscarSeguroServicio.buscarPorNumero(numero.trim());
            if (seguro == null) {
                mostrarError("No se encontró un seguro con el número: " + numero);
                return;
            }

            listaSeguros.clear();
            listaSeguros.add(seguro);
            cargarSeleccionEnFormulario(seguro);
            mostrarInfo("Se encontró el seguro: " + seguro.getNumero());
        } catch (Exception e) {
            mostrarError("No se pudo buscar el seguro: " + e.getMessage());
        }
    }

    /**
     * Carga los datos del seguro seleccionado en el formulario.
     *
     * @param seguro seguro seleccionado
     */
    private void cargarSeleccionEnFormulario(Seguro seguro) {
        txtNumero.setText(seguro.getNumero());
        txtValor.setText(String.valueOf(seguro.getValor()));
        txtEstado.setText(seguro.getEstado());
        dpFechaExpedicion.setValue(seguro.getFechaExpedicion());
        cbAsegurado.setValue(seguro.getAsegurado());

        if (seguro instanceof SeguroVida) {
            cbTipoSeguro.setValue("Vida");
            txtBeneficiario.setText(((SeguroVida) seguro).getNombreBeneficiario());
            txtParentesco.setText(((SeguroVida) seguro).getParentesco());
            txtMarca.clear();
        } else if (seguro instanceof SeguroVehiculo) {
            cbTipoSeguro.setValue("Vehículo");
            txtMarca.setText(((SeguroVehiculo) seguro).getMarca());
            txtBeneficiario.clear();
            txtParentesco.clear();
        }
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarFormulario() {
        txtNumero.clear();
        txtValor.clear();
        txtEstado.clear();
        dpFechaExpedicion.setValue(null);
        cbTipoSeguro.setValue(null);
        cbAsegurado.setValue(null);
        txtBeneficiario.clear();
        txtParentesco.clear();
        txtMarca.clear();
    }

    /**
     * Muestra un mensaje de error en una alerta.
     *
     * @param mensaje descripción del error
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje informativo.
     *
     * @param mensaje contenido del mensaje
     */
    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
