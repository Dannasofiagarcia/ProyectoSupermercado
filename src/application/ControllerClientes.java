package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import excepciones.CargoSupermercadoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.EmpleadoSupermercado;
import modelo.Supermercado;

public class ControllerClientes {

	static final long serialVersionUID = 42L;

	private Supermercado supermercado;

	@FXML
	private Button contratarEmpleado;

	@FXML
	private Button contratar;

	@FXML
	private Button despedirEmpleado;

	@FXML
	private Button despedir;

	@FXML
	private Button buscarEmpleado;

	@FXML
	private Button buscarE;

	@FXML
	private Button agregarCargo;

	@FXML
	private Button agregar;

	@FXML
	private Button eliminarCargo;

	@FXML
	private Button eliminar;

	@FXML
	private Pane paneContratarEmpleado;

	@FXML
	private Pane paneDespedirEmpleado;

	@FXML
	private Pane paneBuscarEmpleado;

	@FXML
	private Pane paneAgregarCargo;

	@FXML
	private Pane paneEliminarCargo;

	@FXML
	private TextField nombreContratar;

	@FXML
	private TextField codigoContratar;

	@FXML
	private TextField turnoContratar;

	@FXML
	private TextField cargoContratar;

	@FXML
	private TextField codigoBuscarEmpleado;

	@FXML
	private TextField codigoDespedirEmpleado;

	@FXML
	private TextField nombreCargoAgregar;

	@FXML
	private TextField jefeCargoAgregar;

	@FXML
	private TextField nombreEliminarCargo;

	@FXML
	private Canvas canvas;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void initialize() {
		supermercado = new Supermercado("Supermercado");
		configurarCanvas();
		cargar();
	}

	@FXML
	void contratarEmpleado(ActionEvent e) {
		paneContratarEmpleado.setVisible(true);
		paneDespedirEmpleado.setVisible(false);
		paneBuscarEmpleado.setVisible(false);
		paneAgregarCargo.setVisible(false);
		paneEliminarCargo.setVisible(false);

		contratar.setOnMouseClicked(mouse -> contratar());

	}

	void contratar() {
		paneContratarEmpleado.setVisible(false);
		String nombre = nombreContratar.getText();
		String codigo = codigoContratar.getText();
		String turno = turnoContratar.getText();
		String cargo = cargoContratar.getText();
		try {
			supermercado.contratarPersona(nombre, codigo, turno, cargo);
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Contratar persona");
			parseE.setHeaderText(nombre + " ha sido contratada con éxito");
			parseE.show();
		} catch (CargoSupermercadoException e) {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Ha ocurrido un error");
			parseE.setHeaderText(e.getMessage());
			parseE.show();
		}
	}

	@FXML
	void buscarEmpleado(ActionEvent e) {
		paneContratarEmpleado.setVisible(false);
		paneDespedirEmpleado.setVisible(false);
		paneBuscarEmpleado.setVisible(true);
		paneAgregarCargo.setVisible(false);
		paneEliminarCargo.setVisible(false);

		buscarE.setOnMouseClicked(mouse -> buscarEmpleado());
	}

	void buscarEmpleado() {
		paneBuscarEmpleado.setVisible(false);
		String codigoEmpleado = codigoBuscarEmpleado.getText();
		EmpleadoSupermercado empleado = supermercado.buscarEmpleado(codigoEmpleado);

		if (empleado != null) {
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Buscar persona");
			parseE.setHeaderText("El nombre del empleado es " + empleado.getNombre() + "\nEl codigo es "
					+ empleado.getCodigo() + "\nEl turno es " + empleado.getTurno());
			parseE.show();
		} else {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Buscar persona");
			parseE.setHeaderText("No fue posible encontrar el empleado buscado");
			parseE.show();
		}

	}

	@FXML
	void despedirEmpleado(ActionEvent e) {
		paneContratarEmpleado.setVisible(false);
		paneDespedirEmpleado.setVisible(true);
		paneBuscarEmpleado.setVisible(false);
		paneAgregarCargo.setVisible(false);
		paneEliminarCargo.setVisible(false);

		despedir.setOnMouseClicked(mouse -> despedir());
	}

	void despedir() {
		paneDespedirEmpleado.setVisible(false);
		String codigo = codigoDespedirEmpleado.getText();
		try {
			supermercado.despedirEmpleado(codigo);
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Despedir empleado");
			parseE.setHeaderText("El empleado fue despedido con éxito");
			parseE.show();
		} catch (CargoSupermercadoException e) {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Ha ocurrido un error");
			parseE.setHeaderText(e.getMessage());
			parseE.show();
		}
	}

	@FXML
	void agregarCargo(ActionEvent e) {
		paneContratarEmpleado.setVisible(false);
		paneDespedirEmpleado.setVisible(false);
		paneBuscarEmpleado.setVisible(false);
		paneAgregarCargo.setVisible(true);
		paneEliminarCargo.setVisible(false);

		agregar.setOnMouseClicked(mouse -> agregarCargoNuevo());
	}

	void agregarCargoNuevo() {
		paneAgregarCargo.setVisible(false);
		String nombre = nombreCargoAgregar.getText();
		String jefe = jefeCargoAgregar.getText();
		try {
			supermercado.crearCargo(nombre, jefe);
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Crear cargo");
			parseE.setHeaderText("El cargo fue creado con éxito");
			parseE.show();
		} catch (CargoSupermercadoException e) {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Ha ocurrido un error");
			parseE.setHeaderText(e.getMessage());
			parseE.show();
		}
	}

	@FXML
	void eliminarCargo(ActionEvent e) {
		paneContratarEmpleado.setVisible(false);
		paneDespedirEmpleado.setVisible(false);
		paneBuscarEmpleado.setVisible(false);
		paneAgregarCargo.setVisible(false);
		paneEliminarCargo.setVisible(true);

		eliminar.setOnMouseClicked(mouse -> eliminarCargoElegido());
	}

	void eliminarCargoElegido() {
		paneEliminarCargo.setVisible(false);
		String nombre = nombreEliminarCargo.getText();
		try {
			supermercado.eliminarCargo(nombre);
			Alert parseE = new Alert(AlertType.INFORMATION);
			parseE.setTitle("Eliminar cargo");
			parseE.setHeaderText("El cargo fue eliminado con éxito");
			parseE.show();
		} catch (CargoSupermercadoException e) {
			Alert parseE = new Alert(AlertType.ERROR);
			parseE.setTitle("Ha ocurrido un error");
			parseE.setHeaderText(e.getMessage());
			parseE.show();
		}
	}

	@FXML
	void guardar() {
		File archivo = new File("./data/SupermercadoClientes.txt");
		try {
			if (archivo.exists()) {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
				oos.writeObject(supermercado);
				oos.close();

			} else {
				archivo.createNewFile();
			}
			Alert excepcion1 = new Alert(AlertType.INFORMATION);
			excepcion1.setTitle("Guardar información");
			excepcion1.setHeaderText("Información guardada con exito en el archivo");
			excepcion1.show();

		} catch (FileNotFoundException e) {
			Alert excepcion2 = new Alert(AlertType.ERROR);
			excepcion2.setTitle("Archivo no encontrado");
			excepcion2.setHeaderText("No fue posible guardar la información, el archivo no fue encontrado");
			excepcion2.show();
		} catch (IOException e) {
			Alert excepcion3 = new Alert(AlertType.ERROR);
			excepcion3.setTitle("No fue posible leer el archivo");
			excepcion3.setHeaderText("Ocurrio un error intentando leer el archivo");
			excepcion3.show();
		}
	}

	void cargar() {
		File archivo = new File("./data/SupermercadoClientes.txt");
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

	void configurarCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image image;
		try {
			// image = new Image(new FileInputStream("img\\Cajera1.png"));
			image = new Image(new FileInputStream("img/Cajera3.png"));
			gc.drawImage(image, 0, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
