package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import exception.ClientesSupermercadoException;
import exception.ProductoSupermercadoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClienteSupermercado;
import model.ProductoSupermercado;
import model.PromocionSupermercado;
import model.Supermercado;

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

		paneTabla.setVisible(true);
		paneEliminar.setVisible(false);
		paneBuscar.setVisible(false);
		paneTabla.getChildren().add(tablaProductos);
		tablaProductos.setPrefSize(347, 200);
		paneTabla.setVisible(true);
		configurarCanvas();
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

	}

	/**
	 * Elimina un cliente
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void eliminarClienteBoton(ActionEvent e) {

	}

	/**
	 * Busca un cliente
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void buscarCliente(ActionEvent e) {

	}

	/**
	 * Elimina un producto
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void eliminarProducto(ActionEvent e) {

	}

	@FXML
	void buscarProducto(ActionEvent e) {

	}

	/**
	 * Busca un producto
	 * 
	 */

	/**
	 * Agrega un producto
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void agregarProducto(ActionEvent e) {

	}

	/**
	 * Crea un producto
	 * 
	 */

	/**
	 * Configura el canvas para hacer un logo
	 * 
	 */

	void configurarCanvas() {
		GraphicsContext gc = canvasLogo.getGraphicsContext2D();
		Image image;
		try {
			// image = new Image(new FileInputStream("img\\Cajera1.png"));
			image = new Image(new FileInputStream("img/Cajera1.png"));
			gc.drawImage(image, 0, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modifica la ubicacion
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void modificarUbicacion(ActionEvent e) {

	}

	/**
	 * Modifica el nombre
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void modificarNombre(ActionEvent e) {

	}

	/**
	 * Modifica el c�digo
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void modificarCodigo(ActionEvent e) {

	}

	/**
	 * Modifica el precio
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void modificarPrecio(ActionEvent e) {

	}

	@FXML
	void guardar() {

	}

	/**
	 * Exporta los datos
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void exportar(ActionEvent e) {

	}

	/**
	 * Carga los datos
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void cargarBoton(ActionEvent e) {

	}

	/**
	 * Ver empleados
	 * 
	 * @param ActionEvent e Es la accion que realiza el m�todo en la ventana
	 */

	@FXML
	void verEmpleados(ActionEvent event) {

	}

	@FXML
	public void buscarPromociones() {

	}
}
