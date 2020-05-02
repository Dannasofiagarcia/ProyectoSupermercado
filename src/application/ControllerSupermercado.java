package application;

import java.io.IOException;
import java.time.LocalTime;

import exception.ClientesSupermercadoException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ProductoSupermercado;
import model.Supermercado;
//import threads.TiempoThread;

public class ControllerSupermercado {

	static final long serialVersionUID = 42L;

	private Stage stage;

	private Supermercado supermercado;

	private ObservableList<ProductoSupermercado> productos;

	private LocalTime tiempoActual;

	@FXML
	private Pane paneMostrarInformacionCliente;

	@FXML
	private Pane paneModificarCodigoProducto;

	@FXML
	private Pane paneModificarNombreProducto;

	@FXML
	private Pane paneModificarUbicacionProducto;

	@FXML
	private Pane paneModificarPrecioProducto;

	@FXML
	private Pane paneRegistrarCliente;

	@FXML
	private Pane paneEliminarCliente;

	@FXML
	private Pane paneTabla;

	@FXML
	private Pane paneInsertarProducto;

	@FXML
	private Pane paneEliminar;

	@FXML
	private Pane paneBuscar;

	@FXML
	private Pane paneBuscarCliente;

	@FXML
	private Button eliminar;

	@FXML
	private Button buscar;

	@FXML
	private Button buscarClienteCodigo;

	@FXML
	private Button buscarCliente;

	@FXML
	private Button registrar;

	@FXML
	private Button eliminarCliente;

	@FXML
	private Button insertarProducto;

	@FXML
	private Button guardar;

	@FXML
	private Button modificarNombre;

	@FXML
	private Button realizarModificacionNombre;

	@FXML
	private Button modificarCodigo;

	@FXML
	private Button realizarModificacionCodigo;

	@FXML
	private Button modificarPrecio;

	@FXML
	private Button realizarModificacionPrecio;

	@FXML
	private Button modificarUbicacion;

	@FXML
	private Button realizarModificacionUbicacion;

	@FXML
	private TextField codigoProductoEliminar;

	@FXML
	private TextField codigoProductoBuscar;

	@FXML
	private TextField rutaDatos;

	@FXML
	private TextField nombreClienteAgregar;

	@FXML
	private TextField apellidoClienteAgregar;

	@FXML
	private TextField codigoClienteAgregar;

	@FXML
	private TextField correoClienteAgregar;

	@FXML
	private TextField codigoClienteEliminar;

	@FXML
	private TextField nombreProductoAgregar;

	@FXML
	private TextField codigoProductoAgregar;

	@FXML
	private TextField precioProductoAgregar;

	@FXML
	private TextField ubicacionProductoAgregar;

	@FXML
	private TextField codigoClienteBuscar;

	@FXML
	private TextField codigoProductoModificarC;

	@FXML
	private TextField codigoProductoNuevo;

	@FXML
	private TextField codigoProductoModificarN;

	@FXML
	private TextField nombreProductoNuevo;

	@FXML
	private TextField codigoProductoModificarP;

	@FXML
	private TextField precioProductoNuevo;

	@FXML
	private TextField codigoProductoModificarU;

	@FXML
	private TextField ubicacionProductoNuevo;

	@FXML
	private Label nombreCliente;

	@FXML
	private Label codigoCliente;

	@FXML
	private Label correoCliente;

	@FXML
	private Label hora;

	@FXML
	private Canvas canvasLogo;

	@FXML
	private ComboBox<String> ordenar;

	@FXML
	private Button buscarPromociones;

	@FXML
	private TextField campoCodigoPromocion;

	@FXML
	private TableView<ProductoSupermercado> tablaProductos;

	/**
	 * Inicializa opciones de la ventana
	 * 
	 */
	@FXML
	void initialize() {
		tiempoActual = LocalTime.now();
		supermercado = new Supermercado("Supermercado");

		// supermercado.cargarProductos(".\\data\\ProductosSupermercado.txt");

		paneTabla.setVisible(true);
		paneEliminar.setVisible(false);
		paneBuscar.setVisible(false);
		paneTabla.getChildren().add(tablaProductos);
		tablaProductos.setPrefSize(347, 200);
		paneTabla.setVisible(true);
	}

	@FXML
	void abrirFarmacia(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/Farmacia.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			FarmaciaController cfc = new FarmaciaController();
			stage = new Stage();
			cfc.setStage(stage);
			stage.setTitle("Informacion de los clientes");
			stage.centerOnScreen();
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException io) {

		}
	}

	/**
	 * Registra un cliente
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void registrarCliente(ActionEvent e) {
		paneEliminarCliente.setVisible(false);
		paneMostrarInformacionCliente.setVisible(false);
		paneRegistrarCliente.setVisible(true);

		registrar.setOnMouseClicked(mouse -> agregarCliente());
	}

	/**
	 * Agrega un cliente
	 * 
	 */

	void agregarCliente() {
		paneRegistrarCliente.setVisible(false);

		String nombreCliente = nombreClienteAgregar.getText();
		String apellidoCliente = apellidoClienteAgregar.getText();
		String codigoCliente = codigoClienteAgregar.getText();
		String correoCliente = correoClienteAgregar.getText();

		try {
			supermercado.agregarClienteSupermercado(nombreCliente, apellidoCliente, codigoCliente, correoCliente);
			Alert dialogo = new Alert(AlertType.INFORMATION);
			dialogo.setTitle("Agregar cliente");
			dialogo.setHeaderText(
					"Se ha agregado con �xito al cliente " + nombreCliente + " con c�digo " + codigoCliente);
			dialogo.show();
		} catch (ClientesSupermercadoException e) {
			Alert dialogo = new Alert(AlertType.ERROR);
			dialogo.setTitle("Agregar cliente");
			dialogo.setHeaderText(e.getMessage());
			dialogo.show();
		}
	}

	/**
	 * Elimina un cliente
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void eliminarClienteBoton(ActionEvent e) {
		paneRegistrarCliente.setVisible(false);
		paneMostrarInformacionCliente.setVisible(false);
		paneEliminarCliente.setVisible(true);

		eliminarCliente.setOnMouseClicked(mouse -> eliminarCliente());
	}

	/**
	 * Elimina un cliente
	 * 
	 */

	void eliminarCliente() {
		paneEliminarCliente.setVisible(false);
		String codigo = codigoClienteEliminar.getText();

		supermercado.eliminarClienteSupermercado(codigo);
		Alert informacionE = new Alert(AlertType.INFORMATION);
		informacionE.setTitle("Eliminar cliente");
		informacionE.setHeaderText("Cliente eliminado con �xito");
		informacionE.show();

	}

	/**
	 * Busca un cliente
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void buscarCliente(ActionEvent e) {
		paneEliminarCliente.setVisible(false);
		paneRegistrarCliente.setVisible(false);
		paneBuscarCliente.setVisible(true);
		// buscarClienteCodigo.setOnMouseClicked(mouse -> mostrarInformacionCliente());

	}

}
