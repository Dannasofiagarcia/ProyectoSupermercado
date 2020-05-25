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

<<<<<<< HEAD
import excepciones.ClientesSupermercadoException;
import excepciones.ProductoSupermercadoException;
import javafx.collections.FXCollections;
=======
import exception.ClientesSupermercadoException;
import exception.ProductoSupermercadoException;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.time.LocalTime;

import exception.ClientesSupermercadoException;

>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
<<<<<<< HEAD
=======

>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
<<<<<<< HEAD
import modelo.ClienteSupermercado;
import modelo.ProductoSupermercado;
import modelo.PromocionSupermercado;
import modelo.Supermercado;
import threads.TiempoThread;
=======
import model.ClienteSupermercado;
import model.ProductoSupermercado;
import model.PromocionSupermercado;
import model.Supermercado;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ProductoSupermercado;
import model.Supermercado;
//import threads.TiempoThread;
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df

public class ControllerSupermercado {

	static final long serialVersionUID = 42L;

	private Stage stage;

	private Supermercado supermercado;

	private ObservableList<ProductoSupermercado> productos;

	private TiempoThread tiempoThread;

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

<<<<<<< HEAD
		// supermercado.cargarProductos(".\\data\\ProductosSupermercado.txt");
		supermercado.cargarProductos("data/ProductosSupermercado.txt");
		tiempoThread = new TiempoThread(this);
		tiempoThread.start();
		cambiarTiempo();

=======
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
		paneTabla.setVisible(true);
		paneEliminar.setVisible(false);
		paneBuscar.setVisible(false);
		tablaProductos = crearTablaProductos();
		paneTabla.getChildren().add(tablaProductos);
		tablaProductos.setPrefSize(347, 200);
		paneTabla.setVisible(true);
		configurarCanvas();
<<<<<<< HEAD
		cargar();
	}

	@FXML
	void exportarArchivo(ActionEvent event) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir archivo");
		fileChooser.showOpenDialog(stage);
=======

>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
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
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void registrarCliente(ActionEvent e) {
<<<<<<< HEAD
		paneEliminarCliente.setVisible(false);
		paneMostrarInformacionCliente.setVisible(false);
		paneRegistrarCliente.setVisible(true);
=======
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df

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
					"Se ha agregado con exito al cliente " + nombreCliente + " con codigo " + codigoCliente);
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
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
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
		informacionE.setHeaderText("Cliente eliminado con exito");
		informacionE.show();

	}

	/**
	 * Busca un cliente
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void buscarCliente(ActionEvent e) {
		paneEliminarCliente.setVisible(false);
		paneRegistrarCliente.setVisible(false);
		paneBuscarCliente.setVisible(true);
		buscarClienteCodigo.setOnMouseClicked(mouse -> mostrarInformacionCliente());

	}

	/**
	 * Busca un cliente
	 * 
	 */

	void mostrarInformacionCliente() {
		paneBuscarCliente.setVisible(false);
		paneMostrarInformacionCliente.setVisible(true);
		String codigo = codigoClienteBuscar.getText();
		ClienteSupermercado buscado = supermercado.buscarCliente(codigo);

		if (buscado != null) {
			nombreCliente.setText(buscado.getNombre());
			codigoCliente.setText(buscado.getCodigo());
			correoCliente.setText(buscado.getCorreo());
		} else {
			Alert informacionE = new Alert(AlertType.ERROR);
			informacionE.setTitle("Cliente no encontrado");
			informacionE.setHeaderText(
					"No se ha podido mostrar la informaciï¿½n del cliente, puesto que no fue encontrado");
			informacionE.show();
		}
	}

	/**
	 * Elimina un producto
	 * 
	 * @param ActionEvent e Es la accion que realiza el metodo en la ventana
	 */

	@FXML
	void eliminarProducto(ActionEvent e) {
		paneModificarUbicacionProducto.setVisible(false);
		paneTabla.setVisible(false);
		paneEliminar.setVisible(true);

		eliminar.setOnMouseClicked(mouse -> eliminar());
	}

	/**
	 * Elimina un producto
	 *
	 */

	void eliminar() {
		String codigo = codigoProductoEliminar.getText();
		paneEliminar.setVisible(false);
		paneTabla.setVisible(true);

		try {
			supermercado.eliminarProducto(codigo);
			Alert informacionE = new Alert(AlertType.INFORMATION);
			informacionE.setTitle("Eliminar producto");
			informacionE.setHeaderText("Producto eliminado con exito");
			informacionE.show();
		} catch (ProductoSupermercadoException e) {
			Alert informacionE = new Alert(AlertType.ERROR);
			informacionE.setTitle("Eliminar producto");
			informacionE.setHeaderText(e.getMessage());
			informacionE.show();
		}
		actualizarProductos();
		tablaProductos = crearTablaProductos();
	}

	/**
	 * Busca un producto
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void buscarProducto(ActionEvent e) {
		paneModificarUbicacionProducto.setVisible(false);
		paneTabla.setVisible(false);
		paneBuscar.setVisible(true);
		paneEliminar.setVisible(false);

		buscar.setOnMouseClicked(mouse -> buscar());
	}

	/**
	 * Busca un producto
	 * 
	 */

	void buscar() {
		String codigoBuscar = codigoProductoBuscar.getText();
		ProductoSupermercado producto = supermercado.buscarProductoSupermercado(codigoBuscar);

		paneBuscar.setVisible(false);
		paneTabla.setVisible(true);

		if (producto != null) {
			Alert informacionP = new Alert(AlertType.INFORMATION);
			informacionP.setTitle("Informaciï¿½n producto");
			informacionP
					.setHeaderText("El nombre del producto es " + producto.getNombre() + "\nEl precio del producto es "
							+ producto.getPrecio() + "\nEl producto esta ubicado en " + producto.getUbicacion());

			informacionP.show();
		} else {
			Alert informacionP = new Alert(AlertType.ERROR);
			informacionP.setTitle("Informaciï¿½n producto");
			informacionP.setHeaderText("El producto no existe");

			informacionP.show();
		}
		producto = null;
	}

	/**
	 * Agrega un producto
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void agregarProducto(ActionEvent e) {
		paneModificarUbicacionProducto.setVisible(false);
		paneEliminar.setVisible(false);
		paneInsertarProducto.setVisible(true);
		paneTabla.setVisible(false);

		insertarProducto.setOnMouseClicked(mouse -> crearProductoNuevo());
	}

	/**
	 * Crea un producto
	 * 
	 */

	void crearProductoNuevo() {
		paneInsertarProducto.setVisible(false);
		paneTabla.setVisible(true);
		String nombre = nombreProductoAgregar.getText();
		String codigo = codigoProductoAgregar.getText();
		double precio = Double.parseDouble(precioProductoAgregar.getText());
		String ubicacion = ubicacionProductoAgregar.getText();

		try {
			supermercado.agregarProductoSupermercado(codigo, nombre, precio, ubicacion);
			Alert creado = new Alert(AlertType.INFORMATION);
			creado.setTitle("Crear producto");
			creado.setHeaderText("Producto agregado con exito");
			creado.show();
		} catch (ParseException e) {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Ha ocurrido un error");
			parseE.setHeaderText("Ocurriï¿½ un error al convertir un valor");
			parseE.show();
		} catch (ProductoSupermercadoException e) {
			Alert excepcion = new Alert(AlertType.ERROR);
			excepcion.setTitle("Ha ocurrido un error");
			excepcion.setHeaderText(e.getMessage());
			excepcion.show();
		}
		actualizarProductos();
		tablaProductos = crearTablaProductos();
	}

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
<<<<<<< HEAD
		}
=======
		paneEliminarCliente.setVisible(false);
		paneMostrarInformacionCliente.setVisible(false);
		paneRegistrarCliente.setVisible(true);

		registrar.setOnMouseClicked(mouse -> agregarCliente());
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
	}

	/**
	 * Modifica la ubicacion
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void modificarUbicacion(ActionEvent e) {
		paneEliminar.setVisible(false);
		paneInsertarProducto.setVisible(false);
		paneTabla.setVisible(false);
		paneModificarUbicacionProducto.setVisible(true);

		realizarModificacionUbicacion.setOnMouseClicked(mouse -> realizarModificacionU());
	}

<<<<<<< HEAD
	/**
	 * Modifica la ubicacion
	 * 
	 */

	void realizarModificacionU() {
		paneModificarUbicacionProducto.setVisible(false);
		paneTabla.setVisible(true);

		String codigo = codigoProductoModificarU.getText();
		String ubicacion = ubicacionProductoNuevo.getText();

		ProductoSupermercado producto = supermercado.buscarProductoSupermercado(codigo);

		if (producto != null) {
			producto.setUbicacion(ubicacion);
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Modificar ubicaciï¿½n");
			parseE.setHeaderText("Se ha modificado correctamente la ubicaciï¿½n");
			parseE.show();
		} else {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Modificar ubicaciï¿½n");
			parseE.setHeaderText("No se encontro el producto que se deseaba modificar");
			parseE.show();
		}

		actualizarProductos();
		tablaProductos = crearTablaProductos();
	}

	/**
	 * Modifica el nombre
=======
		supermercado.agregarClienteSupermercado(nombreCliente, apellidoCliente, codigoCliente, correoCliente);
		Alert dialogo = new Alert(AlertType.INFORMATION);
		dialogo.setTitle("Agregar cliente");
		dialogo.setHeaderText("Se ha agregado con exito al cliente " + nombreCliente + " con codigo " + codigoCliente);
		dialogo.show();

		try {
			supermercado.agregarClienteSupermercado(nombreCliente, apellidoCliente, codigoCliente, correoCliente);
			Alert dialogo = new Alert(AlertType.INFORMATION);
			dialogo.setTitle("Agregar cliente");
			dialogo.setHeaderText(
					"Se ha agregado con ï¿½xito al cliente " + nombreCliente + " con cï¿½digo " + codigoCliente);
			dialogo.show();
		} catch (ClientesSupermercadoException e) {
			Alert dialogo = new Alert(AlertType.ERROR);
			dialogo.setTitle("Agregar cliente");
			dialogo.setHeaderText(e.getMessage());
			dialogo.show();
		}
	}

	/**
	 * Modifica la ubicacion
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void modificarNombre(ActionEvent e) {
		paneEliminar.setVisible(false);
		paneInsertarProducto.setVisible(false);
		paneTabla.setVisible(false);
		paneModificarUbicacionProducto.setVisible(false);
		paneModificarNombreProducto.setVisible(true);

		realizarModificacionNombre.setOnMouseClicked(mouse -> realizarModificacionN());
	}

	/**
	 * Modifica el nombre
	 * 
	 *
	 */

	void realizarModificacionN() {
		paneModificarNombreProducto.setVisible(false);
		paneTabla.setVisible(true);

		String codigo = codigoProductoModificarN.getText();
		String nombre = nombreProductoNuevo.getText();

		ProductoSupermercado producto = supermercado.buscarProductoSupermercado(codigo);

		if (producto != null) {
			producto.setNombre(nombre);
			;
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Modificar ubicaciï¿½n");
			parseE.setHeaderText("Se ha modificado correctamente el nombre");
			parseE.show();
		} else {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Modificar nombre");
			parseE.setHeaderText("No se encontro el producto que se deseaba modificar");
			parseE.show();
		}

		actualizarProductos();
		tablaProductos = crearTablaProductos();
	}

	/**
	 * Modifica el código
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void modificarCodigo(ActionEvent e) {
		paneEliminar.setVisible(false);
		paneInsertarProducto.setVisible(false);
		paneTabla.setVisible(false);
		paneModificarUbicacionProducto.setVisible(false);
		paneModificarNombreProducto.setVisible(false);
		paneModificarCodigoProducto.setVisible(true);

		realizarModificacionCodigo.setOnMouseClicked(mouse -> realizarModificacionC());
	}

	/**
	 * Modifica el codigo
	 * 
	 *
	 */

	void realizarModificacionC() {
		paneModificarCodigoProducto.setVisible(false);
		paneTabla.setVisible(true);

		String codigo = codigoProductoModificarC.getText();
		String codigoNuevo = codigoProductoNuevo.getText();

		ProductoSupermercado producto = supermercado.buscarProductoSupermercado(codigo);

		if (producto != null) {
			producto.setCodigo(codigoNuevo);
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Modificar código");
			parseE.setHeaderText("Se ha modificado correctamente el código");
			parseE.show();
		} else {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Modificar código");
			parseE.setHeaderText("No se encontro el producto que se deseaba modificar");
			parseE.show();
		}

		actualizarProductos();
		tablaProductos = crearTablaProductos();
	}

	/**
<<<<<<< HEAD
	 * Modifica el precio
=======
	 * Modifica el precio ======= Elimina un cliente >>>>>>>
	 * 378bf6232147068a01892b3d89d03f21d5a95279
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
<<<<<<< HEAD
=======

>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
	void modificarPrecio(ActionEvent e) {
		paneEliminar.setVisible(false);
		paneInsertarProducto.setVisible(false);
		paneTabla.setVisible(false);
		paneModificarUbicacionProducto.setVisible(false);
		paneModificarNombreProducto.setVisible(false);
		paneModificarCodigoProducto.setVisible(false);
		paneModificarPrecioProducto.setVisible(true);

		realizarModificacionPrecio.setOnMouseClicked(mouse -> realizarModificacionP());
	}

	/**
	 * Modifica el precio
	 * 
	 *
	 */

	void realizarModificacionP() {
		paneModificarPrecioProducto.setVisible(false);
		paneTabla.setVisible(true);

		String codigo = codigoProductoModificarP.getText();
		double precio = Double.parseDouble(precioProductoNuevo.getText());

		ProductoSupermercado producto = supermercado.buscarProductoSupermercado(codigo);

		if (producto != null) {
			producto.setPrecio(precio);
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Modificar precio");
			parseE.setHeaderText("Se ha modificado correctamente el precio");
			parseE.show();
		} else {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Modificar precio");
			parseE.setHeaderText("No se encontro el producto que se deseaba modificar");
			parseE.show();
		}

		actualizarProductos();
		tablaProductos = crearTablaProductos();
	}

	/**
	 * Crea productos
	 * 
	 * 
	 */

	private ObservableList<ProductoSupermercado> crearProductos() {
		productos = FXCollections.observableArrayList();

		productos.addAll(supermercado.darArrayProductosSupermercado());
		return productos;
	}

	/**
	 * Actualiza productos
	 * 
	 * 
	 */

	private void actualizarProductos() {
		productos.clear();
		productos.addAll(supermercado.darArrayProductosSupermercado());
	}

	/**
	 * Crea tabla productos
	 * 
	 *
	 */

	@SuppressWarnings("unchecked")
	private TableView<ProductoSupermercado> crearTablaProductos() {
		tablaProductos = new TableView<ProductoSupermercado>();
		productos = crearProductos();
		tablaProductos.setEditable(true);

		TableColumn<ProductoSupermercado, String> columnaNombre = new TableColumn<ProductoSupermercado, String>(
				"Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<ProductoSupermercado, String>("nombre"));

		TableColumn<ProductoSupermercado, String> columnaCodigo = new TableColumn<ProductoSupermercado, String>(
				"Codigo");
		columnaCodigo.setCellValueFactory(new PropertyValueFactory<ProductoSupermercado, String>("codigo"));

		TableColumn<ProductoSupermercado, Double> columnaPrecio = new TableColumn<ProductoSupermercado, Double>(
				"Precio");
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<ProductoSupermercado, Double>("precio"));

		TableColumn<ProductoSupermercado, String> columnaUbicacion = new TableColumn<ProductoSupermercado, String>(
				"Ubicación");
		columnaUbicacion.setCellValueFactory(new PropertyValueFactory<ProductoSupermercado, String>("ubicacion"));

		tablaProductos.setItems(productos);
		tablaProductos.getColumns().addAll(columnaNombre, columnaCodigo, columnaPrecio, columnaUbicacion);
		tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return tablaProductos;
	}

	/**
	 * Guarda los datos
	 * 
	 *
	 */

	@FXML
<<<<<<< HEAD
	void guardar() {
		File archivo = new File("./data/Supermercado.txt");
		try {
			if (archivo.exists()) {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
				oos.writeObject(supermercado);
				oos.close();

			} else {
				archivo.createNewFile();
			}
			supermercado.guardarProductos("./data/ProductosSupermercado.txt");
			Alert excepcion1 = new Alert(AlertType.INFORMATION);
			excepcion1.setTitle("Guardar informaciï¿½n");
			excepcion1.setHeaderText("Informaciï¿½n guardada con exito en el archivo");
			excepcion1.show();
=======
	void cargarBoton(ActionEvent e) {
	}

	void eliminarClienteBoton(ActionEvent e) {
		paneRegistrarCliente.setVisible(false);
		paneMostrarInformacionCliente.setVisible(false);
		paneEliminarCliente.setVisible(true);
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df

		} catch (FileNotFoundException e) {
			Alert excepcion2 = new Alert(AlertType.ERROR);
			excepcion2.setTitle("Archivo no encontrado");
			excepcion2.setHeaderText("No fue posible guardar la informaciï¿½n, el archivo no fue encontrado");
			excepcion2.show();
		} catch (IOException e) {
			Alert excepcion3 = new Alert(AlertType.ERROR);
			excepcion3.setTitle("No fue posible leer el archivo");
			excepcion3.setHeaderText("Ocurrio un error intentando leer el archivo");
			excepcion3.show();
		}
	}

	/**
	 * Carga los datos
	 * 
	 * 
	 */

	void cargar() {
		File archivo = new File("./data/Supermercado.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
			supermercado = (Supermercado) ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

<<<<<<< HEAD
	/**
	 * Exporta los datos
	 * 
	 * @param ActionEvent e Es la accion que realiza el método en la ventana
	 */

	@FXML
	void exportar(ActionEvent e) {
		String ruta = rutaDatos.getText();
		try {
			supermercado.guardarProductos(ruta);
		} catch (IOException iE) {
			Alert excepcion3 = new Alert(AlertType.ERROR);
			excepcion3.setTitle("No fue posible leer el archivo");
			excepcion3.setHeaderText("Ocurrio un error intentando leer el archivo");
			excepcion3.show();
		}
	}

	/**
	 * Carga los datos
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */
=======
		supermercado.eliminarClienteSupermercado(codigo);
		Alert informacionE = new Alert(AlertType.INFORMATION);
		informacionE.setTitle("Eliminar cliente");
		informacionE.setHeaderText("Cliente eliminado con ï¿½xito");
		informacionE.show();
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df

	@FXML
	void cargarBoton(ActionEvent e) {
		String ruta = rutaDatos.getText();
		supermercado.cargarProductos(ruta);
		actualizarProductos();
		tablaProductos = crearTablaProductos();
	}

	/**
<<<<<<< HEAD
	 * Ver empleados
=======
	 * <<<<<<< HEAD Ver empleados ======= Busca un cliente >>>>>>>
	 * 378bf6232147068a01892b3d89d03f21d5a95279
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df
	 * 
	 * @param ActionEvent e Es la accion que realiza el mï¿½todo en la ventana
	 */

	@FXML
	void verEmpleados(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmpleadosVentana.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			ControllerClientes controllerClientes = new ControllerClientes();
			stage = new Stage();
			controllerClientes.setStage(stage);
			stage.setTitle("Informaciï¿½n empleados");
			stage.centerOnScreen();
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			Alert excepcion3 = new Alert(AlertType.ERROR);
			excepcion3.setTitle("No fue posible leer el archivo");
			excepcion3.setHeaderText("Ocurrio un error intentando leer el archivo");
			excepcion3.show();
		}
	}

	/**
	 * Cambiar tiempo
	 * 
	 *
	 */

	public void cambiarTiempo() {
		tiempoActual = tiempoActual.plusSeconds(1);
		hora.setText(DateTimeFormatter.ofPattern("hh:mm:ss a").format(tiempoActual));
	}
<<<<<<< HEAD
=======

	void buscarCliente(ActionEvent e) {
		paneEliminarCliente.setVisible(false);
		paneRegistrarCliente.setVisible(false);
		paneBuscarCliente.setVisible(true);
		// buscarClienteCodigo.setOnMouseClicked(mouse -> mostrarInformacionCliente());
>>>>>>> cd5a47b85c3df8a78a401c8a445bf4993b7243df

	@FXML
	public void buscarPromociones() {
		String codigo = campoCodigoPromocion.getText();
		PromocionSupermercado[] promocion = supermercado.metodoBurbuja();
		PromocionSupermercado promo = supermercado.busquedaBinariaCodigo(promocion, codigo);
		if (promo != null) {
			Alert excepcion3 = new Alert(AlertType.INFORMATION);
			excepcion3.setTitle("Informacion promoción");
			excepcion3.setHeaderText("La promoción buscada ofrece " + promo.getPromocion());
			excepcion3.show();
		} else {
			Alert excepcion3 = new Alert(AlertType.ERROR);
			excepcion3.setTitle("Ha ocurrido un error");
			excepcion3.setHeaderText("No se encontró ninguna promoción con ese nombre");
			excepcion3.show();
		}
	}

}
